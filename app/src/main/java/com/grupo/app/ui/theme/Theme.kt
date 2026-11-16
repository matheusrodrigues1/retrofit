package com.grupo.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Define a paleta de cores do Material 3 aplicada quando o tema claro está ativo
private val EsquemaClaro = lightColorScheme(
    primary = Color(0xFF4F46E5),
    onPrimary = Color.White,      // Cores prefixadas com 'on' (ex: onPrimary) definem a cor de textos e ícones exibidos sobre suas respectivas superfícies base
    primaryContainer = Color(0xFFE0E7FF),
    onPrimaryContainer = Color(0xFF1E1B4B),
    secondary = Color(0xFF0D9488),
    onSecondary = Color.White,
    background = Color(0xFFF1F5F9),
    onBackground = Color(0xFF0F172A),
    surface = Color.White,
    onSurface = Color(0xFF0F172A),
    surfaceVariant = Color(0xFFE2E8F0),
    onSurfaceVariant = Color(0xFF64748B),
    error = Color(0xFFDC2626)
)


// Define a paleta de cores do Material 3 aplicada quando o tema escuro está ativo, garantindo contraste adequado em fundos escuros
private val EsquemaEscuro = darkColorScheme(
    primary = Color(0xFF818CF8),
    onPrimary = Color(0xFF1E1B4B),
    primaryContainer = Color(0xFF312E81),
    onPrimaryContainer = Color(0xFFE0E7FF),
    secondary = Color(0xFF2DD4BF),
    onSecondary = Color(0xFF042F2E),
    background = Color(0xFF0F172A),
    onBackground = Color(0xFFF1F5F9),
    surface = Color(0xFF1E293B),
    onSurface = Color(0xFFF1F5F9),
    surfaceVariant = Color(0xFF334155),
    onSurfaceVariant = Color(0xFF94A3B8),
    error = Color(0xFFF87171)
)


/**
 * Configura e aplica o [MaterialTheme] à hierarquia de interface passada em [content].
 *
 * Seleciona dinamicamente entre [EsquemaEscuro] e [EsquemaClaro] com base no parâmetro [darkTheme],
 * que por padrão detecta a configuração de tema do sistema operacional via [isSystemInDarkTheme].
 *
 * @param darkTheme Define se o esquema de cores escuro deve ser aplicado.
 * @param content Bloco Composable que herdará as definições do tema.
 */
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) EsquemaEscuro else EsquemaClaro,
        content = content
    )
}