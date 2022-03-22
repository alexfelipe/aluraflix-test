package br.com.alexf.aluraflix.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import br.com.alexf.aluraflix.dao.VideoDao
import br.com.alexf.aluraflix.database.AppDatabase
import br.com.alexf.aluraflix.databinding.ActivityListaVideosBinding
import br.com.alexf.aluraflix.model.Categoria
import br.com.alexf.aluraflix.model.Video
import br.com.alexf.aluraflix.ui.recyclerview.adapter.CabecalhoAdapter
import br.com.alexf.aluraflix.ui.recyclerview.adapter.ListaVideosAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ListaVideosActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListaVideosBinding.inflate(layoutInflater)
    }
    private val dao by lazy {
        AppDatabase.instancia(this).videoDao()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        configuraRecyclerView()
    }

    private fun configuraFab() {
        binding.activityListaVideosFabAdicionaVideo
            .setOnClickListener {
                Intent(this, FormVideoActivity::class.java)
                    .also {
                        startActivity(it)
                    }
            }
    }

    private fun configuraRecyclerView() {
        MainScope().launch {
            dao.buscaTodos().map {
                it.map { entity ->
                    Video(entity.id, entity.categoria)
                }
            }.collect { videos ->
                val videosPorCategoria =
                    videos.groupBy { it.categoria }
                        .toSortedMap()
                        .mapValues {
                            ListaVideosAdapter(
                                this@ListaVideosActivity,
                                it.key,
                                it.value,
                                videoClicado = { videoId ->
                                    abreVideo(videoId = videoId)
                                }
                            )
                        }
                val adapter = ConcatAdapter(
                    CabecalhoAdapter(
                        this@ListaVideosActivity,
                        videoId = "pcnfjJG3jY4",
                        botaoAssistirClicado = {
                            abreVideo(it)
                        }
                    ),
                )
                videosPorCategoria.forEach {
                    adapter.addAdapter(it.value)
                }
                binding.activityListaVideosRecyclerview.adapter = adapter
            }
        }
    }

    private fun abreVideo(videoId: String) {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.youtube.com/watch?v=$videoId")
        ).also {
            startActivity(it)
        }
    }

}