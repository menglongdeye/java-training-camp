package com.example.mygateway.outbound.okclient;

import com.example.mygateway.client.MyOkHttpClient;
import com.example.mygateway.filter.HeaderHttpHttpResponseFilter;
import com.example.mygateway.filter.HttpRequestFilter;
import com.example.mygateway.filter.HttpResponseFilter;
import com.example.mygateway.remout.HttpEndpointRouter;
import com.example.mygateway.remout.RandomHttpRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.List;
import java.util.concurrent.*;

public class OkHttpOutboundHandler {

    private List<String> urlList;
    private ExecutorService proxyServicePool;
    private CloseableHttpAsyncClient httpClient;
    HttpResponseFilter responseFilter = new HeaderHttpHttpResponseFilter();
    HttpEndpointRouter router = new RandomHttpRouter();

    public OkHttpOutboundHandler(List<String> urlList){
        this.urlList = urlList;

        // 定义线程池及参数
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2048);
        ThreadFactory threadFactory = new NamedThreadFactory("proxy-service");
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        proxyServicePool = new ThreadPoolExecutor(corePoolSize,corePoolSize,keepAliveTime,
                TimeUnit.MILLISECONDS, workQueue, threadFactory, handler);


        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(corePoolSize)
                .setRcvBufSize(32*1024).build();

        httpClient = HttpAsyncClients.custom().setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioReactorConfig)
                .setKeepAliveStrategy(((httpResponse, httpContext) -> 6000))
                .build();
        httpClient.start();
    }


    public void handler(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, HttpRequestFilter filter){
        filter.filter(fullHttpRequest, ctx);
        String uri = fullHttpRequest.getUri();
        String url = router.getUrl(this.urlList) + uri;
        proxyServicePool.submit(() -> fetchGet(fullHttpRequest, ctx, url));
    }


    private void fetchGet(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, String url) {
        final HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        httpClient.execute(httpGet, new FutureCallback<org.apache.http.HttpResponse>() {
            @Override
            public void completed(org.apache.http.HttpResponse httpResponse) {
                try {
                    handlerResponse(fullHttpRequest, ctx, httpResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void failed(Exception e) {
                httpGet.abort();
                e.printStackTrace();
            }

            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });
    }


    private void handlerResponse(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, org.apache.http.HttpResponse httpResponse) throws Exception {
        FullHttpResponse response = null;
        try {
            byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());
            //String value = okHttpClient.testHttpGet(url);
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));

            response.headers().set("Content-Type", "application/json");
            response.headers().set("Content-Length", response.content().readableBytes());
            responseFilter.filter(response);
        } catch (Exception e) {
            System.out.printf("处理异常" + e.getMessage());
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
        }finally {
            if(fullHttpRequest != null){
                if(!HttpUtil.isKeepAlive(fullHttpRequest)){
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                }else {
                    //response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderNames.KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
    }

    private void fetchhttpGet(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, String url) {
        MyOkHttpClient okHttpClient = new MyOkHttpClient();
        FullHttpResponse response = null;
        try {
            String value = okHttpClient.testHttpGet(url);
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));

            response.headers().set("Content-Type", "application/json");
            response.headers().set("Content-Length", response.content().readableBytes());
            responseFilter.filter(response);
        } catch (Exception e) {
            System.out.printf("处理异常" + e.getMessage());
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
        }finally {
            if(fullHttpRequest != null){
                if(!HttpUtil.isKeepAlive(fullHttpRequest)){
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                }else {
                    //response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderNames.KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
    }


}
