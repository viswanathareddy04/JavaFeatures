package com.java8.features;

public class TestInstanceClass implements MyFunctionalInterface{
    @Override
    public void myMethod(int x, int y) {
               System.out.println(x*y);
    }

    @Override
    public void myDefaultMethod() {
        MyFunctionalInterface.super.myDefaultMethod();
        System.out.println("I have been overwritten by the implemented class, i'm not from interface");
    }
}
