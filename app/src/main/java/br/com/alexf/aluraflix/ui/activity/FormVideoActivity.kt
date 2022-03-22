package br.com.alexf.aluraflix.ui.activity

import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.com.alexf.aluraflix.database.AppDatabase
import br.com.alexf.aluraflix.databinding.ActivityFormVideoBinding
import br.com.alexf.aluraflix.extension.carregaImagemDoYoutube
import br.com.alexf.aluraflix.extension.pegaVideoIdDoYoutube
import br.com.alexf.aluraflix.ui.model.Categoria
import br.com.alexf.aluraflix.ui.model.Video
import br.com.alexf.aluraflix.ui.model.toVideoEntity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class FormVideoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormVideoBinding.inflate(layoutInflater)
    }
    private val dao by lazy {
        AppDatabase.instancia(this).videoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraCampoCategoria()
        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        binding.activityFormBotaoSalvar.setOnClickListener {
            val videoId = binding.activityFormVideoId
                .editText?.text.toString().pegaVideoIdDoYoutube()
            val categoria: Categoria = try {
                buscaCategoria()
            } catch (e: Exception) {
                Categoria.SEM_CATEGORIA
            }
            val video = Video(videoId, categoria)
            salva(video)
        }
    }

    private fun salva(video: Video) {
        MainScope().launch {
            dao.salva(
                video.toVideoEntity()
            )
            finish()
        }
    }

    private fun buscaCategoria(): Categoria {
        val valorCategoria = binding
            .activityFormVideoCategoria.text.toString()
        return Categoria.values().first {
            it.texto == valorCategoria
        }
    }

    private fun configuraCampoCategoria() {
        val categorias = carregaCategorias()
        val campoCategorias = binding.activityFormVideoCategoria
        campoCategorias.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                categorias
            )
        )
        val imagemVideo = binding.imageView
        campoCategorias.setOnFocusChangeListener { _, _ ->
            val id = binding.activityFormVideoId.editText?.text.toString()
                .pegaVideoIdDoYoutube()
            imagemVideo.carregaImagemDoYoutube(id)
            imagemVideo.visibility = VISIBLE
        }
    }

    private fun carregaCategorias(): List<String> {
        val categorias = Categoria.values()
            .map {
                it.texto
            }
        return categorias
    }
}

