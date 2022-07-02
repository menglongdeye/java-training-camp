package myjuc.homework;

import javafx.util.Pair;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestPrintSql {
    public static void main(String[] args) throws Exception {
        //readFileToList2();
        Pair<String, String> pair = new Pair<String, String>("1","2");
        pair.getKey();
        pair.getValue();
        System.out.printf(pair.getKey() + "," + pair.getValue());
    }

    public static AtomicInteger atomicInteger = new AtomicInteger();
    public static AtomicInteger atomicInteger1 = new AtomicInteger();
    public static Random random = new Random();
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static ExecutorService executorService = Executors.newFixedThreadPool(8);

    public static void readFileToList2() throws Exception {
        List<Future<List<String>>> ansFutureList = new ArrayList<>();
        // printdemo1(ansFutureList);
        // printdemo2(ansFutureList);
        // printdemo3(ansFutureList);
        printdemo4(ansFutureList);
        executorService.shutdownNow();


    }


    public static void printdemo3(List<Future<List<String>>> ansFutureList) throws Exception{
        String path = "/Users/conglongli/Documents/workspace/java-training-camp/06-mysql/homework/3.sql";
        for (int k=0; k< 10;k++){
            Future<List<String>> listFuture = executorService.submit(new Print3Demo(k+1, path));
            ansFutureList.add(listFuture);
        }
        for(Future<List<String>> listFuture : ansFutureList){
            listFuture.get();
        }
    }

    public static void printdemo1(List<Future<List<String>>> ansFutureList) throws Exception{
        String path = "/Users/conglongli/Documents/workspace/java-training-camp/06-mysql/homework/1.sql";


        for (int k=0; k< 10;k++){
            Future<List<String>> listFuture = executorService.submit(new Print1Demo(k+1, path));
            ansFutureList.add(listFuture);
        }
        for(Future<List<String>> listFuture : ansFutureList){
            listFuture.get();
        }

    }

    public static void printdemo2(List<Future<List<String>>> ansFutureList) throws Exception{
        String insertValue = "insert into order_master(order_id,order_sn,customer_id ,shipping_user,province,city," +
                "  district,address,payment_method,order_money ,district_money,shipping_money ,payment_money,shipping_comp_name," +
                "  shipping_sn,create_time,shipping_time,pay_time ,receive_time,order_status,order_point,invoice_time,modified_time) values";
        String path = "/Users/conglongli/Documents/workspace/java-training-camp/06-mysql/homework/2.sql";
        List<String> ans = new ArrayList<>();
        ans.add(insertValue);
        writeListToFile(ans, path);
        for (int k=0; k< 2;k++){
            Future<List<String>> listFuture = executorService.submit(new Print2Demo(11+k, path));
            ansFutureList.add(listFuture);
        }
        for(Future<List<String>> listFuture : ansFutureList){
            listFuture.get();
        }
        StringBuilder sb = new StringBuilder();

        sb.append("(");
        String orderId = 12 + String.valueOf(Math.abs(System.currentTimeMillis() + atomicInteger1.incrementAndGet()));
        sb.append(orderId.substring(7,orderId.length())).append(",");
        sb.append(System.currentTimeMillis() + atomicInteger.incrementAndGet()).append(",");
        sb.append(Math.abs(random.nextInt())).append(",");
        sb.append("'").append(UUID.randomUUID().toString().substring(0,7)).append("'").append(",");
        sb.append(random.nextInt(10)+1).append(",");
        sb.append(random.nextInt(100)+1).append(",");
        sb.append(random.nextInt(1000)+1).append(",");
        sb.append("'").append(UUID.randomUUID()).append("'").append(",");
        sb.append(Math.abs(random.nextInt(5))).append(",");
        sb.append(Math.abs(random.nextDouble())).append(",");
        sb.append(Math.abs(random.nextDouble())).append(",");
        sb.append(Math.abs(random.nextDouble())).append(",");
        sb.append(Math.abs(random.nextDouble())).append(",");
        sb.append("'").append(UUID.randomUUID().toString().substring(0,7)).append("'").append(",");
        sb.append("'").append(UUID.randomUUID().toString()).append("'").append(",");
        sb.append("'").append(LocalDateTime.now().plusDays(-3).format(dateTimeFormatter)).append("'").append(",");
        sb.append("'").append(LocalDateTime.now().plusDays(-2).format(dateTimeFormatter)).append("'").append(",");
        sb.append("'").append(LocalDateTime.now().plusDays(-3).plusMinutes(5).format(dateTimeFormatter)).append("'").append(",");
        sb.append("'").append(LocalDateTime.now().format(dateTimeFormatter)).append("'").append(",");
        sb.append(Math.abs(random.nextDouble())).append(1).append(",");
        sb.append(Math.abs(random.nextInt())).append(",");
        sb.append("'").append(UUID.randomUUID().toString().substring(0,7)).append("'").append(",");
        sb.append("'").append(LocalDateTime.now().format(dateTimeFormatter)).append("'");
        sb.append(")");
        ans = new ArrayList<>();
        ans.add(sb.toString());
        writeListToFile(ans, path);
    }


    public static void printdemo4(List<Future<List<String>>> ansFutureList) throws Exception{
        String insertValue = "insert into order_master values";
        String path = "/Users/conglongli/Documents/workspace/java-training-camp/06-mysql/homework/4.sql";
        List<String> ans = new ArrayList<>();
        ans.add(insertValue);
        writeListToFile(ans, path);
        for (int k=0; k< 10;k++){
            Future<List<String>> listFuture = executorService.submit(new Print2Demo(11+k, path));
            ansFutureList.add(listFuture);
        }
        for(Future<List<String>> listFuture : ansFutureList){
            listFuture.get();
        }
        StringBuilder sb = new StringBuilder();

        sb.append("(");
        String orderId = 12 + String.valueOf(Math.abs(System.currentTimeMillis() + atomicInteger1.incrementAndGet()));
        sb.append(orderId.substring(7,orderId.length())).append(",");
        sb.append(System.currentTimeMillis() + atomicInteger.incrementAndGet()).append(",");
        sb.append(Math.abs(random.nextInt())).append(",");
        sb.append("'").append(UUID.randomUUID().toString().substring(0,7)).append("'").append(",");
        sb.append(random.nextInt(10)+1).append(",");
        sb.append(random.nextInt(100)+1).append(",");
        sb.append(random.nextInt(1000)+1).append(",");
        sb.append("'").append(UUID.randomUUID()).append("'").append(",");
        sb.append(Math.abs(random.nextInt(5))).append(",");
        sb.append(Math.abs(random.nextDouble())).append(",");
        sb.append(Math.abs(random.nextDouble())).append(",");
        sb.append(Math.abs(random.nextDouble())).append(",");
        sb.append(Math.abs(random.nextDouble())).append(",");
        sb.append("'").append(UUID.randomUUID().toString().substring(0,7)).append("'").append(",");
        sb.append("'").append(UUID.randomUUID().toString()).append("'").append(",");
        sb.append("'").append(LocalDateTime.now().plusDays(-3).format(dateTimeFormatter)).append("'").append(",");
        sb.append("'").append(LocalDateTime.now().plusDays(-2).format(dateTimeFormatter)).append("'").append(",");
        sb.append("'").append(LocalDateTime.now().plusDays(-3).plusMinutes(5).format(dateTimeFormatter)).append("'").append(",");
        sb.append("'").append(LocalDateTime.now().format(dateTimeFormatter)).append("'").append(",");
        sb.append(Math.abs(random.nextDouble())).append(1).append(",");
        sb.append(Math.abs(random.nextInt())).append(",");
        sb.append("'").append(UUID.randomUUID().toString().substring(0,7)).append("'").append(",");
        sb.append("'").append(LocalDateTime.now().format(dateTimeFormatter)).append("'");
        sb.append(")");
        ans = new ArrayList<>();
        ans.add(sb.toString());
        writeListToFile(ans, path);
    }


    static class Print2Demo implements Callable{
        private int index;
        private String path;

        public Print2Demo(int index, String path){
            this.index = index;
            this.path = path;
        }

        @Override
        public List<String> call() throws Exception {
            List<String> list = new ArrayList<>();
            for (int i=0;i<100000;i++){
                StringBuilder sb = new StringBuilder();

                sb.append("(");
                String orderId = index + String.valueOf(Math.abs(System.currentTimeMillis() + atomicInteger1.incrementAndGet()));
                sb.append(orderId.substring(7,orderId.length())).append(",");
                sb.append(System.currentTimeMillis() + atomicInteger.incrementAndGet()).append(",");
                sb.append(Math.abs(random.nextInt())).append(",");
                sb.append("'").append(UUID.randomUUID().toString().substring(0,7)).append("'").append(",");
                sb.append(random.nextInt(10)+1).append(",");
                sb.append(random.nextInt(100)+1).append(",");
                sb.append(random.nextInt(1000)+1).append(",");
                sb.append("'").append(UUID.randomUUID()).append("'").append(",");
                sb.append(Math.abs(random.nextInt(5))).append(",");
                sb.append(Math.abs(random.nextDouble())).append(",");
                sb.append(Math.abs(random.nextDouble())).append(",");
                sb.append(Math.abs(random.nextDouble())).append(",");
                sb.append(Math.abs(random.nextDouble())).append(",");
                sb.append("'").append(UUID.randomUUID().toString().substring(0,7)).append("'").append(",");
                sb.append("'").append(UUID.randomUUID().toString()).append("'").append(",");
                sb.append("'").append(LocalDateTime.now().plusDays(-3).format(dateTimeFormatter)).append("'").append(",");
                sb.append("'").append(LocalDateTime.now().plusDays(-2).format(dateTimeFormatter)).append("'").append(",");
                sb.append("'").append(LocalDateTime.now().plusDays(-3).plusMinutes(5).format(dateTimeFormatter)).append("'").append(",");
                sb.append("'").append(LocalDateTime.now().format(dateTimeFormatter)).append("'").append(",");
                sb.append(Math.abs(random.nextDouble())).append(i%2).append(",");
                sb.append(Math.abs(random.nextInt())).append(",");
                sb.append("'").append(UUID.randomUUID().toString().substring(0,7)).append("'").append(",");
                sb.append("'").append(LocalDateTime.now().format(dateTimeFormatter)).append("'");
                sb.append("),");
                list.add(sb.toString());
                if(list.size() == 10000){
                    synchronized (LockClass.class){
                        writeListToFile(list, path);
                    }
                    list = new ArrayList<>();
                }
            }
            return list;
        }
    }


    static class Print1Demo implements Callable{
        private int index;
        private String path;

        public Print1Demo(int index, String path){
            this.index = index;
            this.path = path;
        }

        @Override
        public List<String> call() throws Exception {
            List<String> list = new ArrayList<>();
            for (int i=0;i<100000;i++){
                StringBuilder sb = new StringBuilder();
                String insertValue = "insert into order_master(order_id,order_sn,customer_id ,shipping_user,province,city," +
                        "  district,address,payment_method,order_money ,district_money,shipping_money ,payment_money,shipping_comp_name," +
                        "  shipping_sn,create_time,shipping_time,pay_time ,receive_time,order_status,order_point,invoice_time,modified_time)";
                sb.append(insertValue).append("values(");
                String orderId = index + String.valueOf(Math.abs(System.currentTimeMillis() + atomicInteger1.incrementAndGet()));
                sb.append(orderId.substring(7,orderId.length())).append(",");
                sb.append(System.currentTimeMillis() + atomicInteger.incrementAndGet()).append(",");
                sb.append(Math.abs(random.nextInt())).append(",");
                sb.append("'").append(UUID.randomUUID().toString().substring(0,7)).append("'").append(",");
                sb.append(random.nextInt(10)+1).append(",");
                sb.append(random.nextInt(100)+1).append(",");
                sb.append(random.nextInt(1000)+1).append(",");
                sb.append("'").append(UUID.randomUUID()).append("'").append(",");
                sb.append(Math.abs(random.nextInt(5))).append(",");
                sb.append(Math.abs(random.nextDouble())).append(",");
                sb.append(Math.abs(random.nextDouble())).append(",");
                sb.append(Math.abs(random.nextDouble())).append(",");
                sb.append(Math.abs(random.nextDouble())).append(",");
                sb.append("'").append(UUID.randomUUID().toString().substring(0,7)).append("'").append(",");
                sb.append("'").append(UUID.randomUUID().toString()).append("'").append(",");
                sb.append("'").append(LocalDateTime.now().plusDays(-3).format(dateTimeFormatter)).append("'").append(",");
                sb.append("'").append(LocalDateTime.now().plusDays(-2).format(dateTimeFormatter)).append("'").append(",");
                sb.append("'").append(LocalDateTime.now().plusDays(-3).plusMinutes(5).format(dateTimeFormatter)).append("'").append(",");
                sb.append("'").append(LocalDateTime.now().format(dateTimeFormatter)).append("'").append(",");
                sb.append(Math.abs(random.nextDouble())).append(i%2).append(",");
                sb.append(Math.abs(random.nextInt())).append(",");
                sb.append("'").append(UUID.randomUUID().toString().substring(0,7)).append("'").append(",");
                sb.append("'").append(LocalDateTime.now().format(dateTimeFormatter)).append("'");
                sb.append(")").append(";");
                list.add(sb.toString());
                if(list.size() == 10000){
                    synchronized (LockClass.class){
                        writeListToFile(list, path);
                    }
                    list = new ArrayList<>();
                }
            }
            return list;
        }
    }


    static class Print3Demo implements Callable{
        private int index;
        private String path;

        public Print3Demo(int index, String path){
            this.index = index;
            this.path = path;
        }

        @Override
        public List<String> call() throws Exception {
            List<String> list = new ArrayList<>();
            for (int i=0;i<100000;i++){
                StringBuilder sb = new StringBuilder();
                String insertValue = "insert into order_master";
                sb.append(insertValue).append(" values(");
                String orderId = index + String.valueOf(Math.abs(System.currentTimeMillis() + atomicInteger1.incrementAndGet()));
                sb.append(orderId.substring(7,orderId.length())).append(",");
                sb.append(System.currentTimeMillis() + atomicInteger.incrementAndGet()).append(",");
                sb.append(Math.abs(random.nextInt())).append(",");
                sb.append("'").append(UUID.randomUUID().toString().substring(0,7)).append("'").append(",");
                sb.append(random.nextInt(10)+1).append(",");
                sb.append(random.nextInt(100)+1).append(",");
                sb.append(random.nextInt(1000)+1).append(",");
                sb.append("'").append(UUID.randomUUID()).append("'").append(",");
                sb.append(Math.abs(random.nextInt(5))).append(",");
                sb.append(Math.abs(random.nextDouble())).append(",");
                sb.append(Math.abs(random.nextDouble())).append(",");
                sb.append(Math.abs(random.nextDouble())).append(",");
                sb.append(Math.abs(random.nextDouble())).append(",");
                sb.append("'").append(UUID.randomUUID().toString().substring(0,7)).append("'").append(",");
                sb.append("'").append(UUID.randomUUID().toString()).append("'").append(",");
                sb.append("'").append(LocalDateTime.now().plusDays(-3).format(dateTimeFormatter)).append("'").append(",");
                sb.append("'").append(LocalDateTime.now().plusDays(-2).format(dateTimeFormatter)).append("'").append(",");
                sb.append("'").append(LocalDateTime.now().plusDays(-3).plusMinutes(5).format(dateTimeFormatter)).append("'").append(",");
                sb.append("'").append(LocalDateTime.now().format(dateTimeFormatter)).append("'").append(",");
                sb.append(Math.abs(random.nextDouble())).append(i%2).append(",");
                sb.append(Math.abs(random.nextInt())).append(",");
                sb.append("'").append(UUID.randomUUID().toString().substring(0,7)).append("'").append(",");
                sb.append("'").append(LocalDateTime.now().format(dateTimeFormatter)).append("'");
                sb.append(")").append(";");
                list.add(sb.toString());
                if(list.size() == 10000){
                    synchronized (LockClass.class){
                        writeListToFile(list, path);
                    }
                    list = new ArrayList<>();
                }
            }
            return list;
        }
    }


    /**
     * 实现写操作方法
     */
    private static void writeListToFile(List<String> listStr, String path) {
        File file = new File(path);// 要写入的文件路径
        if (!file.exists()) {// 判断文件是否存在
            try {
                file.createNewFile();// 如果文件不存在创建文件
                System.out.println("文件"+file.getName()+"不存在已为您创建!");
            } catch (IOException e) {
                System.out.println("创建文件异常!");
                e.printStackTrace();
            }
        } else {
            System.out.println("文件"+file.getName()+"已存在!");
        }

        for (String str : listStr) {// 遍历listStr集合
            FileOutputStream fos = null;
            PrintStream ps = null;
            try {
                fos = new FileOutputStream(file,true);// 文件输出流  追加
                ps = new PrintStream(fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String string  = str + "\r\n";// +换行
            ps.print(string); // 执行写操作
            ps.close(); // 关闭流

        }

        System.out.println("文件写入完毕!");
    }

    static class LockClass{

    }
}
