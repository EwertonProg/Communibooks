package communibooks.com.br.communibooks.model;

import java.util.Date;

public class Transacao {
    private Livro livro;
    private Usuario doador;
    private Usuario donatario;
    private Date data;

    public Transacao() {
        this.doador = livro.getUsuario();
        this.data = new Date();
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getDoador() {
        return doador;
    }

    public void setDoador(Usuario doador) {
        this.doador = doador;
    }

    public Usuario getDonatario() {
        return donatario;
    }

    public void setDonatario(Usuario donatario) {
        this.donatario = donatario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
