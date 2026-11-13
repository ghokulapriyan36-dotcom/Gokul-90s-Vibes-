package com.example.audio

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import com.example.model.SynthNote
import com.example.model.SynthWaveform
import com.example.model.ThemeMusicClip
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sin

data class SynthPlaybackState(
    val activeClip: ThemeMusicClip? = null,
    val isPlaying: Boolean = false,
    val progress: Float = 0f,
    val currentNoteLabel: String = "",
    val equalizerBars: List<Float> = List(8) { 0.15f }
)

class RetroSynthPlayer {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private var playbackJob: Job? = null
    private var activeTrack: AudioTrack? = null

    private val _state = MutableStateFlow(SynthPlaybackState())
    val state: StateFlow<SynthPlaybackState> = _state.asStateFlow()

    fun togglePlay(clip: ThemeMusicClip) {
        val current = _state.value
        if (current.activeClip?.id == clip.id && current.isPlaying) {
            stop()
        } else {
            play(clip)
        }
    }

    fun play(clip: ThemeMusicClip) {
        stopInternal()
        _state.value = SynthPlaybackState(
            activeClip = clip,
            isPlaying = true,
            progress = 0f,
            currentNoteLabel = clip.notes.firstOrNull()?.label ?: "♪",
            equalizerBars = listOf(0.5f, 0.8f, 0.6f, 0.9f, 0.7f, 0.85f, 0.5f, 0.4f)
        )

        playbackJob = scope.launch {
            val sampleRate = 22050
            val pcmData = synthesizeClip(clip, sampleRate)
            val totalSamples = pcmData.size
            val totalDurationMs = (totalSamples * 1000L) / sampleRate

            val minBuf = AudioTrack.getMinBufferSize(
                sampleRate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT
            ).coerceAtLeast(4096)

            val track = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(sampleRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setTransferMode(AudioTrack.MODE_STATIC)
                .setBufferSizeInBytes((pcmData.size * 2).coerceAtLeast(minBuf))
                .build()

            activeTrack = track

            try {
                track.write(pcmData, 0, pcmData.size)
                track.play()

                val noteSchedule = buildNoteSchedule(clip.notes)
                val startTime = System.currentTimeMillis()
                while (isActive) {
                    val elapsed = System.currentTimeMillis() - startTime
                    if (elapsed >= totalDurationMs) break

                    val progress = (elapsed.toFloat() / totalDurationMs.coerceAtLeast(1L)).coerceIn(0f, 1f)
                    val activeNote = noteSchedule.firstOrNull { elapsed in it.first..it.second }?.third
                    val bars = computeVisualizerBars(
                        freqHz = activeNote?.freqHz ?: 0f,
                        phaseSeed = elapsed / 75f
                    )

                    _state.update {
                        it.copy(
                            isPlaying = true,
                            progress = progress,
                            currentNoteLabel = activeNote?.label ?: "♪",
                            equalizerBars = bars
                        )
                    }
                    delay(60L)
                }
            } catch (_: Throwable) {
                // Graceful fallback if audio hardware is unavailable (e.g., headless unit test)
            } finally {
                stopInternal()
                _state.update {
                    it.copy(
                        isPlaying = false,
                        progress = 0f,
                        equalizerBars = List(8) { 0.12f }
                    )
                }
            }
        }
    }

    fun stop() {
        stopInternal()
        _state.update {
            it.copy(
                isPlaying = false,
                progress = 0f,
                equalizerBars = List(8) { 0.12f }
            )
        }
    }

    fun dismiss() {
        stopInternal()
        _state.value = SynthPlaybackState()
    }

    fun release() {
        stopInternal()
        scope.cancel()
    }

    private fun stopInternal() {
        playbackJob?.cancel()
        playbackJob = null
        activeTrack?.let { track ->
            try {
                if (track.playState == AudioTrack.PLAYSTATE_PLAYING) {
                    track.pause()
                    track.flush()
                }
                track.stop()
            } catch (_: IllegalStateException) {
            } finally {
                track.release()
            }
        }
        activeTrack = null
    }

    private fun buildNoteSchedule(notes: List<SynthNote>): List<Triple<Long, Long, SynthNote>> {
        val result = ArrayList<Triple<Long, Long, SynthNote>>(notes.size)
        var cursor = 0L
        for (note in notes) {
            val end = cursor + note.durationMs
            result.add(Triple(cursor, end, note))
            cursor = end
        }
        return result
    }

    private fun computeVisualizerBars(freqHz: Float, phaseSeed: Float): List<Float> {
        if (freqHz <= 0f) return List(8) { 0.14f }
        val baseEnergy = ((freqHz - 180f) / 700f).coerceIn(0.35f, 0.95f)
        return List(8) { index ->
            val wave = abs(sin((phaseSeed + index * 0.7f).toDouble())).toFloat()
            (0.22f + baseEnergy * 0.75f * wave).coerceIn(0.15f, 1f)
        }
    }

    private fun synthesizeClip(clip: ThemeMusicClip, sampleRate: Int): ShortArray {
        val totalSamples = clip.notes.sumOf { (it.durationMs * sampleRate) / 1000 }
        val buffer = ShortArray(totalSamples)
        var cursor = 0

        for (note in clip.notes) {
            val count = (note.durationMs * sampleRate) / 1000
            if (count <= 0) continue

            val attackSamples = (sampleRate * 0.012f).toInt().coerceAtMost(count / 4)
            val releaseSamples = (sampleRate * 0.035f).toInt().coerceAtMost(count / 3)

            for (i in 0 until count) {
                if (cursor + i >= buffer.size) break
                if (note.freqHz <= 0f) {
                    buffer[cursor + i] = 0
                    continue
                }

                val t = i.toDouble() / sampleRate
                val lead = sampleWaveform(clip.waveform, note.freqHz.toDouble(), t)
                val harmony = if (note.harmonyHz > 0f) {
                    0.45 * sampleWaveform(SynthWaveform.TRIANGLE, note.harmonyHz.toDouble(), t)
                } else {
                    0.0
                }

                val envelope = when {
                    i < attackSamples && attackSamples > 0 -> i.toDouble() / attackSamples
                    i > count - releaseSamples && releaseSamples > 0 ->
                        ((count - i).toDouble() / releaseSamples).coerceAtLeast(0.0)
                    else -> 1.0
                }

                val mixed = ((lead * 0.65 + harmony * 0.35) * envelope * 0.42).coerceIn(-1.0, 1.0)
                buffer[cursor + i] = (mixed * Short.MAX_VALUE).toInt().toShort()
            }
            cursor += count
        }
        return buffer
    }

    private fun sampleWaveform(waveform: SynthWaveform, freqHz: Double, timeSec: Double): Double {
        val phase = (timeSec * freqHz) % 1.0
        return when (waveform) {
            SynthWaveform.SQUARE -> if (phase < 0.5) 0.85 else -0.85
            SynthWaveform.SAWTOOTH -> 2.0 * phase - 1.0
            SynthWaveform.TRIANGLE -> 1.0 - 4.0 * abs(phase - 0.5)
        }
    }
}
