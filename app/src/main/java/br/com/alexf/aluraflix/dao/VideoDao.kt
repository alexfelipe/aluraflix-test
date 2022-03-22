package br.com.alexf.aluraflix.dao

import br.com.alexf.aluraflix.model.Categoria
import br.com.alexf.aluraflix.model.Video

class VideoDao {
    fun salva(video: Video) {
        videos.add(video)
    }

    val buscaTodos = videos

    companion object {
        private val videos = mutableListOf(
            Video(
                "qYhixp5qxTc",
                Categoria.DATA_SCIENCE
            ),
            Video(
                "GUanHEGlje4",
                Categoria.PROGRAMACAO
            ),
        )
    }

}