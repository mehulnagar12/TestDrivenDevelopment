package com.tdd.testdrivendev.firsttddtest;

public class Calculator {
    public double divide(double a, double b) {
        if (b == 0){
            throw new ArithmeticException("Divide by zero");
        } else {
            return a / b;
        }
    }
}
