package lcl.nio01;

import okhttp3.OkHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


class OkHttpClientDemo {


    public static void main(String[] args) {
        try {
            testHttpGet("hello");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String testHttpGet(String s) throws Exception {
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

}
