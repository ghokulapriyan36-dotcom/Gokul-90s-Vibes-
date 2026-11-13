package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.audio.SynthPlaybackState
import com.example.data.l10n.AppLanguage
import com.example.data.l10n.LocalizedContentProvider
import com.example.data.l10n.LocalizedUiLabels
import com.example.model.NostalgiaDetailItem
import com.example.model.ShowCategory
import com.example.model.ThemeMusicClip
import com.example.model.TvShowItem
import com.example.ui.components.ChannelBadge
import com.example.ui.components.CrtBannerFrame
import com.example.ui.components.EqualizerBars
import com.example.ui.components.FavoriteIconButton
import com.example.ui.components.RetroCard
import com.example.ui.components.RetroFilterChip
import com.example.ui.components.RetroHorizontalChipsRow
import com.example.ui.components.RetroSectionHeader
import com.example.ui.components.RetroTagPill
import com.example.ui.theme.LocalRetroExtras
import com.example.viewmodel.NostalgiaUiState
import com.example.viewmodel.TriviaQuizState

@Composable
fun TriviaAndHeroesScreen(
    uiState: NostalgiaUiState,
    playbackState: SynthPlaybackState,
    triviaState: TriviaQuizState,
    allShows: List<TvShowItem>,
    onSubTabSelected: (Int) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onToggleThemeClip: (ThemeMusicClip) -> Unit,
    onToggleHeroTrivia: (String) -> Unit,
    onOpenDetail: (NostalgiaDetailItem) -> Unit,
    onTriviaAnswer: (Int) -> Unit,
    onTriviaNext: () -> Unit,
    onTriviaRestart: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lang = uiState.appLanguage
    val labels = remember(lang) { LocalizedContentProvider.uiLabels(lang) }

    val superheroes = remember(allShows) {
        allShows.filter { it.category == ShowCategory.SUPERHERO }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 840.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                RetroHorizontalChipsRow {
                    RetroFilterChip(
                        label = labels.subtabTrivia,
                        selected = uiState.triviaSubTab == 0,
                        onClick = { onSubTabSelected(0) },
                        testTag = "subtab_trivia",
                        accentColor = MaterialTheme.colorScheme.secondary
                    )
                    RetroFilterChip(
                        label = "${labels.subtabSuperheroes} (${superheroes.size})",
                        selected = uiState.triviaSubTab == 1,
                        onClick = { onSubTabSelected(1) },
                        testTag = "subtab_superheroes",
                        accentColor = MaterialTheme.colorScheme.tertiary
                    )
                }
            }

            if (uiState.triviaSubTab == 0) {
                item {
                    TriviaQuizSection(
                        triviaState = triviaState,
                        lang = lang,
                        labels = labels,
                        bestScore = uiState.bestTriviaScore,
                        onSelectOption = onTriviaAnswer,
                        onNextQuestion = onTriviaNext,
                        onRestart = onTriviaRestart
                    )
                }
            } else {
                item {
                    CrtBannerFrame(
                        imageRes = R.drawable.img_superhero_spotlight,
                        contentDescription = "Retro 90s comic superhero illustration",
                        channelBadge = "SUPERHERO JUKEBOX • 8-BIT SYNTH",
                        headline = labels.superheroBannerHeadline,
                        subtitle = labels.superheroBannerSubtitle,
                        scanlinesEnabled = uiState.crtScanlinesEnabled,
                        height = 176.dp
                    )
                }

                item {
                    RetroCard(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        onClick = { onSubTabSelected(0) },
                        modifier = Modifier.testTag("hero_banner_quiz_cta")
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.EmojiEvents,
                                contentDescription = labels.subtabTrivia,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = labels.startQuizCta,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = LocalizedContentProvider.bestScoreLabel(
                                        best = uiState.bestTriviaScore,
                                        total = triviaState.questions.size,
                                        lang = lang
                                    ),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                items(superheroes, key = { it.id }) { heroShow ->
                    SuperheroSpotlightCard(
                        show = heroShow,
                        lang = lang,
                        labels = labels,
                        isFavorite = heroShow.id in uiState.favoriteIds,
                        isTriviaExpanded = heroShow.id in uiState.expandedHeroTriviaIds,
                        playbackState = playbackState,
                        onToggleFavorite = { onToggleFavorite(heroShow.id) },
                        onToggleThemeClip = { heroShow.themeClip?.let(onToggleThemeClip) },
                        onToggleTrivia = { onToggleHeroTrivia(heroShow.id) },
                        onOpenDetail = { onOpenDetail(NostalgiaDetailItem.Show(heroShow)) }
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun SuperheroSpotlightCard(
    show: TvShowItem,
    lang: AppLanguage,
    labels: LocalizedUiLabels,
    isFavorite: Boolean,
    isTriviaExpanded: Boolean,
    playbackState: SynthPlaybackState,
    onToggleFavorite: () -> Unit,
    onToggleThemeClip: () -> Unit,
    onToggleTrivia: () -> Unit,
    onOpenDetail: () -> Unit
) {
    val clip = show.themeClip
    val isThisPlaying = clip != null && playbackState.activeClip?.id == clip.id && playbackState.isPlaying
    val extras = LocalRetroExtras.current

    RetroCard(
        modifier = Modifier.testTag("hero_card_${show.id}"),
        onClick = onOpenDetail
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ChannelBadge(channel = show.primaryChannel, lang = lang)
                        RetroTagPill(
                            text = show.yearsAired,
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = show.title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = show.taglineOrHindiTitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.Bold
                    )
                }
                FavoriteIconButton(
                    isFavorite = isFavorite,
                    onToggle = onToggleFavorite,
                    itemLabel = show.title,
                    testTag = "fav_hero_${show.id}"
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (show.heroAlias != null || show.archNemesis != null) {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, extras.inkBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        show.heroAlias?.let {
                            Text(
                                text = "${labels.secretIdentityPrefix}: $it",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        show.archNemesis?.let {
                            Text(
                                text = "${labels.archNemesisPrefix}: $it",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            Text(
                text = show.synopsis,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "“${show.catchphrase}”",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )

            if (clip != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    color = extras.crtScreenBg,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(2.dp, if (isThisPlaying) extras.goldAccent else extras.inkBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MusicNote,
                                contentDescription = "Theme Clip",
                                tint = extras.goldAccent,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = clip.title,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = Color.White
                                )
                                Text(
                                    text = "${clip.waveform.name} SYNTH • ${clip.tempoBpm} BPM • ${clip.durationLabel}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFF80CBC4)
                                )
                            }
                            if (isThisPlaying) {
                                EqualizerBars(
                                    bars = playbackState.equalizerBars,
                                    isPlaying = true,
                                    barColor = extras.goldAccent,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                            }
                            Button(
                                onClick = onToggleThemeClip,
                                modifier = Modifier
                                    .minimumInteractiveComponentSize()
                                    .testTag("play_theme_${show.id}"),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isThisPlaying) MaterialTheme.colorScheme.tertiary else extras.goldAccent,
                                    contentColor = if (isThisPlaying) Color.White else Color(0xFF17162A)
                                ),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                            ) {
                                Icon(
                                    imageVector = if (isThisPlaying) Icons.Filled.Stop else Icons.Filled.PlayArrow,
                                    contentDescription = if (isThisPlaying) labels.stopClip else labels.playClip,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (isThisPlaying) labels.stopClip else labels.playClip,
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = clip.lyricCue,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFFFE082)
                        )
                    }
                }
            }

            if (show.triviaFacts.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedButton(
                    onClick = onToggleTrivia,
                    modifier = Modifier
                        .fillMaxWidth()
                        .minimumInteractiveComponentSize()
                        .testTag("toggle_trivia_${show.id}"),
                    border = BorderStroke(1.5.dp, extras.inkBorder),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Lightbulb,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isTriviaExpanded) {
                            "${labels.hideTriviaLabel} (${show.triviaFacts.size})"
                        } else {
                            "${labels.showTriviaLabel} (${show.triviaFacts.size})"
                        },
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = if (isTriviaExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = null
                    )
                }

                AnimatedVisibility(visible = isTriviaExpanded) {
                    Column(
                        modifier = Modifier.padding(top = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        show.triviaFacts.forEachIndexed { idx, fact ->
                            Row(verticalAlignment = Alignment.Top) {
                                Text(
                                    text = "#${idx + 1}",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = fact,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TriviaQuizSection(
    triviaState: TriviaQuizState,
    lang: AppLanguage,
    labels: LocalizedUiLabels,
    bestScore: Int,
    onSelectOption: (Int) -> Unit,
    onNextQuestion: () -> Unit,
    onRestart: () -> Unit
) {
    val extras = LocalRetroExtras.current

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        RetroSectionHeader(
            title = labels.quizHeader,
            subtitle = labels.quizSubtitle
        )

        RetroCard(containerColor = extras.crtScreenBg) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = LocalizedContentProvider.quizScoreLabel(triviaState.score, triviaState.questions.size, lang),
                        style = MaterialTheme.typography.titleMedium,
                        color = extras.goldAccent
                    )
                    Text(
                        text = LocalizedContentProvider.quizAllTimeBestLabel(bestScore, triviaState.questions.size, lang),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFB2DFDB)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.FlashOn,
                        contentDescription = "Streak",
                        tint = Color(0xFF00E676),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = LocalizedContentProvider.quizStreakLabel(triviaState.streak, lang),
                        style = MaterialTheme.typography.labelLarge,
                        color = Color(0xFF00E676)
                    )
                }
            }
        }

        if (triviaState.isFinished) {
            RetroCard(containerColor = MaterialTheme.colorScheme.primaryContainer) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.EmojiEvents,
                        contentDescription = "Trophy",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(52.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = labels.quizCompleteTitle,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = LocalizedContentProvider.quizResultScoreText(triviaState.score, triviaState.questions.size, lang),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = LocalizedContentProvider.quizRankLabel(triviaState.score, lang),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onRestart,
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("trivia_restart_button"),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(labels.quizRestartBtn)
                    }
                }
            }
        } else {
            val rawQuestion = triviaState.currentQuestion ?: return
            val question = remember(rawQuestion, lang) {
                LocalizedContentProvider.localizeTrivia(rawQuestion, lang)
            }
            RetroCard {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RetroTagPill(
                            text = "Q${triviaState.currentIndex + 1} / ${triviaState.questions.size}",
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = Color.White
                        )
                        RetroTagPill(text = "${question.showTitle} • ${question.channelTag}")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = question.question,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    question.options.forEachIndexed { index, option ->
                        val selected = triviaState.selectedOptionIndex
                        val isAnswered = selected != null
                        val isCorrectOption = index == question.correctIndex
                        val isSelectedOption = index == selected

                        val bgColor = when {
                            !isAnswered -> MaterialTheme.colorScheme.surfaceVariant
                            isCorrectOption -> Color(0xFFC8E6C9)
                            isSelectedOption -> Color(0xFFFFCDD2)
                            else -> MaterialTheme.colorScheme.surfaceVariant
                        }
                        val textColor = if (isAnswered && (isCorrectOption || isSelectedOption)) {
                            Color(0xFF17162A)
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }

                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .minimumInteractiveComponentSize()
                                .testTag("trivia_option_$index"),
                            color = bgColor,
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(
                                width = if (isSelectedOption || (isAnswered && isCorrectOption)) 2.5.dp else 1.5.dp,
                                color = extras.inkBorder
                            ),
                            onClick = { if (!isAnswered) onSelectOption(index) }
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${'A' + index}.",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = textColor
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = option,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = textColor,
                                    modifier = Modifier.weight(1f)
                                )
                                if (isAnswered && isCorrectOption) {
                                    Icon(
                                        imageVector = Icons.Filled.CheckCircle,
                                        contentDescription = "Correct answer",
                                        tint = Color(0xFF2E7D32)
                                    )
                                }
                            }
                        }
                    }

                    AnimatedVisibility(visible = triviaState.selectedOptionIndex != null) {
                        Column(modifier = Modifier.padding(top = 12.dp)) {
                            Surface(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.5.dp, extras.inkBorder),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = question.explanation,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.padding(12.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = onNextQuestion,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .minimumInteractiveComponentSize()
                                    .testTag("trivia_next_button"),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    text = if (triviaState.currentIndex + 1 >= triviaState.questions.size) {
                                        labels.seeFinalScoreBtn
                                    } else {
                                        labels.nextQuestionBtn
                                    },
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
