package br.com.alexf.aluraflix.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.alexf.aluraflix.R
import br.com.alexf.aluraflix.databinding.CategoriaVideoBinding
import br.com.alexf.aluraflix.model.Categoria

class CategoriaVideoAdapter(
    private val context: Context,
    private val categoria: Categoria
) : RecyclerView.Adapter<CategoriaVideoAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: CategoriaVideoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun vincula() {
            val campoTituloCategoria = binding.categoriaVideoTexto
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
        holder.vincula()
    }

    override fun getItemCount() = 1

    override fun getItemViewType(position: Int): Int =
        R.layout.categoria_video

}
