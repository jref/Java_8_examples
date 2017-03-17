package com.ua.codespace;


import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;


public class StreamsHW_001 {


    interface StreamGenerator<T> {
        Stream<T> generateStream(long size);
    }

    static Random random = new Random();

    public static void main(String[] args) {
        /*
         * Implement StreamGenerator using lambda expression and random.
         * Use size for number of elements.
         * Set min value to 0, and max value to size/10
         * */
        StreamGenerator<Long> longStreamGenerator;

        /*
        * Generate stream of 10000 elements.
        * Collect stream to map, with key = element, and value = number of element occurrences
        * */
        Map<Long, Long> longMap;

        /*
        * Create another stream using map as a source
        * Filter entries, where number of occurrences > 10
        * Print each entry
        * */


    }
}
