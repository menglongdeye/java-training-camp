package com.example.mygateway.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class MyOkHttpClient {

    public String testHttpGet(String url) throws Exception {
        HttpGet request = new HttpGet(url);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            String result = EntityUtils.toString(response.getEntity());
            //System.out.println(url);
             System.out.println(result);
             return result;
        }
    }

}
