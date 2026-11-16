package com.grupo.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo.app.data.Album
import com.grupo.app.data.Api
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


// Define a hierarquia fechada de estados possíveis da interface de álbuns (carregamento, sucesso ou erro)
sealed interface AlbunsState {
    data object Carregando : AlbunsState
    data class Sucesso(val albuns: List<Album>) : AlbunsState
    data class Erro(val mensagem: String) : AlbunsState
}

class AlbunsViewModel : ViewModel() {

    // Encapsula o estado mutável interno expondo-o como StateFlow imutável para a interface e dispara o carregamento inicial dos álbuns na inicialização do ViewModel
    private val _state = MutableStateFlow<AlbunsState>(AlbunsState.Carregando)
    val state: StateFlow<AlbunsState> = _state
    init { carregar() }


    // Define o estado como Carregando e inicia uma corrotina no viewModelScope para buscar os álbuns da API, emitindo Sucesso ou Erro conforme o resultado da requisição
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