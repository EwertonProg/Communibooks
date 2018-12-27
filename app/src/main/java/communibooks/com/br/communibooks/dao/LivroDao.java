package communibooks.com.br.communibooks.dao;

import communibooks.com.br.communibooks.model.Livro;

import java.util.ArrayList;

public class LivroDao {
    private static ArrayList<Livro> livros = new ArrayList<>();

    public static void add(Livro livro) {
        livro.setId(livros.size());
        livros.add(livro);
    }

    public static ArrayList<Livro> getLivros() {
        return livros;
    }

    public static Livro findById(Integer id) {
        Livro livro = new Livro();
        for (Livro l : livros) {
            livro = (l.getId().equals(id)) ? l : null;
        }
        return livro;
    }
}
