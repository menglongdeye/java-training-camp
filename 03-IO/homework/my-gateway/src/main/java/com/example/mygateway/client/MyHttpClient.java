package com.example.mygateway.client;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MyHttpClient {

    private OkHttpClient client = new OkHttpClient();

    public String testOkHttpGet(String url) throws IOException {
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
