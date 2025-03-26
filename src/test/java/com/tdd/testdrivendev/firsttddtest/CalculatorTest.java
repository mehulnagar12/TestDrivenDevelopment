package com.tdd.testdrivendev.firsttddtest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    @Test
    void divideTwoNumbers() {
        //arrange
        Calculator calc = new Calculator();

        //Test
        double result = calc.divide(6.0, 2.0);

        //assert
        assertEquals(3.0, result);
    }

    @Test
    void divideByZero() {
        Calculator calc = new Calculator();
        assertThrows(ArithmeticException.class, () -> calc.divide(6.0, 0.0));
    }
}
