package com.grupo.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

// TELA 2: dados de uma API externa (Retrofit)
@Composable
fun AlbunsScreen(vm: AlbunsViewModel = viewModel()) { //obtém o ViewModel.
    val state by vm.state.collectAsState() //o estado disponibilizado pelo AlbunsViewModel
    val total = (state as? AlbunsState.Sucesso)?.albuns?.size // Retorna a quantidade de álbuns se o estado atual for Sucesso, ou null caso contrário

    Column(Modifier.fillMaxSize()) {

        //reutilização de componentes
        ScreenHeader(
            titulo = "Álbuns",
            subtitulo = "Dados da API · JSONPlaceholder",
            cores = listOf(Color(0xFF0D9488), Color(0xFF2563EB)),
            contador = total?.let { "$it álbuns" } // Formata o texto (ex: "100 álbuns") se o total não for nulo; caso contrário, mantém null
        )

        when (val s = state) {
            is AlbunsState.Carregando -> Box( //Se os dados ainda estão sendo buscados
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator() //Mostra o indicador circular de carregamento
                    Spacer(Modifier.height(16.dp)) //espaçamento 16 dp de altura
                    Text(
                        text = "Buscando álbuns na API...",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Se ocorrer uma falha na requisição
            is AlbunsState.Erro -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("⚠️", fontSize = 44.sp) //1 exibe
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "Não foi possível carregar",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Verifique sua conexão com a internet e tente novamente.",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = s.mensagem, //contém a mensagem de erro disponibilizada pelo ViewModel
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    //Botão de tentar novamente
                    Spacer(Modifier.height(20.dp))
                    Button(onClick = { vm.carregar() }) {
                        Text("Tentar novamente")
                    }
                }
            }


            //Quando a API retorna os dados corretamente
            is AlbunsState.Sucesso -> LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                //apresenta uma lista de álbuns
                itemsIndexed(s.albuns, key = { _, album -> album.id }) { indice, album ->
                    val cor = CoresItens[indice % CoresItens.size]
                    CartaoItem {
                        Avatar(album.id.toString(), cor) //o avatar apresenta o número identificador do álbum
                        Column(
                            Modifier
                                .weight(1f)
                                .padding(start = 14.dp)
                        ) {
                            Text(
                                text = album.title.replaceFirstChar { it.uppercase() }, //Transforma a primeira letra do título em maiúscula
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(Modifier.height(6.dp))
                            Etiqueta("Usuário ${album.userId}", cor) //Identificação do usuário
                        }
                    }
                }
            }
        }
    }
}