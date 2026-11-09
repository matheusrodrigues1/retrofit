package com.grupo.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

// TELA 1: dados do banco local (Room)
@Composable
fun LivrosScreen(vm: LivrosViewModel = viewModel()) {
    val livros by vm.livros.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Livros (Room)", style = MaterialTheme.typography.headlineSmall)
        LazyColumn(Modifier.padding(top = 12.dp)) {
            items(livros, key = { it.id }) { livro ->
                Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(Modifier.padding(12.dp)) {
                        Text(livro.titulo, style = MaterialTheme.typography.titleMedium)
                        Text("${livro.autor} • ${livro.ano}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
