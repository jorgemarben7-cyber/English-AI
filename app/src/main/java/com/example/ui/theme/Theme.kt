package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val SpaceDarkColorScheme = darkColorScheme(
    primary = ElectricCyan,
    onPrimary = Color(0xFF031E33),
    primaryContainer = Color(0xFF1E3A5F),
    onPrimaryContainer = Color(0xFFBAE6FD),
    secondary = CosmicViolet,
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFF3B1F64),
    onSecondaryContainer = Color(0xFFE9D5FF),
    tertiary = StarGold,
    onTertiary = Color(0xFF451A03),
    tertiaryContainer = Color(0xFF452B05),
    onTertiaryContainer = Color(0xFFFEF3C7),
    background = SpaceDeepNight,
    onBackground = TextWhite,
    surface = SpaceCardNavy,
    onSurface = TextWhite,
    surfaceVariant = SpaceCardElevated,
    onSurfaceVariant = TextMuted,
    outline = SpaceBorder,
    error = ErrorRose,
    onError = Color.White
)

val SpaceLightColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE0F2FE),
    onPrimaryContainer = Color(0xFF0369A1),
    secondary = Color(0xFF7C3AED),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFEDE9FE),
    onSecondaryContainer = Color(0xFF5B21B6),
    tertiary = Color(0xFFD97706),
    onTertiary = Color.White,
    background = LightBg,
    onBackground = LightText,
    surface = LightSurface,
    onSurface = LightText,
    surfaceVariant = LightSurfaceElevated,
    onSurfaceVariant = LightTextMuted,
    outline = Color(0xFFCBD5E1),
    error = ErrorRose,
    onError = Color.White
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) SpaceDarkColorScheme else SpaceLightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
