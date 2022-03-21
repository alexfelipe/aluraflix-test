package br.com.alexf.aluraflix.dao

import br.com.alexf.aluraflix.model.Video

class VideoDao {

    fun buscaTodos() = videos.toList()

    fun salva(video: Video) {
        videos.add(video)
    }

    companion object {
        private val videos = mutableListOf<Video>()
    }


}