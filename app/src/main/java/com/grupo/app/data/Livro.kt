package com.grupo.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Tabela do integrante. Cada colega deve usar um nome de tabela diferente
// (ex.: "livros_maria", "filmes_joao") e sua própria entidade.
@Entity(tableName = "livros")
data class Livro(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val autor: String,
    val ano: Int
)
