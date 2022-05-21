package com.example.mygateway.inbound;

import com.example.mygateway.client.MyOkHttpClient;
import com.example.mygateway.filter.HeaderHttpHttpRequestFilter;
import com.example.mygateway.filter.HttpRequestFilter;
import com.example.mygateway.outbound.okclient.HttpOutboundHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MyHttpHandler extends ChannelInboundHandlerAdapter {

    private List<String> urlList;

    private HttpOutboundHandler httpOutboundHandler;

    HttpRequestFilter httpRequestFilter = new HeaderHttpHttpRequestFilter();

    public MyHttpHandler(List<String> urlList){
        this.urlList = urlList;
        this.httpOutboundHandler = new HttpOutboundHandler(this.urlList);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
        String uri = fullHttpRequest.getUri();
//        if("/test".equals(uri)){
//            handlerTest(ctx, fullHttpRequest, "hello lcl");
//        }else {
//            handlerTest(ctx, fullHttpRequest, "hello other");
//        }
        httpOutboundHandler.handler(fullHttpRequest, ctx, httpRequestFilter);
        ctx.fireChannelRead(msg);
    }

    private void handlerTest(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest, String body) {
        MyOkHttpClient okHttpClient = new MyOkHttpClient();
        FullHttpResponse response = null;


        try {

            String value = okHttpClient.testHttpGet(this.urlList.get(0)) + body;
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().set("Content-Length", response.content().readableBytes());
        } catch (Exception e) {
            System.out.printf("处理异常" + e.getMessage());
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
        }finally {
            if(fullHttpRequest != null){
                if(!HttpUtil.isKeepAlive(fullHttpRequest)){
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                }else {
                    response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderNames.KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }
}
