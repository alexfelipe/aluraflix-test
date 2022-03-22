package br.com.alexf.aluraflix.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import br.com.alexf.aluraflix.dao.VideoDao
import br.com.alexf.aluraflix.database.AppDatabase
import br.com.alexf.aluraflix.database.entity.VideoEntity
import br.com.alexf.aluraflix.databinding.ActivityFormVideoBinding
import br.com.alexf.aluraflix.extension.carregaImagemDoYoutube
import br.com.alexf.aluraflix.model.Categoria
import br.com.alexf.aluraflix.model.Video
import coil.load
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

        val categorias = Categoria.values()
            .mapNotNull {
                it.texto
            }
        val campoCategorias = binding.activityFormVideoCategoria
        campoCategorias.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                categorias
            )
        )
        campoCategorias.setOnFocusChangeListener { _, _ ->
            val id = binding.activityFormVideoId.editText?.text.toString()
                .removePrefix("https://www.youtube.com/watch?v=")
            binding.imageView.carregaImagemDoYoutube(id)
            binding.imageView.visibility = VISIBLE
        }

        binding.button.setOnClickListener {
            val id = binding.activityFormVideoId.editText?.text.toString()
                .removePrefix("https://www.youtube.com/watch?v=")
            //TODO colocar try catch
            val value = binding.activityFormVideoCategoria.text.toString()
            val categoria = Categoria.values().first {
                it.texto == value
            }
            MainScope().launch {
                dao.salva(VideoEntity(id, categoria))
                finish()
            }

        }
    }
}