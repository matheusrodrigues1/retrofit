package com.grupo.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


// Configura o banco de dados local Room registrando a entidade Livro, definindo a versão inicial (1) e desativando a exportação do esquema
@Database(
    entities = [Livro::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() { //abstrata porque o Room gera a implementação

    // Declara o metodo abstrato que fornece a instância do DAO (LivroDao) para execução das operações de persistência no banco de dados Room
    abstract fun livroDao(): LivroDao
    companion object { //utilizado para armazenar a instância única do banco de dados
        @Volatile private var INSTANCE: AppDatabase? = null
        // Singleton: garante uma única instância do banco, usando @Volatile e synchronized para controlar o acesso entre threads.


        // Retorna a instância Singleton thread-safe do banco de dados Room (AppDatabase), construindo-a com o arquivo "app.db" e registrando o callback de população inicial caso ainda não exista
        fun get(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext, //o contexto do app inteiro
                    AppDatabase::class.java,
                    "app.db" //o nome do arquivo SQLite no aparelho
                )
                    .addCallback(PopularBanco) //registra o código de dados iniciais
                    .build()
                    .also { INSTANCE = it }
            }

        // Dados iniciais, inseridos na criação do banco
        private val PopularBanco = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db) //roda uma única vez, quando o arquivo do banco é criado pela primeira vez
                db.execSQL("INSERT INTO livros (titulo, autor, ano) VALUES ('Dom Casmurro', 'Machado de Assis', 1899)")
                db.execSQL("INSERT INTO livros (titulo, autor, ano) VALUES ('Vidas Secas', 'Graciliano Ramos', 1938)")
                db.execSQL("INSERT INTO livros (titulo, autor, ano) VALUES ('O Cortiço', 'Aluísio Azevedo', 1890)")
                db.execSQL("INSERT INTO livros (titulo, autor, ano) VALUES ('Capitães da Areia', 'Jorge Amado', 1937)")
                db.execSQL("INSERT INTO livros (titulo, autor, ano) VALUES ('Grande Sertão: Veredas', 'Guimarães Rosa', 1956)")
            }
        }
    }
}
