package br.com.alexf.aluraflix.extension

import android.widget.ImageView
import coil.load

fun ImageView.carregaImagemDoYoutube(
    id: String
){
    this.load("https://img.youtube.com/vi/${id}/0.jpg")
}