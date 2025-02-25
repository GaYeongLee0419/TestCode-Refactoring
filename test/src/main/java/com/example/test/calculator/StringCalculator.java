package com.example.test.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    리팩토링
    1. 한 메서드에는 하나의 책임만

 */
public class StringCalculator {
    private String[] split(String input) {
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(input);

        if (m.find()) {
            String customCompiler = m.group(1);
            return m.group(2).split(customCompiler);
        }

        return input.split(",|:");
    }

    private boolean isBlank(String input) {
        return input == null || input.isEmpty();
    }

    private int[] toInts(String[] values) throws Exception {
        int[] numbers = new int[values.length];

        for(int i = 0; i < values.length; i++) {
            numbers[i] = toPositive(values[i]);
        }

        return numbers;
    }

    private int toPositive(String value) throws RuntimeException {
        int number = Integer.parseInt(value);
        if (number < 0) throw new RuntimeException();

        return number;
    }

    private int sum(int[] numbers) {
        return Arrays.stream(numbers).sum();
    }

    public int add(String input) throws Exception {
        if (isBlank(input)) return 0;

        return sum(toInts(split(input)));
    }
}
