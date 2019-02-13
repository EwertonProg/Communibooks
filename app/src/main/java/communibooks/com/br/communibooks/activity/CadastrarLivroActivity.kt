package communibooks.com.br.communibooks.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Toast
import communibooks.com.br.communibooks.R
import communibooks.com.br.communibooks.dao.CategoriaDao
import communibooks.com.br.communibooks.dao.UsuarioDao
import communibooks.com.br.communibooks.domain.LivroDomain
import communibooks.com.br.communibooks.model.Livro
import communibooks.com.br.communibooks.model.Usuario
import communibooks.com.br.communibooks.util.Util
import kotlinx.android.synthetic.main.activity_cadastrar_livro.*
import kotlinx.android.synthetic.main.app_bar_cadastrar_livro.*
import kotlinx.android.synthetic.main.content_cadastrar_livro.*
import kotlinx.android.synthetic.main.nav_header_tela_principal.*


class CadastrarLivroActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var localImagem: Uri
    lateinit var pastaSelecionada: String
    lateinit var imageLivro: ImageButton
    lateinit var usuarioLogado: Usuario

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_livro)
        toolbar_cadastrar_livro.title = "Adicionar novo Livro"
        setSupportActionBar(toolbar_cadastrar_livro)
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

        Util.popularCategoria(assets, "https://categoriapop.000webhostapp.com/")
        val spinnerCategoria = sp_categoria_livro_cadastro
        val listaCategoria: ArrayList<String> = ArrayList()
        CategoriaDao.categorias.forEach { categoria -> listaCategoria.add(categoria.nome) }

        val arrayAdapterCategoria = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCategoria)
        spinnerCategoria.adapter = arrayAdapterCategoria

        val listaSituacao: ArrayList<String> = ArrayList()
        listaSituacao.add(Livro.Situacao.PARADOACAO.toString())
        listaSituacao.add(Livro.Situacao.PARATROCA.toString())

        val arrayAdapterSituacao = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaSituacao)
        sp_situacao_livro_cadastro.adapter = arrayAdapterSituacao

        imageLivro = imgbtn_imagem_livro_cadastro

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout_cadastrar_livro, toolbar_cadastrar_livro,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout_cadastrar_livro.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("pastaSelecionada", "entrou")
        if (resultCode == RESULT_OK) {
            if (data?.getData() != null) {
                localImagem = data.getData()
            } else {
                Log.d("pastaSelecionada ", "Came here its null !");
                Toast.makeText(getApplicationContext(), "Falha", Toast.LENGTH_SHORT).show()
            }

            if (requestCode == 10) {

                pastaSelecionada = localImagem.path
                val inputStream = getContentResolver().openInputStream(localImagem)
                val myImg = Drawable.createFromStream(inputStream, localImagem.toString())
                imageLivro.background = myImg

                Log.d("pastaSelecionada ", pastaSelecionada)

            }

        }
    }

    public fun addImagem(view: View) {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Selecione a imagem do livro"), 10)
    }

    fun cadastrarLivro(view: View) {
        if (Util.validarEditText(
                ed_descricao_livro_cadastro,
                ed_nome_livro_cadastro
            )
        ) {
            val livro: Livro = Livro()
            livro.nome = ed_nome_livro_cadastro.text.toString()
            livro.descricao = ed_descricao_livro_cadastro.text.toString()
            livro.categoria = CategoriaDao.findByName(sp_categoria_livro_cadastro.selectedItem as String)
            livro.imagem = imgbtn_imagem_livro_cadastro.background
            livro.usuario = usuarioLogado
            livro.situacao = Livro.Situacao.toEnum(sp_situacao_livro_cadastro.selectedItem as String)
            LivroDomain.cadastrar(livro)
            Toast.makeText(this, "Adicionado", Toast.LENGTH_SHORT).show()
            Log.d("enum", sp_categoria_livro_cadastro.selectedItem as String)
            Log.d("enum", sp_situacao_livro_cadastro.selectedItem as String)
            val intent = Intent(this, TelaPrincipal::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout_cadastrar_livro.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_cadastrar_livro.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.tela_principal, menu)
        tv_nome_menu.setText(usuarioLogado.nome + " " + usuarioLogado.sobrenome)
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
        lateinit var i: Intent

        when (item.itemId) {
            R.id.inicio_item -> {
                startActivity(Intent(this, TelaPrincipal::class.java))
            }

            R.id.perfil_item -> {

            }

            R.id.meus_livros_item -> {
                i = Intent(this, ListaLivrosPesquisaActivity::class.java)
                i.putExtra("usuarioLogado", usuarioLogado.nomeUsuario)
                i.putExtra("tipoPesquisa", "meusLivros")
                startActivity(i)
            }

            R.id.categorias_item -> {

            }
            R.id.his_transacoes_item -> {

            }

        }

        drawer_layout_cadastrar_livro.closeDrawer(GravityCompat.START)
        return true
    }
}
