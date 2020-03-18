package com.tel_ran;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;

public class Main {

    public static void main(String[] args) {


    }
    public int filter(int[] array) {
        return Arrays
                .stream(array)
                .filter(val -> val % 3 == 0)
                .max()
                .orElseGet(() -> 0);
    }

    public static String collectString(Collection<String> strings) {
        return strings
                .stream()
                .filter(str -> str.length() < 5)
                .map(String::toUpperCase)
                .distinct()
                .reduce((s, s2) -> s + s2)
                .orElse("");
    }

    public static Stream<String> trickyFilter(Collection<String> strings) {
        return strings
                .stream()
                .filter(str -> str.length() < 5)
                .map(String::toUpperCase)
                .distinct();
    }

    public static IntStream toLengthsStream(Collection<String> strings) {
        return strings
                .stream()
                .mapToInt(value -> value.length());
    }

    public static boolean isPrime(int n) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(n))
                .noneMatch(num -> n % num == 0);
    }

    public static long factorial(int n) {
        return LongStream.rangeClosed(1, n)
                .reduce((currentResult, currentNum) -> currentResult * currentNum)
                .orElse(0);
    }
    public static Map<Boolean, List<String>> palindromeSplitting(Collection<String> strings) {
        return strings.stream()
                .collect(partitioningBy(
                        str -> new StringBuilder(str)
                                .reverse()
                                .toString()
                                .equals(str)));
    }


}
