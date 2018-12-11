package communibooks.com.br.communibooks.dao;

import communibooks.com.br.communibooks.model.Categoria;

import java.util.ArrayList;

public class CategoriaDao {
    public static ArrayList<Categoria> categorias = new ArrayList<>();

    public static void add(Categoria categoria) {
        categorias.add(categoria);
    }

    public static ArrayList<Categoria> getCategorias() {
        return categorias;
    }
}
