package communibooks.com.br.communibooks.activity

import android.annotation.SuppressLint
import android.content.Intent
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
        var lista: ArrayList<String> = ArrayList()
        CategoriaDao.categorias.forEach { categoria -> lista.add(categoria.nome) }
        val CategoriaSpinnerAdapter = ArrayAdapter<String>(this, R.id.sp_categoria_livro_cadastro, lista)
        sp_categoria_livro_cadastro.adapter = CategoriaSpinnerAdapter

        val situacao = ArrayAdapter<String>(this, R.id.sp_situacao_livro_cadastro,R.layout.situacoes)
        sp_situacao_livro_cadastro.adapter = situacao


    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == RESULT_OK) {
            if (data?.getData() != null) {
                localImagem = data.getData();
            } else {
                Log.d("pastaSelecionada : ", "Came here its null !");
                Toast.makeText(getApplicationContext(), "Falha", Toast.LENGTH_SHORT).show();
            }

            if (requestCode == 10) {

                pastaSelecionada = localImagem.path;
                imageLivro.setImageURI(localImagem);
                Log.d("pastaSelecionada : ", pastaSelecionada);

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
            ) && sp_categoria_livro_cadastro.isSelected && sp_situacao_livro_cadastro.isSelected
        ) {
            val livro: Livro = Livro();
            livro.nome = ed_nome_livro_cadastro.text.toString()
            livro.descrição = ed_descricao_livro_cadastro.text.toString()
            livro.categoria = CategoriaDao.findByName(sp_categoria_livro_cadastro.selectedItem.toString())
            livro.imagem = imgbtn_imagem_livro_cadastro.background
            livro.usuario = usuarioLogado;
            livro.situacao = Livro.Situacao.valueOf(sp_situacao_livro_cadastro.toString())
            LivroDao.add(livro)
        }
    }
}
