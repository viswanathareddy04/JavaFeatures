package com.java8.features;

@FunctionalInterface
public interface MyFunctionalInterface {

    public abstract void myMethod(int x, int y);

    public default void myDefaultMethod(){
        System.out.println("I'm from Default Method");
    }

}
