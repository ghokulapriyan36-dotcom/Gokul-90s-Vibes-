package com.example.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.example.ui.theme.ChannelCartoonNetwork
import com.example.ui.theme.ChannelDdMetro
import com.example.ui.theme.ChannelDdNational
import com.example.ui.theme.ChannelDdPodhigai
import com.example.ui.theme.ChannelRajTv
import com.example.ui.theme.ChannelSonyTv
import com.example.ui.theme.ChannelStarPlus
import com.example.ui.theme.ChannelSunTv
import com.example.ui.theme.ChannelVijayTv
import com.example.ui.theme.ChannelZeeTv

enum class TvChannel(
    val displayName: String,
    val badgeCode: String,
    val eraNote: String,
    val brandColor: Color,
    val isTamilPrimary: Boolean = false
) {
    SUN_TV("Sun TV", "CH 01 • SUN", "90s Tamil household king — Marmadesam & Ungal Choice", ChannelSunTv, isTamilPrimary = true),
    DD_PODHIGAI("DD Podhigai (DD-5)", "CH 02 • DD5", "Oliyum Oliyum Fridays & Tamil Sunday classics", ChannelDdPodhigai, isTamilPrimary = true),
    RAJ_TV("Raj TV", "CH 03 • RAJ", "Classic 90s Tamil movies, Koppiyam & cartoons", ChannelRajTv, isTamilPrimary = true),
    VIJAY_TV("Vijay TV", "CH 04 • VJY", "90s Tamil youth shows, game shows & serials", ChannelVijayTv, isTamilPrimary = true),
    DD_NATIONAL("DD National", "CH 05 • DD1", "Sunday 9 AM Mowgli, Shaktimaan & Malgudi Days", ChannelDdNational),
    CARTOON_NETWORK("Cartoon Network", "CH 09 • CN", "1995 India launch — 24x7 cartoons in TN cable", ChannelCartoonNetwork),
    DD_METRO("DD Metro", "CH 11 • DD2", "Youth sitcoms & 90s prime time", ChannelDdMetro),
    STAR_PLUS("Star Plus", "CH 14 • STAR", "Disney Hour, Spider-Man & Power Rangers", ChannelStarPlus),
    ZEE_TV("Zee TV", "CH 18 • ZEE", "Hum Paanch & Hip Hip Hurray", ChannelZeeTv),
    SONY_TV("Sony TV", "CH 22 • SET", "Aahat chills & Boogie Woogie", ChannelSonyTv)
}

enum class ShowCategory(val label: String) {
    CARTOON("Cartoons & Animation"),
    TV_SERIAL("Iconic TV Shows"),
    SUPERHERO("Superheroes")
}

enum class SynthWaveform {
    SQUARE,
    SAWTOOTH,
    TRIANGLE
}

@Immutable
data class SynthNote(
    val freqHz: Float,
    val durationMs: Int,
    val harmonyHz: Float = 0f,
    val label: String = "♪"
)

@Immutable
data class ThemeMusicClip(
    val id: String,
    val title: String,
    val showTitle: String,
    val waveform: SynthWaveform,
    val durationLabel: String,
    val tempoBpm: Int,
    val lyricCue: String,
    val notes: List<SynthNote>
)

@Immutable
data class TvShowItem(
    val id: String,
    val title: String,
    val taglineOrHindiTitle: String,
    val primaryChannel: TvChannel,
    val otherChannels: List<TvChannel> = emptyList(),
    val yearsAired: String,
    val typicalSlot: String,
    val category: ShowCategory,
    val synopsis: String,
    val desiMemory: String,
    val catchphrase: String,
    val isTamilClassic: Boolean = false,
    val heroAlias: String? = null,
    val archNemesis: String? = null,
    val triviaFacts: List<String> = emptyList(),
    val themeClip: ThemeMusicClip? = null
)

enum class GamePlatformType(val displayName: String, val shortTag: String) {
    HANDHELD_BRICK("Handheld Brick / LCD", "9999-IN-1"),
    EIGHT_BIT_CARTRIDGE("8-Bit Cartridge (NES/Media)", "8-BIT CART"),
    ARCADE_COIN("₹1 / ₹2 Coin Game Parlour", "ARCADE"),
    SIXTEEN_BIT_SEGA("16-Bit SEGA Mega Drive", "16-BIT"),
    PC_DOS_WIN95("MS-DOS / Windows 95-98 PC", "PC / DOS"),
    PLAYSTATION_1("Sony PlayStation (PS1)", "PS1")
}

@Immutable
data class VideoGameItem(
    val id: String,
    val title: String,
    val platform: GamePlatformType,
    val releaseEra: String,
    val genre: String,
    val developer: String,
    val players: String,
    val gameplayDetails: String,
    val controlsAndTips: String,
    val cheatCodesOrSecrets: String,
    val desiMemory: String,
    val isTamilClassic: Boolean = true
)

enum class ToyCategory(val displayName: String) {
    STREET_GAMES("Tamil & Street Games"),
    COLLECTIBLES("Tazos & Cards"),
    RETRO_TECH("90s Tech & Music"),
    CANDY_SCHOOL("Mittai & School Bag"),
    DESK_TOYS("Handheld & Desk Toys")
}

@Immutable
data class ToyItem(
    val id: String,
    val name: String,
    val category: ToyCategory,
    val eraOrPrice: String,
    val howWePlayed: String,
    val nostalgiaStory: String,
    val funFact: String,
    val isTamilClassic: Boolean = false
)

@Immutable
data class TriviaQuestion(
    val id: String,
    val showTitle: String,
    val channelTag: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

sealed interface NostalgiaDetailItem {
    data class Show(val item: TvShowItem) : NostalgiaDetailItem
    data class Game(val item: VideoGameItem) : NostalgiaDetailItem
    data class Toy(val item: ToyItem) : NostalgiaDetailItem
}
