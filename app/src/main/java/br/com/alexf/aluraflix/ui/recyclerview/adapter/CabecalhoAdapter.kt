package br.com.alexf.aluraflix.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.recyclerview.widget.RecyclerView
import br.com.alexf.aluraflix.R
import br.com.alexf.aluraflix.databinding.CabecalhoBinding
import br.com.alexf.aluraflix.extension.carregaImagemDoYoutube

class CabecalhoAdapter(
    private val context: Context,
    private val videoId: String,
    var botaoAssistirClicado: (videoId: String) -> Unit = {}
) : RecyclerView.Adapter<CabecalhoAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: CabecalhoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.cabecalhoBotaoAssitir.setOnClickListener {
                botaoAssistirClicado(videoId)
            }
        }

        fun vincula() {
            binding.cabecalhoTitulo.text = LoremIpsum(10).values.first()
            binding.cabecalhoImagem.carregaImagemDoYoutube(videoId)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(
            CabecalhoBinding.inflate(
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

    override fun getItemCount() = 1

    override fun getItemViewType(position: Int): Int =
        R.layout.cabecalho

}