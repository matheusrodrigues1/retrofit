package com.grupo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.grupo.app.ui.AlbunsScreen
import com.grupo.app.ui.LivrosScreen
import com.grupo.app.ui.theme.AppTheme

class MainActivity : ComponentActivity() { //ComponentActivity é uma classe base suporte integração com Jetpack Compose.

    override fun onCreate(savedInstanceState: Bundle?) { // Chamado na criação da Activity; recebe o estado anterior salvo (se houver)

        /**
         * Configura a exibição edge-to-edge com barra de status transparente.
         * Nota: [SystemBarStyle.dark] define o contraste para fundo escuro (ícones claros), não o tema do app.
         */
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
        )
        super.onCreate(savedInstanceState)

        /**
         * Inicializa a interface da Activity com Jetpack Compose dentro do [AppTheme],
         * centralizando o acesso a cores, tipografia e formas via [MaterialTheme].
         */
        setContent {
            AppTheme {

                /**
                 * Configura a estrutura base da tela via [Scaffold] com barra inferior,
                 * aplicando as cores do [MaterialTheme] e zerando os [WindowInsets] para controle manual das áreas do sistema.
                 */
                var aba by rememberSaveable { mutableIntStateOf(0) }
                val corItem = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.primary
                )


                /**
                 * Estrutura o layout base da tela utilizando [Scaffold] com navegação inferior.
                 *
                 * Configura a cor de fundo a partir do esquema de cores do tema ([MaterialTheme.colorScheme])
                 * e zera os [WindowInsets] padrão para delegar o controle da barra de status aos cabeçalhos
                 * individuais de cada tela. A [bottomBar] instancia uma [NavigationBar] que gerencia a
                 * alternância de estado da variável `aba` entre as seções "Livros" (índice 0) e "Álbuns" (índice 1).
                 */
                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    // O cabeçalho de cada tela cuida da barra de status
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                    bottomBar = {
                        NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                            NavigationBarItem(
                                selected = aba == 0,
                                onClick = { aba = 0 },
                                icon = { Text("📚", fontSize = 22.sp) },
                                label = { Text("Livros") },
                                colors = corItem
                            )
                            NavigationBarItem(
                                selected = aba == 1,
                                onClick = { aba = 1 },
                                icon = { Text("🎵", fontSize = 22.sp) },
                                label = { Text("Álbuns") },
                                colors = corItem
                            )
                        }
                    }
                ) { padding -> // Renderiza a tela correspondente à aba selecionada, respeitando os espaçamentos internos do Scaffold
                    Box(Modifier.padding(padding).fillMaxSize()) {
                        if (aba == 0) LivrosScreen() else AlbunsScreen()
                    }
                }
            }
        }
    }
}