package com.grupo.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Cores usadas de forma alternada nos itens das listas
val CoresItens = listOf(
    Color(0xFF6366F1),
    Color(0xFF0D9488),
    Color(0xFFF59E0B),
    Color(0xFFEC4899),
    Color(0xFF3B82F6)
)

// Cabeçalho com degradê, título, subtítulo e um contador opcional
@Composable
fun ScreenHeader(
    titulo: String,
    subtitulo: String,
    cores: List<Color>,
    contador: String? = null
) {
    Row( //Organiza horizontalmente os elementos do cabeçalho ocupando toda a largura disponível da tela
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.linearGradient(cores),
                RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
            )
            .statusBarsPadding()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) { //organiza o título e o subtítulo verticalmente
            Text(
                text = titulo,
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitulo,
                color = Color.White.copy(alpha = 0.85f),
                style = MaterialTheme.typography.bodyMedium
            )
        }


        // o contador só será exibido se contador não for nulo
        if (contador != null) {
            Surface(
                shape = RoundedCornerShape(50), //formato bastante arredondado
                color = Color.White.copy(alpha = 0.2f)
            ) {
                Text( //Exibe o texto do contador. "5 livros"
                    text = contador,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                )
            }
        }
    }
}

// Quadrado arredondado colorido com um texto curto (inicial ou número)
@Composable
fun Avatar(texto: String, cor: Color) {

    // Define um contêiner quadrado de 48.dp com cantos arredondados e fundo translúcido (15% de opacidade), centralizando seu conteúdo
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(cor.copy(alpha = 0.15f), RoundedCornerShape(14.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text( //// Exibe o identificador textual do avatar em negrito utilizando a cor sólida principal para contrastar com o fundo translúcido
            text = texto,
            color = cor,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

/**
 * Renderiza uma etiqueta visual (badge/chip) em formato de pílula com fundo translúcido.
 *
 * Aplica a cor base com opacidade reduzida (14%) na superfície arredondada ([Surface])
 * enquanto mantém a cor sólida no texto para assegurar legibilidade e contraste.
 *
 * @param texto Rótulo textual a ser exibido no interior da etiqueta.
 * @param cor Cor temática principal utilizada tanto para o texto quanto para o fundo translúcido.
 */
@Composable
fun Etiqueta(texto: String, cor: Color) {
    Surface(
        shape = RoundedCornerShape(50),
        color = cor.copy(alpha = 0.14f)
    ) {
        Text(
            text = texto,
            color = cor,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

/**
 * Renderiza um cartão padronizado de largura total com cantos arredondados e elevação sutil.
 *
 * Organiza os elementos filhos horizontalmente dentro de uma [Row] com espaçamento interno
 * uniforme e alinhamento vertical centralizado, aplicando a cor de superfície do tema atual.
 *
 * @param content Bloco Composable com escopo de linha ([RowScope]) que define os elementos internos do cartão.
 */
@Composable
fun CartaoItem(content: @Composable RowScope.() -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}