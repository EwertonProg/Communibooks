package communibooks.com.br.communibooks.dao;

import communibooks.com.br.communibooks.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private static List<Usuario> usuarios = new ArrayList<>();
    public static void add(Usuario usuario){
        usuarios.add(usuario);
    }

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }


}
