package br.com.alexf.aluraflix.model

import br.com.alexf.aluraflix.R

enum class Categoria(
    val texto: String? = null,
    val corText: Int? = null,
    val corFundo: Int? = null
) {
    SEM_CATEGORIA,
    PROGRAMACAO(
        "Programação",
        R.color.black,
        R.color.categoria_programacao
    ),
    MOBILE(
        "Mobile",
        R.color.white,
        R.color.categoria_mobile
    ),
    FRONTEND(
        "Front-End",
        R.color.black,
        R.color.categoria_front_end
    ),
    DATA_SCIENCE(
        "Data Science",
        R.color.white,
        R.color.categoria_data_science
    )
}