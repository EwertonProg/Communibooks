package communibooks.com.br.communibooks.model;

import android.graphics.drawable.Drawable;

public class Livro {
    private Integer id;
    private String nome;
    private Categoria categoria;
    private Drawable imagem;
    private String descrição;
    private Usuario usuario;
    private Situacao situacao;

    public enum Situacao {
        PARATROCA("Troca"), PARADOACAO("Doação"), DOADO("Doado"), TROCADO("Trocado");

        private final String situacao;

        Situacao(String situacaoValor){
            situacao = situacaoValor;
        }
    }

    public Livro() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Drawable getImagem() {
        return imagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImagem(Drawable imagem) {
        this.imagem = imagem;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
}
