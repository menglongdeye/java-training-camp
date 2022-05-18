package com.example.jvm_demo;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.Enumeration;

@RestController
@RequestMapping("/v1")
class DemoController {


    private OkHttpClient client = new OkHttpClient();

    @GetMapping("/httpclient")
    public String testHttpGet(@RequestParam String s) throws Exception {
        String url = "http://localhost:8080/v1/demo?s="+s;
        HttpGet request = new HttpGet(url);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            String result = EntityUtils.toString(response.getEntity());
            //System.out.println(url);
             System.out.println(result);
             return result;
        }
    }

    @GetMapping("/okclient")
    public String testOkHttpGet(@RequestParam String s) throws IOException {
        String url = "http://localhost:8080/v1/demo?s="+s;
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

    @GetMapping("/stream")
    public ResponseEntity<String> testStream(HttpServletRequest request) throws IOException {
        String s = request.getHeader("Request-Origion");
        if(!"Knife4j".equals(s)){
            return new ResponseEntity<>("internal error", HttpStatus.SERVICE_UNAVAILABLE);
        }
        Enumeration<String> headerNames = request.getHeaderNames();
        Enumeration<String> ll1 = request.getHeaders("ll");

        return ResponseEntity.ok("OK");
    }


}
