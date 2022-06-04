package com.lcl.myspring.homework.singleton;

public class DoubleCheckSingleton {
    private static volatile DoubleCheckSingleton singleton;

    public DoubleCheckSingleton getSingleton(){
        if(singleton == null){
            synchronized (this){
                if (singleton == null){
                    return new DoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }

    private DoubleCheckSingleton(){

    }

    private Object readResolve(){
        return singleton;
    }
}
