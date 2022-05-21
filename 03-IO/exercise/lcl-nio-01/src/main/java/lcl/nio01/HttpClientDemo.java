package lcl.nio01;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

class HttpClientDemo {


    private static OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        try {
            testOkHttpGet("hello");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static String testOkHttpGet(String s) throws IOException {
        String url = "http://localhost:8088/v1/demo?s="+s;
        Request request = new Request.Builder()
                   .url(url)
                   .get()
                   .build();
        System.out.println(url);
        final Call call = client.newCall(request);
        Response response = call.execute();
        String result = response.body().string();
        System.out.println(result);
        return result;
    }

}
