package com.lcl.myspring.homework.singleton;

public class HungrySingleton {

    private static HungrySingleton hungrySingleton = new HungrySingleton();

    public HungrySingleton getHungrySingleton(){
        return hungrySingleton;
    }

    private HungrySingleton(){

    }
}
