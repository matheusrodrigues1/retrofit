package com.grupo.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "livros") //transforma a classe em tabela
data class Livro( //guarda os dados
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,  //o id é a chave primária e o banco gera o número, por isso o valor padrão 0

    val titulo: String, //colunas
    val autor: String,
    val ano: Int
)
