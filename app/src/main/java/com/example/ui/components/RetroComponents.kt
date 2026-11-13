package com.example.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SettingsInputAntenna
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.audio.SynthPlaybackState
import com.example.data.l10n.AppLanguage
import com.example.data.l10n.LocalizedContentProvider
import com.example.model.TvChannel
import com.example.ui.theme.LocalRetroExtras

@Composable
fun RetroCard(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    shadowOffset: Dp = 4.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val extras = LocalRetroExtras.current
    val shape = MaterialTheme.shapes.medium

    Box(
        modifier = modifier
            .padding(end = shadowOffset, bottom = shadowOffset)
    ) {
        // Hard 90s comic-zine drop shadow
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = shadowOffset, y = shadowOffset)
                .background(color = extras.hardShadow, shape = shape)
        )

        val cardModifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(color = containerColor, shape = shape)
            .border(width = 2.dp, color = extras.inkBorder, shape = shape)
            .let { base ->
                if (onClick != null) base.clickable(onClick = onClick) else base
            }

        Column(
            modifier = cardModifier,
            content = content
        )
    }
}

@Composable
fun CrtBannerFrame(
    @DrawableRes imageRes: Int,
    contentDescription: String,
    channelBadge: String,
    headline: String,
    subtitle: String,
    scanlinesEnabled: Boolean,
    modifier: Modifier = Modifier,
    height: Dp = 196.dp
) {
    val extras = LocalRetroExtras.current
    val shape = MaterialTheme.shapes.large

    RetroCard(
        modifier = modifier.fillMaxWidth(),
        containerColor = extras.crtScreenBg,
        shadowOffset = 5.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .clip(shape)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Gradient scrim for crisp 90s OSD legibility
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0x66101122),
                                Color(0x22101122),
                                Color(0xCC101122)
                            )
                        )
                    )
            )

            // Retro CRT Scanlines drawn via Canvas
            if (scanlinesEnabled) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val step = 6.dp.toPx()
                    var y = 0f
                    while (y < size.height) {
                        drawLine(
                            color = Color(0x2E000000),
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = 1.5f
                        )
                        y += step
                    }
                }
            }

            // Top-left VHS / CRT OSD badge
            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
                    .background(
                        color = Color(0xCC151436),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .border(1.dp, extras.goldAccent, RoundedCornerShape(6.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color(0xFF00E676), CircleShape)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = channelBadge,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF00E676)
                )
            }

            // Bottom title overlay
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = headline,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFFFE082),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun ChannelBadge(
    channel: TvChannel,
    modifier: Modifier = Modifier,
    lang: AppLanguage = AppLanguage.ENGLISH
) {
    Surface(
        modifier = modifier,
        color = channel.brandColor,
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(1.5.dp, LocalRetroExtras.current.inkBorder)
    ) {
        Text(
            text = LocalizedContentProvider.channelName(channel, lang).uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun RetroTagPill(
    text: String,
    containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = containerColor,
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(1.dp, LocalRetroExtras.current.inkBorder)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = contentColor,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}

@Composable
fun RetroFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    testTag: String,
    modifier: Modifier = Modifier,
    accentColor: Color = MaterialTheme.colorScheme.primary
) {
    val extras = LocalRetroExtras.current
    val bgColor = if (selected) accentColor else MaterialTheme.colorScheme.surface
    val fgColor = if (selected) Color.White else MaterialTheme.colorScheme.onSurface

    Surface(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .testTag(testTag),
        color = bgColor,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, extras.inkBorder),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = fgColor,
                maxLines = 1
            )
        }
    }
}

@Composable
fun RetroSectionHeader(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    val extras = LocalRetroExtras.current
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(8.dp)
                    .height(24.dp)
                    .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(3.dp))
                    .border(1.5.dp, extras.inkBorder, RoundedCornerShape(3.dp))
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        if (subtitle != null) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun FavoriteIconButton(
    isFavorite: Boolean,
    onToggle: () -> Unit,
    itemLabel: String,
    testTag: String
) {
    IconButton(
        onClick = onToggle,
        modifier = Modifier
            .minimumInteractiveComponentSize()
            .testTag(testTag)
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
            contentDescription = if (isFavorite) "Remove $itemLabel from Vault" else "Save $itemLabel to Vault",
            tint = if (isFavorite) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun EqualizerBars(
    bars: List<Float>,
    isPlaying: Boolean,
    barColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.height(22.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        bars.forEach { level ->
            val targetHeight by animateFloatAsState(
                targetValue = if (isPlaying) level else 0.15f,
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                label = "eqBar"
            )
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height((22f * targetHeight).coerceAtLeast(3f).dp)
                    .background(barColor, RoundedCornerShape(2.dp))
            )
        }
    }
}

@Composable
fun MiniThemePlayerBar(
    playbackState: SynthPlaybackState,
    onTogglePlay: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    lang: AppLanguage = AppLanguage.ENGLISH
) {
    val clip = playbackState.activeClip ?: return
    val extras = LocalRetroExtras.current

    AnimatedVisibility(visible = true, modifier = modifier) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp),
            color = extras.crtScreenBg,
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(2.dp, extras.goldAccent),
            shadowElevation = 6.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.GraphicEq,
                        contentDescription = "Retro theme synthesizer",
                        tint = extras.goldAccent,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${clip.showTitle} • ${clip.title}",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = LocalizedContentProvider.miniPlayerStatus(
                                isPlaying = playbackState.isPlaying,
                                noteLabel = playbackState.currentNoteLabel,
                                lang = lang
                            ),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFB2DFDB),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    EqualizerBars(
                        bars = playbackState.equalizerBars,
                        isPlaying = playbackState.isPlaying,
                        barColor = extras.goldAccent,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    IconButton(
                        onClick = onTogglePlay,
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("mini_player_toggle")
                    ) {
                        Icon(
                            imageVector = if (playbackState.isPlaying) Icons.Filled.Stop else Icons.Filled.PlayArrow,
                            contentDescription = if (playbackState.isPlaying) "Stop theme clip" else "Play theme clip",
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("mini_player_dismiss")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close theme player",
                            tint = Color(0xFFB0BEC5)
                        )
                    }
                }
                LinearProgressIndicator(
                    progress = { playbackState.progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp),
                    color = extras.goldAccent,
                    trackColor = Color(0xFF282B59)
                )
            }
        }
    }
}

@Composable
fun RetroEmptyState(
    title: String,
    hint: String,
    modifier: Modifier = Modifier
) {
    RetroCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Filled.SettingsInputAntenna,
                contentDescription = "Adjust TV antenna",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(44.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = hint,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun RetroHorizontalChipsRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}
