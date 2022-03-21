package br.com.alexf.aluraflix.ui.recyclerview.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.alexf.aluraflix.databinding.VideoItemBinding
import br.com.alexf.aluraflix.extension.carregaImagemDoYoutube
import br.com.alexf.aluraflix.model.Video

class ListaVideosAdapter(
    private val context: Context,
    videos: List<Video> = emptyList(),
    var onVideoClick: (id: String) -> Unit = {}
) : RecyclerView.Adapter<ListaVideosAdapter.ViewHolder>() {

    private val videos = videos.toMutableList()

    inner class ViewHolder(
        private val binding: VideoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var videoId: String? = null

        init {
            binding.card.setOnClickListener {
                videoId?.let {
                    onVideoClick(it)
                }
            }
        }

        fun vincula(video: Video) {
            videoId = video.id
            binding.videoItemThumbnail.carregaImagemDoYoutube(video.id)
            val campoCategoria = binding.videoItemCategoria

            val textoCategoria = video.categoria.texto
            video.categoria.corText?.let {
                campoCategoria
                    .setTextColor(ContextCompat.getColor(context, it))
            }
            video.categoria.corFundo?.let {
                campoCategoria.background
                    .setTint(ContextCompat.getColor(context, it))
            }

            campoCategoria.text = textoCategoria

            if (textoCategoria != null && textoCategoria.isBlank()) {
                campoCategoria.visibility = GONE
            } else {
                campoCategoria.visibility = VISIBLE
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(
            VideoItemBinding.inflate(
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
        holder.vincula(videos[position])
    }

    override fun getItemCount() = videos.size

    fun atualiza(videoIds: List<Video>) {
        notifyItemRangeRemoved(0, videos.size)
        videos.clear()
        videos.addAll(videoIds)
        notifyItemRangeInserted(0, videos.size)
    }

}
