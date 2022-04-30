package com.example.jvm_demo;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

@Slf4j
public class JvmClassLoaderPrintPath {
    public static void main(String[] args) {
        //启动类加载器：使用sun.misc.Launcher.getBootstrapClassPath()获取启动类加载器，然后使用getURLs获取启动类加载器加载内容的URL
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器");
        for (URL url : urls){
            System.out.println(" ====>> " + url.toExternalForm());
        }

        // 扩展类加载器：使用当前类.class.getClassLoader()可以获取当前类的类加载器，即应用程序类加载器，再使用getParent()获取父类加载器，即扩展类加载器
        printClassLoader("扩展类加载器", JvmClassLoaderPrintPath.class.getClassLoader().getParent());

        // 应用程序类加载器：使用当前类.class.getClassLoader()可以获取当前类的类加载器，即应用程序类加载器
        printClassLoader("应用程序类加载器", JvmClassLoaderPrintPath.class.getClassLoader());
    }

    /**
     * 输出类加载器名称，并打印类加载器加载的文件
     * @param name 类加载器名称
     * @param CL 类加载器对象
     */
    public static void printClassLoader(String name, ClassLoader CL){
        if(CL != null){
            System.out.println(name + " ClassLoader ====>> " + CL.toString());
            printURLForClassLoader(CL);
        }else {
            System.out.println(name + " ClassLoader ====>> null ");
        }
    }

    /**
     * 使用反射获取类加载器中的URLClassPath ucp属性，再使用反射获取URLClassPath中的ArrayList<URL> path属性（类加载器加载的内容）并打印
     * @param CL 类加载器对象
     */
    public static void printURLForClassLoader(ClassLoader CL){
        Object ucp = insightField(CL, "ucp");
        Object path = insightField(ucp, "path");
        ArrayList ps = (ArrayList) path;
        for (Object p : ps){
            System.out.println(" ====>> " + p.toString());
        }
    }

    /**
     * 使用反射获取指定类中字段名称
     * @param obj
     * @param fName
     * @return
     */
    private static Object insightField(Object obj, String fName) {
        try {
            Field f = null;
            if(obj instanceof URLClassLoader){
                f = URLClassLoader.class.getDeclaredField(fName);
            }else {
                f = obj.getClass().getDeclaredField(fName);
            }
            f.setAccessible(true);
            return f.get(obj);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
