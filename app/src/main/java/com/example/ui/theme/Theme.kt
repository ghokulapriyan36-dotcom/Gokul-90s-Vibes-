package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Immutable
data class RetroPaletteExtras(
    val inkBorder: Color,
    val hardShadow: Color,
    val goldAccent: Color,
    val crtScreenBg: Color,
    val crtScanlineTint: Color,
    val arcadeIndigo: Color
)

val LocalRetroExtras = staticCompositionLocalOf {
    RetroPaletteExtras(
        inkBorder = ComicInk,
        hardShadow = ComicInk,
        goldAccent = CartridgeGold,
        crtScreenBg = ArcadeIndigo,
        crtScanlineTint = Color(0x18000000),
        arcadeIndigo = ArcadeIndigo
    )
}

private val LightColors = lightColorScheme(
    primary = DoordarshanSaffron,
    onPrimary = Color.White,
    primaryContainer = SaffronContainer,
    onPrimaryContainer = ComicInk,
    secondary = CartoonTeal,
    onSecondary = Color.White,
    secondaryContainer = TealContainer,
    onSecondaryContainer = ComicInk,
    tertiary = VhsMagenta,
    onTertiary = Color.White,
    tertiaryContainer = MagentaContainer,
    onTertiaryContainer = ComicInk,
    background = CreamCanvas,
    onBackground = ComicInk,
    surface = WarmPaper,
    onSurface = ComicInk,
    surfaceVariant = VintageSand,
    onSurfaceVariant = MutedInk,
    outline = ComicInk
)

private val DarkColors = darkColorScheme(
    primary = CrtOrange,
    onPrimary = Color(0xFF1A0900),
    primaryContainer = Color(0xFF4A2008),
    onPrimaryContainer = Color(0xFFFFDBCC),
    secondary = CrtTeal,
    onSecondary = Color(0xFF00201C),
    secondaryContainer = Color(0xFF0A3D36),
    onSecondaryContainer = Color(0xFFBDF5EC),
    tertiary = CrtMagenta,
    onTertiary = Color(0xFF2E0015),
    tertiaryContainer = Color(0xFF4F122D),
    onTertiaryContainer = Color(0xFFFFD6E5),
    background = CrtDeepBg,
    onBackground = CrtText,
    surface = CrtSurface,
    onSurface = CrtText,
    surfaceVariant = CrtElevated,
    onSurfaceVariant = CrtMutedText,
    outline = CrtBorder
)

val RetroShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(14.dp),
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(26.dp)
)

@Composable
fun NinetiesRewindTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    val extras = if (darkTheme) {
        RetroPaletteExtras(
            inkBorder = CrtBorder,
            hardShadow = Color(0xFF060711),
            goldAccent = CartridgeGold,
            crtScreenBg = Color(0xFF0A0C1D),
            crtScanlineTint = Color(0x28000000),
            arcadeIndigo = Color(0xFF292B68)
        )
    } else {
        RetroPaletteExtras(
            inkBorder = ComicInk,
            hardShadow = ComicInk,
            goldAccent = CartridgeGold,
            crtScreenBg = ArcadeIndigo,
            crtScanlineTint = Color(0x16000000),
            arcadeIndigo = ArcadeIndigo
        )
    }

    CompositionLocalProvider(LocalRetroExtras provides extras) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            shapes = RetroShapes,
            content = content
        )
    }
}
