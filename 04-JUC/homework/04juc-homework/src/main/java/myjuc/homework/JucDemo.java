package myjuc.homework;

import java.util.concurrent.*;

public class JucDemo {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception{
        // 方式一：使用Future+Runable
        futureRunableDemo(); // TODO
        // 方式二：使用Future+Callable
        futureCallableDemo();
        // 方式三：使用Future+Callable+lamdba
        futureCallableLamdaDemo();
        // 方式四：使用FutureTask
        futureTaskDemo();
        // 方式五：使用completablefuture
        completablefutureDemo();
        // 停止主线程
        executorService.shutdownNow();
    }

    public static void completablefutureDemo() throws Exception{
        long start=System.currentTimeMillis();
        // 异步执行 下面方法
        CompletableFuture<Integer> futureTask = CompletableFuture.supplyAsync(()->{
            return sum();
        });
        Integer result = futureTask.get();
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static void futureTaskDemo() throws Exception{
        long start=System.currentTimeMillis();
        // 异步执行 下面方法
        FutureTask<Integer> futureTask = new FutureTask<>(()->sum());
        new Thread(futureTask).start();
        Integer result = futureTask.get();
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static void futureRunableDemo() throws Exception{
        long start=System.currentTimeMillis();
        // 异步执行 下面方法
        Integer result = null;
        // Demo demo = new Demo();
        Future<Integer> sumFuture = executorService.submit(new TaskDemo(result), result);
//        Future<Demo> sumFuture = executorService.submit(new Runnable() {
//            private Demo demo;
//            @Override
//            public void run() {
//                demo.setNum(sum());
//            }
//        },  demo);
        System.out.println("异步计算结果为："+result);
        result = sumFuture.get();
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }


    public static void futureCallableLamdaDemo() throws Exception{
        long start=System.currentTimeMillis();
        // 异步执行 下面方法
        Future<Integer> sumFuture = executorService.submit(() -> {return sum();});
        Integer result = sumFuture.get();
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static void futureCallableDemo() throws Exception{
        long start=System.currentTimeMillis();
        // 异步执行 下面方法
        Future<Integer> sumFuture = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        Integer result = sumFuture.get();
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2){
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }
}

class TaskDemo implements Runnable{
    private Integer result;
    public TaskDemo(Integer result){
        this.result = result;
    }

    @Override
    public void run() {
        result = sum();
    }

    private int sum() {
        return fibo(36);
    }

    private int fibo(int a) {
        if ( a < 2){
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }
}