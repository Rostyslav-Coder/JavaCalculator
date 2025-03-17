package org.example;

public class Calculator {
    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        return  a / b;
    }

    public static double square(double a) {
        return Math.pow(a, 2);
    }

    public static double sqrt(double a) {
        return Math.sqrt(a);
    }

    public static double percent(double a) {
        return a / 100;
    }

    public static double inverse(double a) {
        return 1 / a;
    }

    public static double negate(double a) {
        return -a;
    }

}
