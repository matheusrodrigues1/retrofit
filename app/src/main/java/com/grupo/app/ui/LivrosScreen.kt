package com.grupo.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

// TELA 1: dados do banco local (Room)
@Composable
fun LivrosScreen(vm: LivrosViewModel = viewModel()) {
    val livros by vm.livros.collectAsState()

    Column(Modifier.fillMaxSize()) {
        ScreenHeader(
            titulo = "Minha estante",
            subtitulo = "Dados do banco local · Room",
            cores = listOf(Color(0xFF4F46E5), Color(0xFF7C3AED)),
            contador = "${livros.size} livros"
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(livros, key = { _, livro -> livro.id }) { indice, livro ->
                val cor = CoresItens[indice % CoresItens.size]
                CartaoItem {
                    Avatar(livro.titulo.take(1).uppercase(), cor)
                    Column(
                        Modifier
                            .weight(1f)
                            .padding(horizontal = 14.dp)
                    ) {
                        Text(
                            text = livro.titulo,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = livro.autor,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Etiqueta(livro.ano.toString(), cor)
                }
            }
        }
    }
}