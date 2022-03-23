package br.com.alexf.aluraflix.model

import br.com.alexf.aluraflix.R

enum class Categoria(
    val texto: String,
    val corTexto: Int = R.color.black,
    val corFundo: Int = R.color.white
) {
    SEM_CATEGORIA("Outros"),
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
    ),
    ALEX(
        "Alex",
        R.color.white,
        R.color.categoria_data_science
    ),
    KAKO(
        "Kako",
        R.color.white,
        R.color.categoria_data_science
    )
}