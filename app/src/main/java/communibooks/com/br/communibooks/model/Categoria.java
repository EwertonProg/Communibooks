package communibooks.com.br.communibooks.model;

import android.media.Image;

public class Categoria {
    private String nome;
    private Image imagem;

    public Categoria() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }
}
