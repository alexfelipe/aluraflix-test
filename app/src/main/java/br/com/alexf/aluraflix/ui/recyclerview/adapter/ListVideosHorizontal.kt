package br.com.alexf.aluraflix.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alexf.aluraflix.databinding.VideoItemBinding
import br.com.alexf.aluraflix.extension.carregaImagemDoYoutube
import br.com.alexf.aluraflix.model.Video

class ListVideosHorizontal(
    private val context: Context,
    videos: List<Video>
) : RecyclerView.Adapter<ListVideosHorizontal.ViewHolder>() {

    private val videos = videos.toMutableList()

    class ViewHolder(private val binding: VideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun vincula(video: Video) {
            binding.videoItemThumbnail.carregaImagemDoYoutube(video.id)
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

}
