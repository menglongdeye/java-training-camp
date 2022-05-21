package com.example.mygateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("testkey","ml");
    }
}
