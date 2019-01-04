package communibooks.com.br.communibooks.model;

import android.graphics.drawable.Drawable;

public class Livro {
    private Integer id;
    private String nome;
    private Categoria categoria;
    private Drawable imagem;
    private String descricao;
    private Usuario usuario;
    private Situacao situacao;

    public String getDescricao() {
        return descricao;
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public enum Situacao {
        PARATROCA("Troca"), PARADOACAO("Doação"), DOADO("Doado"), TROCADO("Trocado");

        private final String situacao;

        Situacao(String situacaoValor){
            situacao = situacaoValor;
        }

        public static Situacao toEnum(String nomeSituacao){
            switch (nomeSituacao){
                case "Troca" : return Situacao.PARATROCA;
                case "Doação": return Situacao.PARADOACAO;
                case "Doado": return Situacao.DOADO;
                default: return Situacao.TROCADO;
            }
        }

        @Override
        public String toString() {
            return this.situacao;
        }
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
