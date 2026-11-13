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
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.SportsEsports
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.l10n.AppLanguage
import com.example.data.l10n.LocalizedContentProvider
import com.example.data.l10n.LocalizedUiLabels
import com.example.model.GamePlatformType
import com.example.model.NostalgiaDetailItem
import com.example.model.VideoGameItem
import com.example.ui.components.CrtBannerFrame
import com.example.ui.components.FavoriteIconButton
import com.example.ui.components.RetroCard
import com.example.ui.components.RetroFilterChip
import com.example.ui.components.RetroHorizontalChipsRow
import com.example.ui.components.RetroSectionHeader
import com.example.ui.components.RetroTagPill
import com.example.ui.theme.LocalRetroExtras
import com.example.viewmodel.NostalgiaUiState

@Composable
fun VideoGamesScreen(
    uiState: NostalgiaUiState,
    allGames: List<VideoGameItem>,
    onPlatformSelected: (GamePlatformType?) -> Unit,
    onToggleCheat: (String) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onOpenDetail: (NostalgiaDetailItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val lang = uiState.appLanguage
    val labels = remember(lang) { LocalizedContentProvider.uiLabels(lang) }

    val filteredGames = remember(allGames, uiState.selectedPlatform) {
        if (uiState.selectedPlatform == null) allGames
        else allGames.filter { it.platform == uiState.selectedPlatform }
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
                    imageRes = R.drawable.img_retro_gaming,
                    contentDescription = "Retro 90s Brick Game, yellow cartridge, gamepad and arcade controls",
                    channelBadge = "PLAYER 1 READY • 64-IN-1",
                    headline = labels.gamesBannerHeadline,
                    subtitle = labels.gamesBannerSubtitle,
                    scanlinesEnabled = uiState.crtScanlinesEnabled,
                    height = 176.dp
                )
            }

            item {
                RetroSectionHeader(
                    title = labels.gamesHeader,
                    subtitle = LocalizedContentProvider.gamesShowingSubtitle(filteredGames.size, lang)
                )
            }

            item {
                RetroHorizontalChipsRow {
                    RetroFilterChip(
                        label = "${labels.allTypesChip} (${allGames.size})",
                        selected = uiState.selectedPlatform == null,
                        onClick = { onPlatformSelected(null) },
                        testTag = "platform_chip_all",
                        accentColor = MaterialTheme.colorScheme.secondary
                    )
                    GamePlatformType.entries.forEach { platform ->
                        RetroFilterChip(
                            label = LocalizedContentProvider.platformShortLabel(platform, lang),
                            selected = uiState.selectedPlatform == platform,
                            onClick = {
                                onPlatformSelected(
                                    if (uiState.selectedPlatform == platform) null else platform
                                )
                            },
                            testTag = "platform_chip_${platform.name.lowercase()}",
                            accentColor = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }

            items(filteredGames, key = { it.id }) { game ->
                VideoGameCard(
                    game = game,
                    lang = lang,
                    labels = labels,
                    isFavorite = game.id in uiState.favoriteIds,
                    isCheatRevealed = game.id in uiState.revealedCheatGameIds,
                    onToggleFavorite = { onToggleFavorite(game.id) },
                    onToggleCheat = { onToggleCheat(game.id) },
                    onClick = { onOpenDetail(NostalgiaDetailItem.Game(game)) }
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun VideoGameCard(
    game: VideoGameItem,
    lang: AppLanguage,
    labels: LocalizedUiLabels,
    isFavorite: Boolean,
    isCheatRevealed: Boolean,
    onToggleFavorite: () -> Unit,
    onToggleCheat: () -> Unit,
    onClick: () -> Unit
) {
    val extras = LocalRetroExtras.current

    RetroCard(
        modifier = Modifier.testTag("game_card_${game.id}"),
        onClick = onClick
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
                        RetroTagPill(
                            text = LocalizedContentProvider.platformDisplayLabel(game.platform, lang),
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = Color.White
                        )
                        RetroTagPill(text = game.releaseEra)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = game.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.SportsEsports,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = game.genre,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.People,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = game.players,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                FavoriteIconButton(
                    isFavorite = isFavorite,
                    onToggle = onToggleFavorite,
                    itemLabel = game.title,
                    testTag = "fav_game_${game.id}"
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = game.gameplayDetails,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, extras.inkBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Filled.Gamepad,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${labels.controlsTipPrefix}: ${game.controlsAndTips}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(
                onClick = onToggleCheat,
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumInteractiveComponentSize()
                    .testTag("cheat_toggle_${game.id}"),
                border = BorderStroke(1.5.dp, extras.inkBorder),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Code,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isCheatRevealed) labels.hideCheatBtn else labels.unlockCheatBtn,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            AnimatedVisibility(visible = isCheatRevealed) {
                Surface(
                    color = extras.crtScreenBg,
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.5.dp, extras.goldAccent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = game.cheatCodesOrSecrets,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = extras.goldAccent,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}
