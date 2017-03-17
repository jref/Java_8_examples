package com.ua.codespace;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LambdasHM_001 {

    /**
     * Function Factory that allows to manage function by string names.
     */
    static class FunctionFactory<T, R> {
        private Map<String, Function<T, R>> functionMap = new HashMap<>();

        public void addFunction(String name, Function<T, R> function) {
            functionMap.put(name, function);
        }

        public Function<T, R> getFunction(String name) {
            if (functionMap.containsKey(name)) {
                return functionMap.get(name);
            } else {
                throw new InvalidFunctionNameException(name);
            }
        }
    }

    static class InvalidFunctionNameException extends RuntimeException {
        public InvalidFunctionNameException(String functionName) {
            super("Function " + functionName + " doesn't exist.");
        }
    }

    /**
     * Follow the instructions to finish the task
     */
    public static void main(String[] args) {
        FunctionFactory<Integer, Integer> functionFactory = new FunctionFactory<>();

        // 1. add simple functions like "square", "increment", "decrement", "negative"
        // 2. test valid functions: Using functionFactory calculate square of 25
        // 3. add simple function like "abs" using method reference (use class Math)
        // 4. add try/catch block, catch InvalidFunctionNameException and print some error message to the console
        // 5. try to get function with invalid name

    }

}


