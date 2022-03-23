package br.com.alexf.aluraflix.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alexf.aluraflix.R
import br.com.alexf.aluraflix.databinding.VideosHorizontalBinding
import br.com.alexf.aluraflix.model.Video

class VideosHorizontalAdapter(
    private val context: Context,
    videos: List<Video>,
    var videoClicado: (videoId: String) -> Unit = {}
) : RecyclerView.Adapter<VideosHorizontalAdapter.ViewHolder>() {

    private val videos = videos.toMutableList()

    inner class ViewHolder(
        private val binding: VideosHorizontalBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun vincula() {
            binding.videos.adapter = VideosAdapter(
                context,
                videos,
                videoClicado
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(
            VideosHorizontalBinding.inflate(
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
        holder.vincula()
    }

    override fun getItemCount(): Int = 1

    override fun getItemViewType(position: Int): Int =
        R.layout.video_item

}
