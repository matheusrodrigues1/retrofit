package com.grupo.app.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Define o Data Access Object (DAO) responsável pelas operações de consulta da entidade Livro, retornando um fluxo reativo (Flow) ordenado alfabeticamente pelo título
@Dao
interface LivroDao {
    @Query("SELECT * FROM livros ORDER BY titulo")
    fun listar(): Flow<List<Livro>> //um fluxo que emite a lista agora e emite de novo toda vez que a tabela mudar.
}
