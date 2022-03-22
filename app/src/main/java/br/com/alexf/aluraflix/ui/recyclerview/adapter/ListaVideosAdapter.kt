package br.com.alexf.aluraflix.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.alexf.aluraflix.R
import br.com.alexf.aluraflix.databinding.VideosPorCategoriaBinding
import br.com.alexf.aluraflix.model.Categoria
import br.com.alexf.aluraflix.model.Video

class ListaVideosAdapter(
    private val context: Context,
    private val categoria: Categoria,
    videos: List<Video> = emptyList(),
    var videoClicado: (videoId: String) -> Unit = {}
) : RecyclerView.Adapter<ListaVideosAdapter.ViewHolder>() {

    private val videos = videos.toMutableList()

    inner class ViewHolder(
        private val binding: VideosPorCategoriaBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun vincula(videos: List<Video>) {
            configuraCampoCategoria()
            configuraListaVideosAdapter(videos)
        }

        private fun configuraListaVideosAdapter(videos: List<Video>) {
            binding.videos.adapter = ListaVideosHorizontalAdapter(
                context,
                videos,
                videoClicado
            )
        }

        private fun configuraCampoCategoria() {
            val campoTituloCategoria = binding.categoriaVideoTitulo
            campoTituloCategoria.text = categoria.texto
            campoTituloCategoria.setTextColor(
                ContextCompat.getColor(
                    context,
                    categoria.corTexto
                )
            )
            campoTituloCategoria.background.setTint(
                ContextCompat.getColor(
                    context,
                    categoria.corFundo
                )
            )
        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(
            VideosPorCategoriaBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.vincula(videos)
    }

    override fun getItemCount() = 1

    override fun getItemViewType(position: Int): Int =
        R.layout.activity_main

}
