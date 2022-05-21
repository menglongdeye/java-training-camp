package com.example.mygateway.outbound.httpclient;

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

import java.util.List;
import java.util.concurrent.*;

public class HttpOutboundHandler {

    private List<String> urlList;
    private ExecutorService proxyServicePool;
    HttpResponseFilter responseFilter = new HeaderHttpHttpResponseFilter();
    HttpEndpointRouter router = new RandomHttpRouter();

    public HttpOutboundHandler(List<String> urlList){
        this.urlList = urlList;

        // 定义线程池及参数
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2048);
        ThreadFactory threadFactory = new NamedThreadFactory("proxy-service");
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        proxyServicePool = new ThreadPoolExecutor(corePoolSize,corePoolSize,keepAliveTime,
                TimeUnit.MILLISECONDS, workQueue, threadFactory, handler);
    }


    public void handler(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, HttpRequestFilter filter){
        filter.filter(fullHttpRequest, ctx);
        String uri = fullHttpRequest.getUri();
        String url = router.getUrl(this.urlList) + uri;
        proxyServicePool.submit(() -> fetchGet(fullHttpRequest, ctx, url));
    }

    private void fetchGet(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, String url) {
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
                    response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderNames.KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }


}
