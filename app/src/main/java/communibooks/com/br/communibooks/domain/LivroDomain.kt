package communibooks.com.br.communibooks.domain

import communibooks.com.br.communibooks.dao.LivroDao
import communibooks.com.br.communibooks.model.Livro
import communibooks.com.br.communibooks.model.Usuario
import java.util.*

object LivroDomain {

    fun cadastrar(livro: Livro) {

        LivroDao.add(livro)
    }

    fun pesquisarLivro(tipoDePesquisa: String, pesquisa: String? = "" , usuarioLogado: Usuario): ArrayList<Livro> {
        val livros: ArrayList<Livro> = ArrayList()

        when (tipoDePesquisa) {
            "porCategoria" -> LivroDao.getLivros().forEach { livro ->
                if (livro.categoria.nome == pesquisa) livros.add(livro)
            }

            "meusLivros" -> LivroDao.getLivros().forEach { livro ->
                if (livro.usuario == usuarioLogado) livros.add(livro)
            }

        }
        return livros
    }

}
