package communibooks.com.br.communibooks.dao;

import communibooks.com.br.communibooks.model.Livro;

import java.util.ArrayList;

public class LivroDao {
    private static ArrayList<Livro> livros = new ArrayList<>();

    public static void add(Livro livro) {
        livros.add(livro);
    }

    public static ArrayList<Livro> getLivros() {
        return livros;
    }
}
