package communibooks.com.br.communibooks.util

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import communibooks.com.br.communibooks.model.Categoria
import kotlinx.android.synthetic.main.categoria_item.view.*

class CategoriaAdapter(
    val lista: List<Categoria>,
    val contexto: Context,
    val layout: Int,
    val listner: (Categoria) -> Unit
) : RecyclerView.Adapter<CategoriaAdapter.CategoriaHolder>() {

    inner class CategoriaHolder(categoriaItemView: View) : RecyclerView.ViewHolder(categoriaItemView) {
        val nomeCategoria = itemView.tv_nome_categoria_recycle
        val iconeCategoria = itemView.img_icone_categoria_recycle

        fun onClick(categoria: Categoria) = with(itemView) {
            setOnClickListener {
                listner(categoria)
            }
        }
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CategoriaAdapter.CategoriaHolder {
        val view: View = LayoutInflater.from(contexto).inflate(layout, p0, false)
        return CategoriaHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriaHolder, position: Int) {
        holder.nomeCategoria.text = lista[position].nome
        Picasso.get().load(lista[position].imagem).resizeDimen(150,150).into(holder.iconeCategoria)
    }

    override fun getItemCount() = lista.size
}