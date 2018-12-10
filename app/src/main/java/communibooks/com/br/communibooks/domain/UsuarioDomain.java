package communibooks.com.br.communibooks.domain;

import communibooks.com.br.communibooks.dao.UsuarioDao;
import communibooks.com.br.communibooks.model.Usuario;

public class UsuarioDomain {
    public static void criar(Usuario usuario){

            UsuarioDao.add(usuario);
    }

    public static boolean autenticar(Usuario usuario){
        Usuario u = UsuarioDao.findByNomeUsuario(usuario.getNomeUsuario());
        if(u!= null){
            if(u.getSenha().equals(usuario.getSenha())){
                return true;
            }
        }
        return false;
    }
}