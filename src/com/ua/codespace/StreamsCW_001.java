package com.ua.codespace;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;


/**
 * Imperative vs Declarative
 */

public class StreamsCW_001 {


    /**
     * Let's consider a few ways how we can create stream
     */
    public static class CreatingStreams {
        public static void main(String[] args) {

        /* Stream created of some values/objects*/
            Stream<Integer> intStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            printStream(intStream);

        /* Stream created from array of doubles*/
            Double[] doubleArray = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0};
            Stream<Double> doubleStream1 = Arrays.stream(doubleArray);
            printStream(doubleStream1);

        /* Stream created from a part of array*/
            Stream<Double> doubleStream2 = Arrays.stream(doubleArray, 1, 5);
            printStream(doubleStream2);

            /**
             * Let's discover Collection class, and its default method stream().
             * What is default method? Why it's so important?
             * */


            List<String> names = Arrays.asList("Alex", "Bob", "Charley", "Alex");
            boolean result = names.stream()
                    .anyMatch(element -> element.equals("Bob1"));
            System.out.println(result);
        }

        static <T> void printStream(Stream<T> stream) {
            stream.forEach(e -> System.out.print(e + ", "));
            System.out.println("\n");
        }
    }

    //    -------------------------------------------------------------------------------------------------------------

    static List<String> names = Arrays.asList("John", "Emma", "Terry", "Brian", "Philip", "George", "Caroline", "Barbara");

    static class Person {
        Integer zipCode;
        String name;

        public Person(int zipCode, String name) {
            this.zipCode = zipCode;
            this.name = name;
        }

        public Integer getZipCode() {
            return zipCode;
        }

        public void setZipCode(Integer zipCode) {
            this.zipCode = zipCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }


    // 1. Create stream of Person
    // 2. Filter persons by name (length>5)
    // 3. Map persons to Map [key: zipCode, value: Set<Persons>]
    // 4. Get stream of map entries
    // 5. Filter values, where list size > 1
    // 6. Print each entry


    static List<Person> persons = Arrays.asList(new Person(03125, "Andrii"), new Person(03125, "Ivan"), new Person(18031, "Petro"), new Person(18031, "Olga"), new Person(03333, "Kiril"));


    public static class SimpleStreamProcessing {
        public static void main(String[] args) {
            Map<Integer, Set<Person>> zipCodeToPersonsMap = persons.stream()
                    .filter(p -> p.getName().length() > 2)
                    .collect(groupingBy(Person::getZipCode, toSet()));

            zipCodeToPersonsMap.entrySet().stream()
                    .filter(entry -> entry.getValue().size() > 1)
                    .forEach(System.out::println);
        }
    }


    //    -------------------------------------------------------------------------------------------------------------


    public static class LongNameCountImperative {
        public static void main(String[] args) {
            int counter = 0;

            for (String name : names) {
                if (name.length() > 5) {
                    counter++;
                }
            }
            System.out.println(counter);

            // The iteration is sequential (external iteration)
            // Filtering and counting are mixed with iteration
            // The code provides "how" implementation

        }
    }

    public static class LongNameCountFunctional {
        public static void main(String[] args) {
            long count = names.stream()
                    .filter(name -> name.length() > 5)
                    .mapToLong(name -> 1L)
                    .reduce(0, Long::sum);
            System.out.println(count);

            // It uses more "what" implementation
            // Lambdas express the behavior
            // Stateless function allows parallel processing
            // Stream provides a lazy view  elements covered by a source

            /* Let's investigate what's inside count and how can we implement it by ourselves */

        }
    }

    //    -------------------------------------------------------------------------------------------------------------

    /**
     * Let's consider the case when we need to combine all names using commas and create a single string.
     * We can user method reduce? Is it a good way to solve this problem? AND WHY NOT?
     */
    public static class CollectAndReduceName {

        public static void main(String[] args) {
            Optional<String> concatenatedNames = names.stream()
                    .reduce((n, m) -> n + ", " + m);

            //Sequential reduce is a fold left, like ((a / b) / c) / d)

            System.out.println(concatenatedNames.orElse("none"));

            String collectedString = names.stream().collect(Collectors.joining(", "));
            System.out.println(collectedString);

        }
    }


    /**
     * Let's look at file State of Lambda and try to play a little bit with it.
     * Using java.nio we can create stream of lines, read from text file.
     * Then we can use pattern to split lines into words.
     * Having a stream of words we can compute different operation, grouping words by length or by number of occurrences.
     * We can filter, sort and collect words.
     */
    public static class CollectAndGroupWords {
        static final Pattern WORDS = Pattern.compile("[\\p{Punct}\\s]+");

        static Path stateOfLambda = Paths.get("in.txt");

        public static void main(String[] args) throws IOException {

            try (Stream<String> lines = Files.lines(stateOfLambda)) {
                Stream<String> words = lines.flatMap(WORDS::splitAsStream);
                Map<String, Long> wordsCountMap = words
                        .filter(word -> word.length() > 5)
                        .collect(groupingBy(identity(), counting()));

                wordsCountMap.entrySet().stream()
                        .filter(entry -> entry.getValue() > 20)
                        .sorted(reverseOrder(Map.Entry.comparingByValue()))
                        .forEach(System.out::println);

                // As we see a lot of Stream API helper methods were added by JDK 8
            }
        }
    }

    /**
     * Going parallel
     */
    public static class ProcessingParallelStreams {
        static final long LIMIT = 1000;
        static final int N = 12;

        public static void main(String[] args) {

            System.out.println("Sequential processing");
            performNTimes(N, () -> LongStream.range(1, LIMIT).count());

            System.out.println("\nParallel processing");
            performNTimes(N, () -> LongStream.range(1, LIMIT).parallel().count());

        }

        static void performNTimes(int n, Runnable r) {
            LongStream.range(0, n).forEach(i -> {
                        long start = System.nanoTime();
                        r.run();
                        System.out.println((System.nanoTime() - start) / 1_000 + "mcs");
                    }
            );
        }
    }

}



