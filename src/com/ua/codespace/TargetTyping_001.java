package com.ua.codespace;

import java.util.Comparator;
import java.util.function.Supplier;

public class TargetTyping_001 {
    public static void main(String[] args) {

        /* Variable declaration*/
        Comparator<String> c = String::compareToIgnoreCase;

        /* Assignments*/
        Comparator<String> c1;
        c1 = (String s1, String s2) -> s1.compareToIgnoreCase(s2);

        Comparator c3 = getComparator();

        /* Conditional operator and lambda expression body*/
        boolean flag = true;
        Supplier<Runnable> codeSupplier = flag ? (() -> () -> System.out.println("TRUE")) : (() -> () -> System.out.println("FALSE"));

        // prints true
        codeSupplier.get().run();

        /* Cast expression */
//        Object runnable = () -> System.out.println("Hello!");
        Object helloCode = (Runnable) () -> System.out.println("Hello!");


    }

    /* Return statement*/
    static Comparator<String> getComparator() {
        return (s1, s2) -> s1.compareToIgnoreCase(s2);
    }
}
