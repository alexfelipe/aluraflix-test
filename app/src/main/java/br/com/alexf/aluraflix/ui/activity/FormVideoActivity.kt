package br.com.alexf.aluraflix.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import br.com.alexf.aluraflix.dao.VideoDao
import br.com.alexf.aluraflix.databinding.ActivityFormVideoBinding
import br.com.alexf.aluraflix.extension.carregaImagemDoYoutube
import br.com.alexf.aluraflix.model.Video
import coil.load

class FormVideoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormVideoBinding.inflate(layoutInflater)
    }
    private val dao by lazy {
        VideoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val categorias = arrayListOf(
            "Programação",
            "Mobile",
            "Front-End",
            "Back-End",
            "UX Design",
            "Gestão",
            "Marketing"
        )
        val campoCategorias = binding.activityFormVideoCategoria
        campoCategorias.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                categorias
            )
        )
        campoCategorias.setOnFocusChangeListener { view, b ->
            val id = binding.activityFormVideoId.editText?.text.toString()
                .removePrefix("https://www.youtube.com/watch?v=")
            Log.i("FormVideoActivity", "onCreate: $id")
            binding.imageView.carregaImagemDoYoutube(id)
        }

        binding.button.setOnClickListener {
            val id = binding.activityFormVideoId.editText?.text.toString()
                .removePrefix("https://www.youtube.com/watch?v=")
            val categoria = binding.activityFormVideoCategoria.text.toString()
            val video = Video(id, categoria)
            dao.salva(video)
            finish()
        }
    }
}