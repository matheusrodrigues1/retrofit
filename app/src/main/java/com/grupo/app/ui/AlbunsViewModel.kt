package com.grupo.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo.app.data.Album
import com.grupo.app.data.Api
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface AlbunsState {
    data object Carregando : AlbunsState
    data class Sucesso(val albuns: List<Album>) : AlbunsState
    data class Erro(val mensagem: String) : AlbunsState
}

class AlbunsViewModel : ViewModel() {

    private val _state = MutableStateFlow<AlbunsState>(AlbunsState.Carregando)
    val state: StateFlow<AlbunsState> = _state

    init { carregar() }

    fun carregar() {
        _state.value = AlbunsState.Carregando
        viewModelScope.launch {
            _state.value = try {
                AlbunsState.Sucesso(Api.service.listarAlbuns())
            } catch (e: Exception) {
                AlbunsState.Erro(e.message ?: "Erro ao acessar a API")
            }
        }
    }
}
