package communibooks.com.br.communibooks.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import communibooks.com.br.communibooks.R
import communibooks.com.br.communibooks.dao.CategoriaDao
import communibooks.com.br.communibooks.dao.UsuarioDao
import communibooks.com.br.communibooks.model.Categoria
import communibooks.com.br.communibooks.model.Usuario
import communibooks.com.br.communibooks.util.CategoriaAdapter
import kotlinx.android.synthetic.main.activity_tela_principal.*
import kotlinx.android.synthetic.main.app_bar_tela_principal.*
import kotlinx.android.synthetic.main.nav_header_tela_principal.*
import com.google.gson.JsonParser
import com.google.gson.Gson
import java.io.*


class TelaPrincipal : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var usuarioLogado: Usuario
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var i: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_principal)
        setSupportActionBar(toolbar)


        lateinit var categoria: Categoria
        val br = BufferedReader( InputStreamReader(assets.open("categorias.json")))
        val gson = Gson()
        val jp = JsonParser()
        val je = jp.parse(br)
        val jobj = je.getAsJsonObject()
        val ja = jobj.getAsJsonArray("Categorias")

        for (i in 0 until ja.size() - 1) {
            categoria = gson.fromJson<Categoria>(ja.get(i), Categoria::class.java)
            Log.d("json",categoria.nome)
            Log.d("json",categoria.imagem)
            CategoriaDao.add(categoria)
        }
        viewManager = GridLayoutManager(this,2)
        recyclerView = findViewById(R.id.recycler_categoria_tela_principal)
        viewAdapter = CategoriaAdapter(contexto = this, lista = CategoriaDao.categorias, layout = R.layout.categoria_item){
            categoria ->  i.putExtra("categoria", categoria.nome)
        }
        recyclerView.adapter = viewAdapter
        recyclerView.layoutManager = viewManager

       // usuarioLogado = UsuarioDao.findByNomeUsuario(intent.getStringExtra("usuarioLogado"))



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
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.inicio_item -> {
                // Handle the camera action
            }
            R.id.perfil_item -> {

            }
            R.id.meus_livros_item -> {

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
