package communibooks.com.br.communibooks.util;

import android.content.res.AssetManager;
import android.util.Log;
import android.widget.EditText;
import com.google.gson.*;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import communibooks.com.br.communibooks.dao.CategoriaDao;
import communibooks.com.br.communibooks.model.Categoria;
import cz.msebera.android.httpclient.Header;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Util {

    private static String json;

    public static void popularCategoria(AssetManager am, String localJson) throws IOException {
        AsyncHttpClient c = new AsyncHttpClient();
        c.get(localJson, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("AsyncHttpClient", "response = " + responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("AsyncHttpClient", "response = " + responseString);
                json = responseString;
            }
        });
        BufferedReader br = new BufferedReader(new InputStreamReader(am.open("categorias.json")));
        Categoria categoria;
        Gson gson = new Gson();
        JsonParser jp = new JsonParser();
        JsonElement je = (json != null) ? jp.parse(json) : jp.parse(br);
        JsonObject jobj = je.getAsJsonObject();
        JsonArray ja = jobj.getAsJsonArray("Categorias");

        for (int i = 0; i < ja.size() - 1; i++) {
            categoria = gson.fromJson(ja.get(i), Categoria.class);
            Log.d("json", categoria.getNome());
            Log.d("json", categoria.getImagem());
            CategoriaDao.add(categoria);
        }
    }


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
