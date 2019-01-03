package communibooks.com.br.communibooks.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Toast
import communibooks.com.br.communibooks.R
import communibooks.com.br.communibooks.dao.CategoriaDao
import communibooks.com.br.communibooks.dao.LivroDao
import communibooks.com.br.communibooks.model.Livro
import communibooks.com.br.communibooks.model.Usuario
import communibooks.com.br.communibooks.util.Util
import kotlinx.android.synthetic.main.activity_cadastrar_livro.*
import kotlinx.android.synthetic.main.app_bar_tela_principal.*


class CadastrarLivroActivity : AppCompatActivity() {
    lateinit var localImagem: Uri
    lateinit var pastaSelecionada: String
    lateinit var imageLivro: ImageButton
    lateinit var usuarioLogado: Usuario

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_livro)
        setSupportActionBar(toolbar)
        // usuarioLogado = UsuarioDao.findByNomeUsuario(intent.getStringExtra("usuarioLogado"))
        Util.popularCategoria(assets, "https://categoriapop.000webhostapp.com/")
        val spinnerCategoria = sp_categoria_livro_cadastro
        val listaCategoria: ArrayList<String> = ArrayList()
        CategoriaDao.categorias.forEach { categoria -> listaCategoria.add(categoria.nome) }

        val arrayAdapterCategoria = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCategoria)
        spinnerCategoria.adapter = arrayAdapterCategoria

        val listaSituacao: ArrayList<String> = ArrayList()
        listaSituacao.add(Livro.Situacao.PARADOACAO.name)
        listaSituacao.add(Livro.Situacao.PARATROCA.name)

        val arrayAdapterSituacao = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaSituacao)
        sp_situacao_livro_cadastro.adapter = arrayAdapterSituacao

        imageLivro = imgbtn_imagem_livro_cadastro

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
                val myImg = Drawable.createFromStream(inputStream, localImagem.toString() )
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

    public fun cadastrarLivro(view: View) {
        if (Util.validarEditText(
                ed_descricao_livro_cadastro,
                ed_nome_livro_cadastro
            )
        ) {
            val livro: Livro = Livro()
            livro.nome = ed_nome_livro_cadastro.text.toString()
            livro.descrição = ed_descricao_livro_cadastro.text.toString()
            livro.categoria = CategoriaDao.findByName(sp_categoria_livro_cadastro.selectedItem as String)
            livro.imagem = imgbtn_imagem_livro_cadastro.background
            TODO("Adicionar o usuario logado ao usuario dono do livro")
            livro.usuario = Usuario("Ewerton", "Luis", "e.luis0990@hotmail.com", "OrionCaos", "98762209", "1998+**")

            livro.situacao = Livro.Situacao.valueOf(sp_situacao_livro_cadastro.selectedItem as String)
            LivroDao.add(livro)
            Toast.makeText(this, "Adicionado", Toast.LENGTH_SHORT).show()
            Log.d("enum", sp_categoria_livro_cadastro.selectedItem as String)
            Log.d("enum", sp_situacao_livro_cadastro.selectedItem as String)
            val intent = Intent(this,TelaPrincipal::class.java)
            startActivity(intent)
        }
    }
}
