package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = HRDarkPrimary,
    onPrimary = Color(0xFF002F6C),
    primaryContainer = HRDarkPrimaryContainer,
    onPrimaryContainer = Color(0xFFD1E4FF),
    secondary = HRSecondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF004B73),
    onSecondaryContainer = Color(0xFFC7E7FF),
    tertiary = HRTealAccent,
    background = HRDarkBackground,
    onBackground = HRDarkOnSurface,
    surface = HRDarkSurface,
    onSurface = HRDarkOnSurface,
    surfaceVariant = HRDarkSurfaceVariant,
    onSurfaceVariant = HRDarkOnSurfaceVariant,
    outline = Color(0xFF64748B),
    error = Color(0xFFFFB4AB)
)

private val LightColorScheme = lightColorScheme(
    primary = HRBluePrimary,
    onPrimary = Color.White,
    primaryContainer = HRBlueContainer,
    onPrimaryContainer = HROnBlueContainer,
    secondary = HRSecondary,
    onSecondary = Color.White,
    secondaryContainer = HRSecondaryContainer,
    onSecondaryContainer = Color(0xFF001F33),
    tertiary = HRTealAccent,
    background = HRBackground,
    onBackground = HROnSurface,
    surface = HRSurface,
    onSurface = HROnSurface,
    surfaceVariant = HRSurfaceVariant,
    onSurfaceVariant = HROnSurfaceVariant,
    outline = HROutline,
    error = HRErrorRed
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Set to false to preserve strict HR Computer Sikho brand identity
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
