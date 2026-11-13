package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.audio.SynthPlaybackState
import com.example.data.l10n.AppLanguage
import com.example.data.l10n.LocalizedContentProvider
import com.example.model.NostalgiaDetailItem
import com.example.model.ThemeMusicClip
import com.example.ui.components.ChannelBadge
import com.example.ui.components.FavoriteIconButton
import com.example.ui.components.RetroTagPill
import com.example.ui.theme.LocalRetroExtras

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NostalgiaDetailBottomSheet(
    detail: NostalgiaDetailItem,
    favoriteIds: Set<String>,
    playbackState: SynthPlaybackState,
    appLanguage: AppLanguage = AppLanguage.ENGLISH,
    onToggleFavorite: (String) -> Unit,
    onToggleThemeClip: (ThemeMusicClip) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val extras = LocalRetroExtras.current
    val labels = LocalizedContentProvider.uiLabels(appLanguage)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            when (detail) {
                is NostalgiaDetailItem.Show -> {
                    val show = detail.item
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                ChannelBadge(channel = show.primaryChannel, lang = appLanguage)
                                show.otherChannels.forEach { ChannelBadge(channel = it, lang = appLanguage) }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = show.title,
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                text = "${show.yearsAired} • ${show.typicalSlot}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        FavoriteIconButton(
                            isFavorite = show.id in favoriteIds,
                            onToggle = { onToggleFavorite(show.id) },
                            itemLabel = show.title,
                            testTag = "sheet_fav_${show.id}"
                        )
                    }

                    Text(
                        text = show.synopsis,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.5.dp, extras.inkBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = labels.detailMemoryLabel,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = show.desiMemory,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "“${show.catchphrase}”",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    val clip = show.themeClip
                    if (clip != null) {
                        val isPlayingThis = playbackState.activeClip?.id == clip.id && playbackState.isPlaying
                        Button(
                            onClick = { onToggleThemeClip(clip) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .minimumInteractiveComponentSize()
                                .testTag("sheet_play_theme_${show.id}"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isPlayingThis) MaterialTheme.colorScheme.tertiary else extras.crtScreenBg,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(
                                imageVector = if (isPlayingThis) Icons.Filled.Stop else Icons.Filled.PlayArrow,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.Filled.MusicNote,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = LocalizedContentProvider.sheetPlayThemeLabel(isPlayingThis, clip.title, appLanguage),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }

                    if (show.triviaFacts.isNotEmpty()) {
                        Text(
                            text = labels.detailTriviaLabel,
                            style = MaterialTheme.typography.titleMedium
                        )
                        show.triviaFacts.forEach { fact ->
                            Row(verticalAlignment = Alignment.Top) {
                                Icon(
                                    imageVector = Icons.Filled.Lightbulb,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = fact, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }

                is NostalgiaDetailItem.Game -> {
                    val game = detail.item
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                RetroTagPill(
                                    text = LocalizedContentProvider.platformDisplayLabel(game.platform, appLanguage),
                                    containerColor = MaterialTheme.colorScheme.secondary,
                                    contentColor = Color.White
                                )
                                RetroTagPill(text = game.releaseEra)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = game.title,
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                text = "${game.developer} • ${game.genre} • ${game.players}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        FavoriteIconButton(
                            isFavorite = game.id in favoriteIds,
                            onToggle = { onToggleFavorite(game.id) },
                            itemLabel = game.title,
                            testTag = "sheet_fav_${game.id}"
                        )
                    }

                    Text(
                        text = labels.detailGameplayLabel,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = game.gameplayDetails,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.5.dp, extras.inkBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = labels.detailControlsLabel,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = game.controlsAndTips, style = MaterialTheme.typography.bodyMedium)
                        }
                    }

                    Surface(
                        color = extras.crtScreenBg,
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.5.dp, extras.goldAccent),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Code,
                                contentDescription = null,
                                tint = extras.goldAccent
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = labels.detailCheatsLabel,
                                    style = MaterialTheme.typography.labelLarge,
                                    color = extras.goldAccent
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = game.cheatCodesOrSecrets,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.White
                                )
                            }
                        }
                    }

                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.5.dp, extras.inkBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = labels.detailGamingMemoryLabel,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = game.desiMemory, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }

                is NostalgiaDetailItem.Toy -> {
                    val toy = detail.item
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                RetroTagPill(
                                    text = LocalizedContentProvider.toyCategoryLabel(toy.category, appLanguage),
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = Color.White
                                )
                                RetroTagPill(text = toy.eraOrPrice)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = toy.name,
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }
                        FavoriteIconButton(
                            isFavorite = toy.id in favoriteIds,
                            onToggle = { onToggleFavorite(toy.id) },
                            itemLabel = toy.name,
                            testTag = "sheet_fav_${toy.id}"
                        )
                    }

                    Text(
                        text = labels.detailHowPlayedLabel,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = toy.howWePlayed,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.5.dp, extras.inkBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = labels.detailMemoryLabel,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = toy.nostalgiaStory, style = MaterialTheme.typography.bodyMedium)
                        }
                    }

                    Surface(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.5.dp, extras.inkBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = labels.detailFunFactLabel,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = toy.funFact, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
