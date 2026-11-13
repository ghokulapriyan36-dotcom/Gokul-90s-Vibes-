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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.l10n.AppLanguage
import com.example.data.l10n.LocalizedContentProvider
import com.example.model.NostalgiaDetailItem
import com.example.model.ToyCategory
import com.example.model.ToyItem
import com.example.model.TvShowItem
import com.example.model.VideoGameItem
import com.example.ui.components.ChannelBadge
import com.example.ui.components.CrtBannerFrame
import com.example.ui.components.FavoriteIconButton
import com.example.ui.components.RetroCard
import com.example.ui.components.RetroEmptyState
import com.example.ui.components.RetroFilterChip
import com.example.ui.components.RetroHorizontalChipsRow
import com.example.ui.components.RetroSectionHeader
import com.example.ui.components.RetroTagPill
import com.example.ui.theme.LocalRetroExtras
import com.example.viewmodel.NostalgiaUiState

@Composable
fun ToysAndVaultScreen(
    uiState: NostalgiaUiState,
    allToys: List<ToyItem>,
    allShows: List<TvShowItem>,
    allGames: List<VideoGameItem>,
    onSelectToyCategory: (ToyCategory?) -> Unit,
    onToggleTamilOnly: (Boolean) -> Unit,
    onToggleVaultOnly: (Boolean) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onToggleScanlines: (Boolean) -> Unit,
    onToggleDarkMode: (Boolean) -> Unit,
    onOpenDetail: (NostalgiaDetailItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val lang = uiState.appLanguage
    val labels = remember(lang) { LocalizedContentProvider.uiLabels(lang) }

    val filteredToys = remember(allToys, uiState.selectedToyCategory, uiState.tamilOnly) {
        allToys.filter { toy ->
            val catMatch = uiState.selectedToyCategory == null || toy.category == uiState.selectedToyCategory
            val tamilMatch = !uiState.tamilOnly || toy.isTamilClassic
            catMatch && tamilMatch
        }
    }

    val savedShows = remember(allShows, uiState.favoriteIds) {
        allShows.filter { it.id in uiState.favoriteIds }
    }
    val savedGames = remember(allGames, uiState.favoriteIds) {
        allGames.filter { it.id in uiState.favoriteIds }
    }
    val savedToys = remember(allToys, uiState.favoriteIds) {
        allToys.filter { it.id in uiState.favoriteIds }
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
                CrtBannerFrame(
                    imageRes = R.drawable.img_toys_nostalgia,
                    contentDescription = "90s Indian toys including kanche marbles, lattu spinning top, cassette tape and tazos",
                    channelBadge = "SCHOOL BAG & SUMMER VAULT",
                    headline = labels.toysBannerHeadline,
                    subtitle = labels.toysBannerSubtitle,
                    scanlinesEnabled = uiState.crtScanlinesEnabled,
                    height = 176.dp
                )
            }

            // Toggle between Toys Archive and My Saved Vault
            item {
                RetroHorizontalChipsRow {
                    RetroFilterChip(
                        label = "${labels.allToysTab} (${allToys.size})",
                        selected = !uiState.showVaultOnly,
                        onClick = { onToggleVaultOnly(false) },
                        testTag = "vault_tab_toys"
                    )
                    RetroFilterChip(
                        label = "${labels.savedVaultTab} (${uiState.favoriteIds.size})",
                        selected = uiState.showVaultOnly,
                        onClick = { onToggleVaultOnly(true) },
                        testTag = "vault_tab_saved",
                        accentColor = MaterialTheme.colorScheme.tertiary
                    )
                }
            }

            if (!uiState.showVaultOnly) {
                item {
                    RetroSectionHeader(
                        title = labels.toysHeader,
                        subtitle = labels.toysSubtitle
                    )
                }

                item {
                    RetroHorizontalChipsRow {
                        RetroFilterChip(
                            label = labels.tamilOnlyChip,
                            selected = uiState.tamilOnly,
                            onClick = { onToggleTamilOnly(!uiState.tamilOnly) },
                            testTag = "toy_filter_tamil",
                            accentColor = Color(0xFFD84315)
                        )
                        RetroFilterChip(
                            label = labels.filterAll,
                            selected = uiState.selectedToyCategory == null,
                            onClick = { onSelectToyCategory(null) },
                            testTag = "toy_cat_all",
                            accentColor = MaterialTheme.colorScheme.secondary
                        )
                        ToyCategory.entries.forEach { cat ->
                            RetroFilterChip(
                                label = LocalizedContentProvider.toyCategoryLabel(cat, lang),
                                selected = uiState.selectedToyCategory == cat,
                                onClick = {
                                    onSelectToyCategory(
                                        if (uiState.selectedToyCategory == cat) null else cat
                                    )
                                },
                                testTag = "toy_cat_${cat.name.lowercase()}",
                                accentColor = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }

                items(filteredToys, key = { it.id }) { toy ->
                    ToyMemoryCard(
                        toy = toy,
                        lang = lang,
                        isFavorite = toy.id in uiState.favoriteIds,
                        onToggleFavorite = { onToggleFavorite(toy.id) },
                        onClick = { onOpenDetail(NostalgiaDetailItem.Toy(toy)) }
                    )
                }
            } else {
                // Saved Vault + CRT Display Preferences
                item {
                    RetroSectionHeader(
                        title = labels.crtSettingsTitle,
                        subtitle = LocalizedContentProvider.crtSettingsSubtitle(lang)
                    )
                }

                item {
                    RetroCard(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Tv,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = labels.crtScanlinesTitle,
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    Text(
                                        text = labels.crtScanlinesDesc,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                Switch(
                                    checked = uiState.crtScanlinesEnabled,
                                    onCheckedChange = onToggleScanlines,
                                    modifier = Modifier.testTag("switch_crt_scanlines")
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.DarkMode,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = labels.crtDarkModeTitle,
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    Text(
                                        text = labels.crtDarkModeDesc,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                Switch(
                                    checked = uiState.crtDarkMode,
                                    onCheckedChange = onToggleDarkMode,
                                    modifier = Modifier.testTag("switch_crt_dark_mode")
                                )
                            }
                        }
                    }
                }

                item {
                    RetroSectionHeader(
                        title = "${labels.savedVaultTab} (${uiState.favoriteIds.size})",
                        subtitle = LocalizedContentProvider.vaultSectionSubtitle(lang)
                    )
                }

                if (savedShows.isEmpty() && savedGames.isEmpty() && savedToys.isEmpty()) {
                    item {
                        RetroEmptyState(
                            title = labels.emptyVaultTitle,
                            hint = labels.emptyVaultHint
                        )
                    }
                } else {
                    items(savedShows, key = { "saved_${it.id}" }) { show ->
                        RetroCard(onClick = { onOpenDetail(NostalgiaDetailItem.Show(show)) }) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    ChannelBadge(channel = show.primaryChannel, lang = lang)
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(show.title, style = MaterialTheme.typography.titleMedium)
                                    Text(
                                        show.taglineOrHindiTitle,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                FavoriteIconButton(
                                    isFavorite = true,
                                    onToggle = { onToggleFavorite(show.id) },
                                    itemLabel = show.title,
                                    testTag = "unfav_${show.id}"
                                )
                            }
                        }
                    }

                    items(savedGames, key = { "saved_${it.id}" }) { game ->
                        RetroCard(onClick = { onOpenDetail(NostalgiaDetailItem.Game(game)) }) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    RetroTagPill(
                                        text = LocalizedContentProvider.platformShortLabel(game.platform, lang),
                                        containerColor = MaterialTheme.colorScheme.secondary,
                                        contentColor = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(game.title, style = MaterialTheme.typography.titleMedium)
                                    Text(
                                        game.genre,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                FavoriteIconButton(
                                    isFavorite = true,
                                    onToggle = { onToggleFavorite(game.id) },
                                    itemLabel = game.title,
                                    testTag = "unfav_${game.id}"
                                )
                            }
                        }
                    }

                    items(savedToys, key = { "saved_${it.id}" }) { toy ->
                        ToyMemoryCard(
                            toy = toy,
                            lang = lang,
                            isFavorite = true,
                            onToggleFavorite = { onToggleFavorite(toy.id) },
                            onClick = { onOpenDetail(NostalgiaDetailItem.Toy(toy)) }
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun ToyMemoryCard(
    toy: ToyItem,
    lang: AppLanguage,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onClick: () -> Unit
) {
    RetroCard(
        modifier = Modifier.testTag("toy_card_${toy.id}"),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        RetroTagPill(
                            text = LocalizedContentProvider.toyCategoryLabel(toy.category, lang),
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        )
                        if (toy.isTamilClassic) {
                            RetroTagPill(
                                text = LocalizedContentProvider.tamilClassicBadge(lang),
                                containerColor = Color(0xFFD84315),
                                contentColor = Color.White
                            )
                        } else {
                            RetroTagPill(text = toy.eraOrPrice)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = toy.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                FavoriteIconButton(
                    isFavorite = isFavorite,
                    onToggle = onToggleFavorite,
                    itemLabel = toy.name,
                    testTag = "fav_toy_${toy.id}"
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = toy.howWePlayed,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, LocalRetroExtras.current.inkBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Bookmark,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = toy.nostalgiaStory,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}
