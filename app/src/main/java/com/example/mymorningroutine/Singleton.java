package com.example.mymorningroutine;

public class Singleton {
    private static Singleton instance;

    public static Singleton get() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    private Singleton() {

    }

    private void build_weekFile(){

    }





}
