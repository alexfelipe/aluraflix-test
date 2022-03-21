package br.com.alexf.aluraflix.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alexf.aluraflix.databinding.CategoriaVideoBinding
import br.com.alexf.aluraflix.model.Categoria

class CategoriaHeaderAdapter(
    private val context: Context,
    private val categoria: Categoria,
) : RecyclerView.Adapter<CategoriaHeaderAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: CategoriaVideoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun vincula(categoria: Categoria) {
            binding.categoriaVideoTexto.text = categoria.texto
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(
            CategoriaVideoBinding.inflate(
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
        holder.vincula(categoria)
    }

    override fun getItemCount() = 1
}