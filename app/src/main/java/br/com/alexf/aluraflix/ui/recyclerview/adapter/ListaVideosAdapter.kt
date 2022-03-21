package br.com.alexf.aluraflix.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.alexf.aluraflix.databinding.VideosPorCategoriaBinding
import br.com.alexf.aluraflix.model.Categoria
import br.com.alexf.aluraflix.model.Video

class ListaVideosAdapter(
    private val context: Context,
    private val categoria: Categoria = Categoria.SEM_CATEGORIA,
    videos: List<Video> = emptyList()
) : RecyclerView.Adapter<ListaVideosAdapter.ViewHolder>() {

    private val videos = videos.toMutableList()

    inner class ViewHolder(
        private val binding: VideosPorCategoriaBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun vincula(videos: List<Video>) {
            val campoTituloCategoria = binding.categoriaVideoTitulo

            campoTituloCategoria.text = categoria.texto

            categoria.corText?.let {
                campoTituloCategoria.setTextColor(ContextCompat.getColor(context, it))
            }
            categoria.corFundo?.let {
                campoTituloCategoria.background.setTint(ContextCompat.getColor(context, it))
            }

            binding.videos.adapter = ListVideosHorizontal(context, videos)
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

    fun atualiza(videoIds: List<Video>) {
        notifyItemRangeRemoved(0, videos.size)
        videos.clear()
        videos.addAll(videoIds)
        notifyItemRangeInserted(0, videos.size)
    }

}
