package com.example.jvm_demo;

import java.util.Random;

public class RandomTest {
    public static void main(String[] args) throws Exception
    {
        Random r = new Random();
        r.setSeed(555L);
        for (int j = 0; j < 5; j++) {
            System.out.print(" " + r.nextInt(100) + ", ");
        }
    }
}
