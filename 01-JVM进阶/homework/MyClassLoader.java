package com.example.jvm_demo;


import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {
            // 使用自定义ClassLoader加载类
            Object obj = new MyClassLoader().findClass("/Users/conglongli/Documents/study/Java进阶训练营-2022/第一周-JAVA进阶/Hello.xlass").newInstance();
            // 使用反射调用 hello 方法
            invokeMethod(obj, "hello");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射获取方法并调用
     * @param obj
     * @param methodName
     */
    private static void invokeMethod(Object obj, String methodName) {
        try {
            Method hello = obj.getClass().getDeclaredMethod(methodName);
            hello.invoke(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 重写findClass方法
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = new byte[0];
        int bnum = 255;
        Path path = Paths.get(name);
        // 获取字节数组
        try {
            bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 还原字节数组
        for (int i=0;i<bytes.length;i++){
            bytes[i] = (byte)(bnum - (int)bytes[i]);
        }
        // 类加载
        return defineClass("Hello", bytes, 0, bytes.length);
    }


}
