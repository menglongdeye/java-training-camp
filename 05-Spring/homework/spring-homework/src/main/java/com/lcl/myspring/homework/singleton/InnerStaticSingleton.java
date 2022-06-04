package com.lcl.myspring.homework.singleton;

public class InnerStaticSingleton {


    public static InnerStaticSingleton getSingleton(){
        return InnerStaticClass.singleton;
    }

    private InnerStaticSingleton(){

    }

    private static class InnerStaticClass{
        private static final InnerStaticSingleton singleton = new InnerStaticSingleton();
    }
}
