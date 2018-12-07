package communibooks.com.br.communibooks.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import communibooks.com.br.communibooks.R;
import communibooks.com.br.communibooks.domain.UsuarioDomain;
import communibooks.com.br.communibooks.model.Usuario;
import communibooks.com.br.communibooks.util.Util;

import java.util.Arrays;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText ed_nome, ed_sobrenome, ed_nomeUsuario, ed_email, ed_telefone, ed_senha;
    Button btn_cadastrar;
    List<EditText> edits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ed_nome = findViewById(R.id.ed_nome_cadastro);
        ed_sobrenome = findViewById(R.id.ed_sobrenome_cadastro);
        ed_nomeUsuario = findViewById(R.id.ed_usuario_cadastro);
        ed_email = findViewById(R.id.ed_email_cadastro);
        ed_telefone = findViewById(R.id.ed_celular_cadastro);
        ed_senha = findViewById(R.id.ed_senha_cadastro);
        btn_cadastrar = findViewById(R.id.btn_cadastrar_cadastro);
        edits = Arrays.asList( ed_nome, ed_sobrenome, ed_nomeUsuario, ed_email, ed_telefone, ed_senha);

    }

    public void cadastrarUsuario(View view) {
        String nome = String.valueOf(ed_nome.getText());
        String sobrenome = String.valueOf(ed_sobrenome.getText());
        String email = String.valueOf(ed_email.getText());
        String nomeUsuario = String.valueOf(ed_nomeUsuario.getText());
        String telefone = String.valueOf(ed_telefone.getText());
        String senha = String.valueOf(ed_senha.getText());

        if(Util.validarEditText(edits)){
        Usuario usuario = new Usuario(nome, sobrenome, email , nomeUsuario, telefone, senha);
        UsuarioDomain.criar(usuario);
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        }
    }
}
