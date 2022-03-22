package br.com.alexf.aluraflix.model

import br.com.alexf.aluraflix.database.entity.VideoEntity

data class Video(
    val id: String,
    val categoria: Categoria = Categoria.SEM_CATEGORIA
)

fun Video.toVideoEntity(): VideoEntity {
    return VideoEntity(this.id, this.categoria)
}

