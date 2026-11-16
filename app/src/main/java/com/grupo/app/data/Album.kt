package com.grupo.app.data


/**
 * Modelo de dados que representa um álbum e seus metadados principais.
 *
 * @property userId Identificador único do usuário proprietário ou autor do álbum.
 * @property id Identificador único do álbum.
 * @property title Título descritivo do álbum.
 */
data class Album(
    val userId: Int,
    val id: Int,
    val title: String
)
