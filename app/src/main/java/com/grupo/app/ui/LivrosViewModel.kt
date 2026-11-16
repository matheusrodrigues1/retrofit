package com.grupo.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.grupo.app.data.AppDatabase
import com.grupo.app.data.Livro
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn


// AndroidViewModel responsável por gerenciar e expor os dados persistidos de livros à interface utilizando o contexto da aplicação
class LivrosViewModel(app: Application) : AndroidViewModel(app) {

    // Converte o fluxo reativo (Flow) do Room em um StateFlow vinculado ao viewModelScope, mantendo a assinatura ativa por 5 segundos após a saída dos observadores
    val livros: StateFlow<List<Livro>> = AppDatabase.get(app)
        .livroDao()
        .listar()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}
