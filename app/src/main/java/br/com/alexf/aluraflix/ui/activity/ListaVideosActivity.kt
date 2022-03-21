package br.com.alexf.aluraflix.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alexf.aluraflix.R
import br.com.alexf.aluraflix.databinding.ActivityListaVideosBinding
import br.com.alexf.aluraflix.model.Video
import br.com.alexf.aluraflix.ui.recyclerview.adapter.ListaVideosAdapter

class ListaVideosActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListaVideosBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        ListaVideosAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.activityListaVideosRecyclerview.adapter = adapter
        adapter.atualiza(listOf(
            Video("pcnfjJG3jY4"),
            Video("fmu1LQvZhms"),
            Video("GUanHEGlje4"),
            Video("HAdt523prB8"),
            Video("fiPfvylj6rk"),
            Video("_Mx64Rkv92c"),
        ))
        adapter.onVideoClick = { id ->
            abreVideo(id)
        }
    }

    private fun abreVideo(videoId: String) {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.youtube.com/watch?v=$videoId")
        )
            .also {
                startActivity(it)
            }
    }

}