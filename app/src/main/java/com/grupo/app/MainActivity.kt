package com.grupo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.grupo.app.ui.AlbunsScreen
import com.grupo.app.ui.LivrosScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var aba by rememberSaveable { mutableIntStateOf(0) }
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = aba == 0,
                                onClick = { aba = 0 },
                                icon = { Text("📚") },
                                label = { Text("Livros") }
                            )
                            NavigationBarItem(
                                selected = aba == 1,
                                onClick = { aba = 1 },
                                icon = { Text("🎵") },
                                label = { Text("Álbuns") }
                            )
                        }
                    }
                ) { padding ->
                    // Troque por navigation-compose quando o app crescer
                    Box(Modifier.padding(padding)) {
                        if (aba == 0) LivrosScreen() else AlbunsScreen()
                    }
                }
            }
        }
    }
}
