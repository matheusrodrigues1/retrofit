package com.grupo.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.grupo.app.data.AppDatabase
import com.grupo.app.data.Livro
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class LivrosViewModel(app: Application) : AndroidViewModel(app) {

    // Flow do Room (executado em coroutine, fora da thread principal)
    val livros: StateFlow<List<Livro>> = AppDatabase.get(app)
        .livroDao()
        .listar()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}
