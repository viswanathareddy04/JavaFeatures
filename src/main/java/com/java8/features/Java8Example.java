package com.java8.features;
public class Java8Example {

    public static void main(String[] args) {
        MyFunctionalInterface  fobje = (int x, int y) -> System.out.println(x+y);
        fobje.myMethod(5, 5);
        fobje.myDefaultMethod();
    }
}
