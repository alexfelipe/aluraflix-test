package br.com.alexf.aluraflix.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.alexf.aluraflix.database.dao.VideoDao
import br.com.alexf.aluraflix.database.entity.VideoEntity

private const val NOME_BANCO_DE_DADOS = "aluraflix.db"

@Database(
    version = 1,
    entities = [VideoEntity::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun videoDao(): VideoDao

    companion object {

        @Volatile
        private var db: AppDatabase? = null

        fun instancia(context: Context): AppDatabase {
            return db ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                NOME_BANCO_DE_DADOS
            ).build().also { db = it }
        }

    }

}