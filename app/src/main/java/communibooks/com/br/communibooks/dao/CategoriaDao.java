package communibooks.com.br.communibooks.dao;

import communibooks.com.br.communibooks.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
    public static List<Categoria> categorias = new ArrayList<>();

    public static void add(Categoria categoria) {
        categorias.add(categoria);
    }

    public static List<Categoria> getCategorias() {
        return categorias;
    }
}
