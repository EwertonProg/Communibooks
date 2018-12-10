package communibooks.com.br.communibooks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import communibooks.com.br.communibooks.R;
import communibooks.com.br.communibooks.domain.UsuarioDomain;
import communibooks.com.br.communibooks.model.Usuario;
import communibooks.com.br.communibooks.util.Util;

public class LoginActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText edSenha;
    EditText edUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edSenha = findViewById(R.id.ed_senha_login);
        edUsuario = findViewById(R.id.ed_usuario_login);
    }

    public void logar(View view) {
        String senha = edSenha.getText().toString();
        String nomeUsuario = edUsuario.getText().toString();
        Usuario usuario = new Usuario(nomeUsuario, senha);
        if (Util.validarEditText(edUsuario, edSenha)) {
            if (UsuarioDomain.autenticar(usuario)) {
                Intent i = new Intent(this, TelaPrincipal.class);
                i.putExtra("usuarioLogado", nomeUsuario);
                startActivity(i);
                finishAffinity();
            }
        }
    }

    public void cadastrar(View view) {
        startActivity(new Intent(this, CadastroActivity.class));
    }

}
