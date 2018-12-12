package communibooks.com.br.communibooks.util

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import communibooks.com.br.communibooks.model.Categoria
import kotlinx.android.synthetic.main.livro_item.view.*

class LivroPesquisaAdapter(
    val lista: List<Categoria>,
    val contexto: Context,
    val layout: Int,
    val listner: (Categoria) -> Unit
) : RecyclerView.Adapter<LivroPesquisaAdapter.LivroPesquisaHolder>() {

    inner class LivroPesquisaHolder(livroPesquisaItemView: View) : RecyclerView.ViewHolder(livroPesquisaItemView) {
        val imagemLivro = itemView.img_livro_pesquisa
        val nomeLivro = itemView.tv_nome_livro_pesquisa
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LivroPesquisaHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: LivroPesquisaHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}