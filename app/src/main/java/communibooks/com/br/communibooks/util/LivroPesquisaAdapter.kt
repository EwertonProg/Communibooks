package communibooks.com.br.communibooks.util

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import communibooks.com.br.communibooks.model.Livro
import kotlinx.android.synthetic.main.livro_item.view.*

class LivroPesquisaAdapter(
    val lista: List<Livro>,
    val contexto: Context,
    val layout: Int,
    val listner: (Livro) -> Unit) : RecyclerView.Adapter<LivroPesquisaAdapter.LivroPesquisaHolder>() {

    inner class LivroPesquisaHolder(livroPesquisaItemView: View) : RecyclerView.ViewHolder(livroPesquisaItemView) {
        val imagemLivro = itemView.img_livro_pesquisa
        val nomeLivro = itemView.tv_nome_livro_pesquisa
        val categoriaLivro = itemView.tv_categoria_livro_pesquisa
        val descicaoLivro = itemView.tv_descricao_livro_pesquisa
        val situacaoLivro = itemView.tv_situacao_livro_pesquisa
        val nomeUsuarioLivro = itemView.tv_nome_usuario_livro_pesquisa

        fun onClick(livro: Livro) = with(itemView) {
            setOnClickListener {
                listner(livro)
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LivroPesquisaHolder {
        val view: View = LayoutInflater.from(contexto).inflate(layout, p0, false)
        return LivroPesquisaHolder(view)
    }


    override fun onBindViewHolder(holder: LivroPesquisaHolder, posicao: Int) {
        holder.imagemLivro.background = lista[posicao].imagem
        holder.nomeLivro.text = lista[posicao].nome
        holder.categoriaLivro.text = lista[posicao].categoria.nome
        holder.descicaoLivro.text = lista[posicao].descrição
        holder.situacaoLivro.text = lista[posicao].situacao.name
        holder.nomeUsuarioLivro.text = lista[posicao].usuario.nome
    }

    override fun getItemCount() = lista.size
}