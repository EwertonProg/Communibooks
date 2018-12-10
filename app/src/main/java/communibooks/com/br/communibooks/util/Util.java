package communibooks.com.br.communibooks.util;

import android.widget.EditText;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import communibooks.com.br.communibooks.model.Categoria;
import org.json.JSONArray;

public class Util {
    public static boolean validarEditText(EditText... edits) {
        boolean b = true;
        for (EditText ed : edits) {
            if (ed.getText().toString().length() == 0) {
                ed.setError("Tem que preencher animal");
                b = false;
            }
        }

        return b;
    }
}
