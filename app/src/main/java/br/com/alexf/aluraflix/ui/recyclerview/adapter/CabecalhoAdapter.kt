package br.com.alexf.aluraflix.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.recyclerview.widget.RecyclerView
import br.com.alexf.aluraflix.databinding.CabecalhoBinding
import br.com.alexf.aluraflix.extension.carregaImagemDoYoutube

class CabecalhoAdapter(
    private val context: Context
) : RecyclerView.Adapter<CabecalhoAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: CabecalhoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun vincula() {
            binding.cabecalhoTitulo.text = LoremIpsum(10).values.first()
            binding.cabecalhoImagem.carregaImagemDoYoutube("pcnfjJG3jY4")
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

}