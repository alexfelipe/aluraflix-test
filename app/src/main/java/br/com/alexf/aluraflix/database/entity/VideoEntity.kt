package br.com.alexf.aluraflix.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.alexf.aluraflix.model.Categoria
import br.com.alexf.aluraflix.model.Video

@Entity(tableName = "video")
data class VideoEntity(
    @PrimaryKey
    val id: String,
    val categoria: Categoria
)

fun VideoEntity.paraVideo(): Video {
    return Video(
        this.id,
        this.categoria
    )
}