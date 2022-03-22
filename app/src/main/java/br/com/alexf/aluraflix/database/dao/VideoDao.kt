package br.com.alexf.aluraflix.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.alexf.aluraflix.database.entity.VideoEntity
import br.com.alexf.aluraflix.model.Video
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {

    @Query("SELECT * FROM video")
    fun buscaTodos(): Flow<List<VideoEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun salva(video: VideoEntity)

}