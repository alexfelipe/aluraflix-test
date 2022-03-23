package br.com.alexf.aluraflix.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alexf.aluraflix.R
import br.com.alexf.aluraflix.databinding.VideoItemBinding
import br.com.alexf.aluraflix.extension.carregaImagemDoYoutube
import br.com.alexf.aluraflix.model.Video

class VideosAdapter(
    private val context: Context,
    videos: List<Video>,
    var videoClicado: (videoId: String) -> Unit = {},
) : RecyclerView.Adapter<VideosAdapter.ViewHolder>() {

    private val videos = videos.toMutableList()

    inner class ViewHolder(
        private val binding: VideoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var videoId: String? = null

        init {
            binding.card.setOnClickListener {
                videoId?.let { id ->
                    videoClicado(id)
                }
            }
        }

        fun vincula(video: Video) {
            binding.videoItemImagem.carregaImagemDoYoutube(video.id)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideosAdapter.ViewHolder {
        return ViewHolder(
            VideoItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: VideosAdapter.ViewHolder,
        position: Int
    ) {
        holder.vincula(videos[position])
    }

    override fun getItemCount() = videos.size

    override fun getItemViewType(position: Int):
            Int = R.layout.video_item

}
