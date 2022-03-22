package br.com.alexf.aluraflix.extension

fun String.pegaVideoIdDoYoutube(): String {
    return this.removePrefix("https://www.youtube.com/watch?v=")
        .removePrefix("https://youtu.be/")
}