package communibooks.com.br.communibooks.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import communibooks.com.br.communibooks.R
import kotlinx.android.synthetic.main.app_bar_tela_principal.*

class ListaLivrosPesquisaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_livros_pesquisa)
        setSupportActionBar(toolbar)

    }
}
