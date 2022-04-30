package com.example.jvm_demo;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class JvmAppClassLoaderAddUrl {
    public static void main(String[] args) {
        String appPath = "file:/Users/conglongli/Documents/workspace/java-training-camp/01-JVM进阶/exercise/";
        URLClassLoader urlClassLoader = (URLClassLoader) JvmAppClassLoaderAddUrl.class.getClassLoader();

        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            URL url = new URL(appPath);
            addURL.invoke(urlClassLoader, url);
            Class.forName("PathClass");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
