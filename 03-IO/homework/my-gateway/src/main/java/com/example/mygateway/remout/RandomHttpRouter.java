package com.example.mygateway.remout;

import java.util.List;
import java.util.Random;

public class RandomHttpRouter implements HttpEndpointRouter{
    @Override
    public String getUrl(List<String> urlList) {
        Random random = new Random(System.currentTimeMillis());
        int index =  random.nextInt(urlList.size());
        return urlList.get(index);
    }
}
