package com.example.ui.screens

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.audio.SynthPlaybackState
import com.example.data.l10n.AppLanguage
import com.example.data.l10n.LocalizedContentProvider
import com.example.model.NostalgiaDetailItem
import com.example.model.ShowCategory
import com.example.model.ThemeMusicClip
import com.example.model.TvChannel
import com.example.model.TvShowItem
import com.example.ui.components.ChannelBadge
import com.example.ui.components.FavoriteIconButton
import com.example.ui.components.RetroCard
import com.example.ui.components.RetroEmptyState
import com.example.ui.components.RetroFilterChip
import com.example.ui.components.RetroHorizontalChipsRow
import com.example.ui.components.RetroSectionHeader
import com.example.ui.components.RetroTagPill
import com.example.ui.theme.LocalRetroExtras
import com.example.viewmodel.NostalgiaUiState

enum class ShowsSectionMode {
    CARTOONS,
    TV_SHOWS
}

@Composable
fun ShowsAndHeroesScreen(
    mode: ShowsSectionMode,
    uiState: NostalgiaUiState,
    playbackState: SynthPlaybackState,
    allShows: List<TvShowItem>,
    onChannelSelected: (TvChannel?) -> Unit,
    onCategorySelected: (ShowCategory?) -> Unit,
    onToggleTamilOnly: (Boolean) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onToggleThemeClip: (ThemeMusicClip) -> Unit,
    onOpenDetail: (NostalgiaDetailItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val lang = uiState.appLanguage
    val labels = remember(lang) { LocalizedContentProvider.uiLabels(lang) }

    val availableCategories = remember(mode) {
        when (mode) {
            ShowsSectionMode.CARTOONS -> listOf(ShowCategory.CARTOON, ShowCategory.SUPERHERO)
            ShowsSectionMode.TV_SHOWS -> ShowCategory.entries
        }
    }

    val filteredShows = remember(
        mode,
        allShows,
        uiState.selectedChannel,
        uiState.selectedShowCategory,
        uiState.tamilOnly
    ) {
        allShows.filter { show ->
            val modeCategoryMatch = when (mode) {
                ShowsSectionMode.CARTOONS -> {
                    val activeCat = uiState.selectedShowCategory
                    if (activeCat == ShowCategory.CARTOON || activeCat == ShowCategory.SUPERHERO) {
                        show.category == activeCat
                    } else {
                        show.category == ShowCategory.CARTOON || show.category == ShowCategory.SUPERHERO
                    }
                }
                ShowsSectionMode.TV_SHOWS -> {
                    uiState.selectedShowCategory == null || show.category == uiState.selectedShowCategory
                }
            }
            val channelMatches = uiState.selectedChannel == null ||
                show.primaryChannel == uiState.selectedChannel ||
                uiState.selectedChannel in show.otherChannels
            val tamilMatches = !uiState.tamilOnly || show.isTamilClassic
            modeCategoryMatch && channelMatches && tamilMatches
        }
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
                RetroSectionHeader(
                    title = if (mode == ShowsSectionMode.CARTOONS) labels.cartoonsHeader else labels.tvShowsHeader,
                    subtitle = if (mode == ShowsSectionMode.CARTOONS) labels.cartoonsSubtitle else labels.tvShowsSubtitle
                )
            }

            // Channel filter chips
            item {
                RetroHorizontalChipsRow {
                    RetroFilterChip(
                        label = labels.allChannels,
                        selected = uiState.selectedChannel == null,
                        onClick = { onChannelSelected(null) },
                        testTag = "channel_chip_all"
                    )
                    TvChannel.entries.forEach { channel ->
                        RetroFilterChip(
                            label = LocalizedContentProvider.channelName(channel, lang),
                            selected = uiState.selectedChannel == channel,
                            onClick = {
                                onChannelSelected(
                                    if (uiState.selectedChannel == channel) null else channel
                                )
                            },
                            testTag = "channel_chip_${channel.name.lowercase()}",
                            accentColor = channel.brandColor
                        )
                    }
                }
            }

            // Category + Tamil 90s filter chips
            item {
                RetroHorizontalChipsRow {
                    RetroFilterChip(
                        label = labels.tamilOnlyChip,
                        selected = uiState.tamilOnly,
                        onClick = { onToggleTamilOnly(!uiState.tamilOnly) },
                        testTag = "show_filter_tamil",
                        accentColor = Color(0xFFD84315)
                    )
                    RetroFilterChip(
                        label = labels.allTypesChip,
                        selected = uiState.selectedShowCategory == null,
                        onClick = { onCategorySelected(null) },
                        testTag = "show_type_all",
                        accentColor = MaterialTheme.colorScheme.secondary
                    )
                    availableCategories.forEach { cat ->
                        RetroFilterChip(
                            label = LocalizedContentProvider.showCategoryLabel(cat, lang),
                            selected = uiState.selectedShowCategory == cat,
                            onClick = {
                                onCategorySelected(
                                    if (uiState.selectedShowCategory == cat) null else cat
                                )
                            },
                            testTag = "show_type_${cat.name.lowercase()}",
                            accentColor = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }

            if (filteredShows.isEmpty()) {
                item {
                    RetroEmptyState(
                        title = LocalizedContentProvider.noShowsOnFrequencyTitle(lang),
                        hint = LocalizedContentProvider.noShowsOnFrequencyHint(lang)
                    )
                }
            } else {
                items(filteredShows, key = { it.id }) { show ->
                    TvShowCard(
                        show = show,
                        lang = lang,
                        isFavorite = show.id in uiState.favoriteIds,
                        isPlayingTheme = playbackState.activeClip?.id == show.themeClip?.id && playbackState.isPlaying,
                        onToggleFavorite = { onToggleFavorite(show.id) },
                        onPlayTheme = { show.themeClip?.let(onToggleThemeClip) },
                        onClick = { onOpenDetail(NostalgiaDetailItem.Show(show)) }
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun TvShowCard(
    show: TvShowItem,
    lang: AppLanguage,
    isFavorite: Boolean,
    isPlayingTheme: Boolean,
    onToggleFavorite: () -> Unit,
    onPlayTheme: () -> Unit,
    onClick: () -> Unit
) {
    RetroCard(
        modifier = Modifier.testTag("show_card_${show.id}"),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
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
                        if (show.isTamilClassic) {
                            RetroTagPill(
                                text = LocalizedContentProvider.tamilClassicBadge(lang),
                                containerColor = Color(0xFFD84315),
                                contentColor = Color.White
                            )
                        } else {
                            show.otherChannels.take(1).forEach { extra ->
                                RetroTagPill(text = "+ ${LocalizedContentProvider.channelName(extra, lang)}")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = show.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = show.taglineOrHindiTitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                FavoriteIconButton(
                    isFavorite = isFavorite,
                    onToggle = onToggleFavorite,
                    itemLabel = show.title,
                    testTag = "fav_${show.id}"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = show.synopsis,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${show.yearsAired} • ${show.typicalSlot}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
                if (show.themeClip != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedButton(
                        onClick = onPlayTheme,
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("play_theme_inline_${show.id}"),
                        border = BorderStroke(1.5.dp, LocalRetroExtras.current.inkBorder),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Icon(
                            imageVector = if (isPlayingTheme) Icons.Filled.Stop else Icons.Filled.MusicNote,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = LocalizedContentProvider.inlineThemeLabel(isPlayingTheme, lang),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }
}
