package v2.trabalho.lfa.trabalho_lfav2;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Daniel Monteiro
 *
 * @since on 28/10/2018.
 */

public class MakeRequest {

    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private static final OkHttpClient c = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor()).build();

    private static OkHttpClient client = c.newBuilder().connectTimeout(25, TimeUnit.SECONDS)
            .readTimeout(25, TimeUnit.SECONDS).build();

    public static String runWithGet(String url) throws Exception {
//        client.wait(200);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String runWithPost(String url, String json) throws Exception {
//        client.wait(200);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
