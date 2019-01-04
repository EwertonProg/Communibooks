package communibooks.com.br.communibooks.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import communibooks.com.br.communibooks.R
import communibooks.com.br.communibooks.dao.UsuarioDao
import communibooks.com.br.communibooks.domain.LivroDomain
import communibooks.com.br.communibooks.model.Livro
import communibooks.com.br.communibooks.model.Usuario
import communibooks.com.br.communibooks.util.LivroPesquisaAdapter
import kotlinx.android.synthetic.main.activity_lista_livros_pesquisa.*
import kotlinx.android.synthetic.main.app_bar_tela_principal.*

class ListaLivrosPesquisaActivity : AppCompatActivity() {
    private lateinit var usuarioLogado: Usuario
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_livros_pesquisa)
        setSupportActionBar(toolbar)
        // Corrigir a seguinte linha quando o login voltar a ser a tela inicial
        usuarioLogado =
                if (intent.getStringExtra("usuarioLogado").isNullOrEmpty() || UsuarioDao.getUsuarios().isEmpty()) Usuario(
                    "Ewerton",
                    "Luis",
                    "e.luis0990@hotmail.com",
                    "OrionCaos",
                    "98762209",
                    "1998+**"
                ) else UsuarioDao.findByNomeUsuario(intent.getStringExtra("usuarioLogado"))

        val tipoPesquisa = intent.getStringExtra("tipoPesquisa")
        val pesquisa = intent.getStringExtra("pesquisa")

        val livrosCategoria: ArrayList<Livro> = LivroDomain.pesquisarLivro(tipoPesquisa,pesquisa,usuarioLogado)


        recyclerView = recycler_livros_pesquisa
        viewManager = LinearLayoutManager(this)
        viewAdapter =
                LivroPesquisaAdapter(lista = livrosCategoria, contexto = this, layout = R.layout.livro_item) { livro ->
                    val i: Intent = Intent(this, TODO("pagina do livro ainda não foi criada"))
                    i.putExtra("livroSelecionado", livro.id)
                    i.putExtra("usuarioLogado", usuarioLogado.nomeUsuario)

                    startActivity(i)
                }
        recyclerView.layoutManager = viewManager
        recyclerView.adapter = viewAdapter
    }

    override fun onBackPressed() {
        
    }
}
