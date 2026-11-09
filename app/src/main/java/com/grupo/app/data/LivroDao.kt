package com.grupo.app.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LivroDao {
    // Somente leitura: a tela apenas consome os dados (sem cadastro/atualização)
    @Query("SELECT * FROM livros ORDER BY titulo")
    fun listar(): Flow<List<Livro>>
}
