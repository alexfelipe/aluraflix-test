package br.com.alexf.aluraflix.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ConcatAdapter
import br.com.alexf.aluraflix.dao.VideoDao
import br.com.alexf.aluraflix.databinding.ActivityListaVideosBinding
import br.com.alexf.aluraflix.model.Categoria
import br.com.alexf.aluraflix.model.Video
import br.com.alexf.aluraflix.ui.recyclerview.adapter.CabecalhoAdapter
import br.com.alexf.aluraflix.ui.recyclerview.adapter.ListaVideosAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ListaVideosActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListaVideosBinding.inflate(layoutInflater)
    }
    private val dao by lazy {
        VideoDao()
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
        val videos = dao.buscaTodos
        val videosPorCategoria =
            videos.groupBy { it.categoria }
                .mapValues {
                    ListaVideosAdapter(
                        this@ListaVideosActivity,
                        it.key,
                        it.value
                    )
                }
        val adapter = ConcatAdapter(
            CabecalhoAdapter(this@ListaVideosActivity),
            *videosPorCategoria.values.toTypedArray()
        )
        binding.activityListaVideosRecyclerview.adapter = adapter
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