package communibooks.com.br.communibooks.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import communibooks.com.br.communibooks.R
import communibooks.com.br.communibooks.dao.CategoriaDao
import communibooks.com.br.communibooks.model.Usuario
import communibooks.com.br.communibooks.util.CategoriaAdapter
import kotlinx.android.synthetic.main.activity_tela_principal.*
import kotlinx.android.synthetic.main.app_bar_tela_principal.*
import communibooks.com.br.communibooks.util.Util


class TelaPrincipal : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var usuarioLogado: Usuario
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_principal)
        setSupportActionBar(toolbar)
       // usuarioLogado = UsuarioDao.findByNomeUsuario(intent.getStringExtra("usuarioLogado"))

        Util.popularCategoria(assets,"https://categoriapop.000webhostapp.com/")

        viewManager = GridLayoutManager(this,2)
        recyclerView = findViewById(R.id.recycler_categoria_tela_principal)
        viewAdapter = CategoriaAdapter(contexto = this, lista = CategoriaDao.categorias, layout = R.layout.categoria_item){
            categoria ->  val i: Intent = Intent(this, ListaLivrosPesquisaActivity::class.java)
            i.putExtra("categoria", categoria.nome)
            i.putExtra("usuarioLogado", usuarioLogado.nomeUsuario)

            startActivity(i)
        }
        recyclerView.adapter = viewAdapter
        recyclerView.layoutManager = viewManager




        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.tela_principal, menu)
        //tv_nome_menu.setText(usuarioLogado.nome+" "+usuarioLogado.sobrenome)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        lateinit var i:Intent

        when (item.itemId) {
            R.id.inicio_item -> {
                // Handle the camera action
            }
            R.id.perfil_item -> {

            }
            R.id.meus_livros_item -> {
                i = Intent(this,CadastrarLivroActivity::class.java)
                i.putExtra("usuarioLogado", usuarioLogado.nomeUsuario)
                startActivity(i)
            }
            R.id.categorias_item -> {

            }
            R.id.his_transacoes_item -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
