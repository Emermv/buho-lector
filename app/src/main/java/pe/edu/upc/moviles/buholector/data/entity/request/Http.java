package pe.edu.upc.moviles.buholector.data.entity.request;

import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import pe.edu.upc.moviles.buholector.model.BookModel;

public class Http {
    public static void request(String url,Callback callback){
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url).addHeader("idTransaccion","1")

                .build();
        client.newCall(request).enqueue(callback);
    }
}
