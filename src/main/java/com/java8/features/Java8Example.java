package com.java8.features;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Example {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        // Functional Interface
        MyFunctionalInterface  fobje = (int x, int y) -> System.out.println(x+y);
        fobje.myMethod(5, 5);
        fobje.myDefaultMethod();

        // Functional Interface Continued
        TestInstanceClass testInstanceClass = new TestInstanceClass();
        testInstanceClass.myDefaultMethod();
        testInstanceClass.myMethod(5,5);

        // Consumer  -- an operation that accepts a single input argument and returns no result
        Consumer<String> stringConsumer = System.out::println;

        // ForEach
        List<String> subList1 = new ArrayList<>();
        subList1.add("Maths");
        subList1.add("English");
        subList1.add("French");
        subList1.add("Sanskrit");
        subList1.add("Abacus");
        subList1.forEach(stringConsumer);

        // Optional  -- final class  -- A container object which may or may not contain a non-null value. If a value is present, isPresent() will return true and get() will return the value.

        Optional<String[]> stringOptional = Optional.of(args);
        if(stringOptional.isEmpty()){
            System.out.println("args is empty");
        }
        else{
            System.out.println("args is not empty");
        }

        // Stream
        // Predicate --- Represents a predicate (boolean-valued function) of one argument.
        Function<String, Integer> stringIntegerFunction = String::length;
        Predicate<String> stringPredicate = s -> s.length()> 5;
        List<String> subList2 = subList1.stream().filter(s ->  stringIntegerFunction.apply(s) >5).toList();
        subList2.forEach(stringConsumer);
        Optional<String> lang = Optional.ofNullable(subList1.stream().filter(s -> s.equals("Telugu")).findAny().orElse(null));
        System.out.println(lang.isPresent());

        // Function and BiFunction
        System.out.println("*********************************** Function<T, R> and BiFunction<T,R,U>:: Are functional Interfaces which takes one arguement of one type as input and return another type  ***************************");
        Function<String, Integer> function1 = String::length;
        BiFunction<String, String, String> biFunction1 = String::concat;
        System.out.println(function1.apply("Viswanatha Reddy"));
        System.out.println(biFunction1.apply("Viswanatha Reddy", " Inja"));
        System.out.println(biFunction1.andThen(function1).apply("Viswanatha Reddy", " Inja"));

        // Predicate and BiPredicate
        System.out.println("*********************************** Predicate<T> and BiPredicate<T, R> :: Are functional interfaces used for performing conditional statements like if condition***************************");
        Predicate<String> stringPredicate1 = s -> s.length()>5;
        BiPredicate<String, String> stringStringBiPredicate = (s1, s2) -> s1.length()> s2.length();
        subList1.stream().filter(stringPredicate1.and(stringPredicate1)).collect(Collectors.toList());

        System.out.println("***********************************  Consumer<T> and BiConsumer<T, R>  :: are Functional Interfaces takes arguments but does not return anything ***************************");
        Consumer<String>  stringConsumer1 = System.out::println;
        BiConsumer<String, String> stringStringBiConsumer = (s, s2) -> System.out.println(s+s2);
        stringConsumer1.accept("Viswanatha Reddy");
        stringStringBiConsumer.accept("Viswanatha Reddy", " Inja");

        System.out.println("*********************************** Supplier<T> ::Supplier is a functional interface; it takes no arguments and returns a result.  ***************************");
        Supplier<String> localDateTimeSupplier = () -> dtf.format(LocalDateTime.now());
        System.out.println(localDateTimeSupplier.get());

        System.out.println("*********************************** UnaryOperator<T> extends Function<T, T>  :: is an functional interface takes one argument, and returns a result of the same type of its arguments.  ***************************");
        UnaryOperator<Integer> integerUnaryOperator = integer -> integer*2;
        System.out.println(integerUnaryOperator.apply(2));

        System.out.println("*********************************** BinaryOperator<T> extends BiFunction<T,T,T> :: is an functional interface takes two arguments of any type, and returns a result of any type.  ***************************");
        BinaryOperator<Integer> func2 = Integer::sum;
        System.out.println(func2.apply(2, 3));
    }
}
