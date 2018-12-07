package communibooks.com.br.communibooks.util;

import android.widget.EditText;

import java.util.List;

public class Util {
    public static boolean validarEditText (List<EditText> edits){
        boolean b = true;
        for(EditText ed:edits){
            if (ed.getText().toString().length() == 0) {
                ed.setError("Tem que preencher animal");
                b =false;
            }
        }

        return b;
    }
}
