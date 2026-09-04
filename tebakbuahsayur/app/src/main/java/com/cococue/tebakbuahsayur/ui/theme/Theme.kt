package com.cococue.tebakbuahsayur.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val KidsPrimary = Color(0xFFFF6B6B)      // Coral Red
val KidsSecondary = Color(0xFF4ECDC4)    // Turquoise / Mint
val KidsTertiary = Color(0xFFFFD166)     // Sunny Yellow
val KidsBackground = Color(0xFFF7FFF7)   // Soft Off-White
val KidsSurface = Color(0xFFFFFFFF)      // Pure White
val KidsOnPrimary = Color(0xFFFFFFFF)
val KidsOnSurface = Color(0xFF2D3142)    // Dark Navy for high readability
val KidsCardBg = Color(0xFFE0FBFC)       // Light Blue Tint

private val LightColorScheme = lightColorScheme(
    primary = KidsPrimary,
    secondary = KidsSecondary,
    tertiary = KidsTertiary,
    background = KidsBackground,
    surface = KidsSurface,
    onPrimary = KidsOnPrimary,
    onSurface = KidsOnSurface,
    surfaceVariant = KidsCardBg
)

@Composable
fun TebakBuahSayurTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
