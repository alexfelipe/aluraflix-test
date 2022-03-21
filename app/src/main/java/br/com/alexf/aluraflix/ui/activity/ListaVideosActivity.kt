package br.com.alexf.aluraflix.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.alexf.aluraflix.dao.VideoDao
import br.com.alexf.aluraflix.databinding.ActivityListaVideosBinding
import br.com.alexf.aluraflix.model.Categoria
import br.com.alexf.aluraflix.model.Video
import br.com.alexf.aluraflix.ui.recyclerview.adapter.ListaVideosAdapter

class ListaVideosActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListaVideosBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        ListaVideosAdapter(this)
    }
    private val dao by lazy {
        VideoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()
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
        binding.activityListaVideosRecyclerview.adapter = adapter
        adapter.onVideoClick = { id ->
            abreVideo(id)
        }
    }

    override fun onResume() {
        super.onResume()
        val videos = dao.buscaTodos()
        adapter.atualiza(videos)
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