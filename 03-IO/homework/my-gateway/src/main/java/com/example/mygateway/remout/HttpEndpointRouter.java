package com.example.mygateway.remout;

import java.util.List;

public interface HttpEndpointRouter {
    String getUrl(List<String> urlList);
}
