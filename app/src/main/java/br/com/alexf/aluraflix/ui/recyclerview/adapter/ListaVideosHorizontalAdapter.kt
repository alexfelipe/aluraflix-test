package br.com.alexf.aluraflix.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alexf.aluraflix.R
import br.com.alexf.aluraflix.databinding.VideoItemBinding
import br.com.alexf.aluraflix.extension.carregaImagemDoYoutube
import br.com.alexf.aluraflix.ui.model.Video

class ListaVideosHorizontalAdapter(
    private val context: Context,
    videos: List<Video>,
    var videoClicado: (videoId: String) -> Unit
) : RecyclerView.Adapter<ListaVideosHorizontalAdapter.ViewHolder>() {

    private val videos = videos.toMutableList()

    inner class ViewHolder(private val binding: VideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var videoId: String? = null

        init {
            binding.card.setOnClickListener {
                videoId?.let {
                    videoClicado(it)
                }
            }
        }

        fun vincula(video: Video) {
            videoId = video.id
            binding.videoItemImagem
                .carregaImagemDoYoutube(video.id)
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

    override fun getItemCount(): Int = videos.size

    override fun getItemViewType(position: Int): Int =
        R.layout.video_item

}
