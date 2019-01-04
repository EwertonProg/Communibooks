package communibooks.com.br.communibooks.model;

import android.location.Location;
import android.media.Image;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private Integer id;
    private Image foto;
    private String nome;
    private String sobrenome;
    private String email;
    private String nomeUsuario;
    private String numTel;
    private String senha;
    private Location localizacao;

    private List<Livro> livros = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nome, String sobrenome, String email, String nomeUsuario, String numTel, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.nomeUsuario = nomeUsuario;
        this.numTel = numTel;
        this.senha = senha;
    }

    public Usuario(String nomeUsuario, String senha ){
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Location getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Location localizacao) {
        this.localizacao = localizacao;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public boolean equals(Object obj) {
        Usuario u = (Usuario)obj;
        return this.nomeUsuario.equals(u.nomeUsuario);
    }
}
