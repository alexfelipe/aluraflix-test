package br.com.alexf.aluraflix.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import br.com.alexf.aluraflix.database.AppDatabase
import br.com.alexf.aluraflix.database.entity.VideoEntity
import br.com.alexf.aluraflix.database.entity.paraVideo
import br.com.alexf.aluraflix.databinding.ActivityMainBinding
import br.com.alexf.aluraflix.ui.model.Video
import br.com.alexf.aluraflix.ui.recyclerview.adapter.CabecalhoAdapter
import br.com.alexf.aluraflix.ui.recyclerview.adapter.ListaVideosAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val dao by lazy {
        AppDatabase.instancia(this).videoDao()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraFab()
        configuraRecyclerView()
    }

    private fun configuraFab() {
        binding.activityListaVideosFabAdicionaVideo
            .setOnClickListener {
                vaiParaFormVideo()
            }
    }

    private fun vaiParaFormVideo() {
        Intent(this, FormVideoActivity::class.java)
            .also {
                startActivity(it)
            }
    }

    private fun configuraRecyclerView() {
        MainScope().launch {
            dao.buscaTodos().map { entity ->
                entity.map(VideoEntity::paraVideo)
                    .sortedByDescending { video ->
                        video.categoria
                    }
            }.collect { videos ->
                configuraAdapters(videos)
            }
        }
    }

    private fun configuraAdapters(videos: List<Video>) {
        val adapter = ConcatAdapter()
        val cabecalhoAdapter = criaCabecalhoAdapter("pcnfjJG3jY4")
        adapter.addAdapter(cabecalhoAdapter)
        val videosPorCategoriaAdapter = criaVideosPorCategoriaAdapter(videos)
        videosPorCategoriaAdapter.forEach {
            adapter.addAdapter(it.value)
        }
        binding.activityListaVideosRecyclerview.adapter = adapter
    }

    private fun criaCabecalhoAdapter(
        videoId: String
    ) = CabecalhoAdapter(
        this,
        videoId = videoId,
        botaoAssistirClicado = {
            abreVideoNoYoutube(it)
        },
    )

    private fun criaVideosPorCategoriaAdapter(
        videos: List<Video>
    ) = videos.groupBy { it.categoria }
        .mapValues {
            val categoria = it.key
            val videosDaCategoria = it.value
            ListaVideosAdapter(
                this@MainActivity,
                categoria,
                videosDaCategoria,
                videoClicado = { videoId ->
                    abreVideoNoYoutube(videoId = videoId)
                }
            )
        }

    private fun abreVideoNoYoutube(videoId: String) {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.youtube.com/watch?v=$videoId")
        ).also {
            startActivity(it)
        }
    }

}