package br.com.alexf.aluraflix.model

data class Video(
    val id: String,
    val categoria: Categoria = Categoria.SEM_CATEGORIA
)
