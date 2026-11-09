package com.grupo.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

// TELA 2: dados de uma API externa (Retrofit)
@Composable
fun AlbunsScreen(vm: AlbunsViewModel = viewModel()) {
    val state by vm.state.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Álbuns (API)", style = MaterialTheme.typography.headlineSmall)

        when (val s = state) {
            is AlbunsState.Carregando -> CircularProgressIndicator(Modifier.padding(top = 24.dp))
            is AlbunsState.Erro -> {
                Text("Erro: ${s.mensagem}", Modifier.padding(top = 12.dp))
                Button(onClick = { vm.carregar() }, Modifier.padding(top = 8.dp)) {
                    Text("Tentar novamente")
                }
            }
            is AlbunsState.Sucesso -> LazyColumn(Modifier.padding(top = 12.dp)) {
                items(s.albuns, key = { it.id }) { album ->
                    Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                        Column(Modifier.padding(12.dp)) {
                            Text(album.title, style = MaterialTheme.typography.titleMedium)
                            Text("Álbum #${album.id} • usuário ${album.userId}", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
