package com.ua.codespace;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Stream;

public class MethodRefCE_001 {
    public static class MethodReferencing1 {

        public static void main(String[] args) throws Exception {

            String name = "John";


        /* Callable FI has method call, that receives no arguments*/
            Callable<String> callable = name::toUpperCase;

        /* Function<String, String> requires method, that receives String argument and returns String.
        * In this case uppercase operation will apply to argument specified by FI method.
        * */
            Function<String, String> upperfier = String::toUpperCase;
            Function<String, Integer> sizeGetter = String::length;
            sizeGetter.apply("asdasdas");

            System.out.println(callable.call());

            System.out.println(upperfier.apply(name));

        /* Method reference on string constant. IS IT CORRECT? */
            Callable helloLength = "Hello"::length;
            System.out.println(helloLength.call());

        /* Ask about String pool and intern() method*/
        }

    }

    //    -------------------------------------------------------------------------------------------------------------

    /**
     * Shows an example of array constructor reference.
     */
    public static class ConstructorReference1 {

        public static void main(String[] args) throws Exception {

        /* Function<Integer, String[]> requires an integer as input parameters, and returns array of String.
         * In this case we use array constructor reference, that receives one int parameter - array size,
         * and creates new array of String.
         * */
            Function<Integer, String[]> stringFactory = String[]::new;

            String[] arrayOfString = stringFactory.apply(10);

            System.out.println(arrayOfString.length);

        }

    }

    //    -------------------------------------------------------------------------------------------------------------

    interface ClassCreator {
        MyClass create(int number);
    }

    static class MyClass {
        private int number;

        public MyClass(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    /**
     * Shows an example of constructor reference.
     */
    public static class ConstructorReference2 {

        public static void main(String[] args) throws Exception {
            ClassCreator creator = MyClass::new;

            MyClass obj = creator.create(235);
            System.out.println(obj.getNumber());
        }

    }

    //    -------------------------------------------------------------------------------------------------------------

    interface StringConverter<T> {
        String convert(T obj);
    }

    /**
     * Generic functional interface StringConverter combined with static method reference
     */
    public static class GenericMethodReference1 {

        public static void main(String[] args) throws Exception {
            StringConverter converter = String::valueOf;

            System.out.println(converter.convert(123).length());
            System.out.println(converter.convert(123.0).length());
            System.out.println(converter.convert(123L).length());

            Method[] methods = converter.getClass().getMethods();
            System.out.println(methods.length);
            Stream.of(methods).forEach(System.out::println);
            System.out.println(converter.convert(Boolean.FALSE).length());

//        StringConverter<Integer> intConverter = String::valueOf;
//        System.out.println(intConverter.convert(255).length());
//        System.out.println(intConverter.convert(255.0).length()); -- method convert cannot be applied to double
        }

    }

}
