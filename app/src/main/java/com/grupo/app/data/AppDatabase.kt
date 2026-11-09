package com.grupo.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Livro::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun livroDao(): LivroDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app.db"
                )
                    .addCallback(PopularBanco)
                    .build()
                    .also { INSTANCE = it }
            }

        // Dados iniciais, inseridos na criação do banco (a tela só lê, não cadastra)
        private val PopularBanco = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                db.execSQL("INSERT INTO livros (titulo, autor, ano) VALUES ('Dom Casmurro', 'Machado de Assis', 1899)")
                db.execSQL("INSERT INTO livros (titulo, autor, ano) VALUES ('Vidas Secas', 'Graciliano Ramos', 1938)")
                db.execSQL("INSERT INTO livros (titulo, autor, ano) VALUES ('O Cortiço', 'Aluísio Azevedo', 1890)")
                db.execSQL("INSERT INTO livros (titulo, autor, ano) VALUES ('Capitães da Areia', 'Jorge Amado', 1937)")
                db.execSQL("INSERT INTO livros (titulo, autor, ano) VALUES ('Grande Sertão: Veredas', 'Guimarães Rosa', 1956)")
            }
        }
    }
}
