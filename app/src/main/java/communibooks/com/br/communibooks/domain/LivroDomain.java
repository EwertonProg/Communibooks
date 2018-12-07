package communibooks.com.br.communibooks.domain;

import communibooks.com.br.communibooks.dao.LivroDao;
import communibooks.com.br.communibooks.model.Livro;

public class LivroDomain {
    public static void cadastrar(Livro livro){
        LivroDao.add(livro);
    }


}
