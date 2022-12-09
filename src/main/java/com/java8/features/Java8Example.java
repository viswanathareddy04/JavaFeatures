package com.java8.features;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Java8Example {

    public static void main(String[] args) {


        MyFunctionalInterface  fobje = (int x, int y) -> System.out.println(x+y);
        fobje.myMethod(5, 5);
        fobje.myDefaultMethod();

        // Consumer  -- an operation that accepts a single input argument and returns no result
        Consumer<String> consumer = System.out::println;

        // ForEach
        List<String> subList = new ArrayList<>();
        subList.add("Maths");
        subList.add("English");
        subList.add("French");
        subList.add("Sanskrit");
        subList.add("Abacus");
        subList.forEach(consumer);

        // Optional  -- final class  -- A container object which may or may not contain a non-null value. If a value is present, isPresent() will return true and get() will return the value.

    }
}
