package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculatorTests {

    @DisplayName("Test addition for multiple cases")
    @ParameterizedTest
    @CsvSource({
        "1.0, -1.0, 0.0",
        "-1.0, 2.0, 1.0",
        "1.2, 0.2, 1.4",
        "1.0, -3.0, -2.0",
        "0.0, 0.0, 0.0"
    })
    void testAddition(double first, double second, double expected) {
        double result = Calculator.add(first, second);
        assertEquals(expected, result);
    }
    
    @DisplayName("Test subtraction for multiple cases")
    @ParameterizedTest
    @CsvSource({"5.0, 2.0, 3.0", "-1.0, 2.0, -3.0", "-1.0, -3.0, 2.0"})
    void testSubtraction(double first, double second, double expected) {
        double result = Calculator.subtract(first, second);
        assertEquals(expected, result);
    }
    
    @DisplayName("Test multiplication for multiple cases")
    @ParameterizedTest
    @CsvSource({"2.0, 2.0, 4.0", "2.0, -2.0, -4.0", "2.0, 0.0, 0.0"})
    void testMultiplication(double first, double second, double expected) {
        double result = Calculator.multiply(first, second);
        assertEquals(expected, result);
    }
    
    @DisplayName("Test division for multiple cases")
    @ParameterizedTest
    @CsvSource({"4.0, 2.0, 2.0", "-4.0, 2.0, -2.0", "4.0, -2.0, -2.0"})
    void testDivision(double first, double second, double expected) {
        double result = Calculator.divide(first, second);
        assertEquals(expected, result);
    }

    @DisplayName("Test division by zero")
    @ParameterizedTest
    @CsvSource({"4.0, 0.0, Cannot divide by zero", "-4.0, 0.0, Cannot divide by zero"})
    void testDivisionByZero(double dividend, double divisor, String expected) {
        ArithmeticException exception = assertThrows(
            ArithmeticException.class, () -> Calculator.divide(dividend, divisor)
        );
        assertEquals(expected, exception.getMessage());
        assertNotNull(exception);
        assertThrows(
            ArithmeticException.class,
            () -> Calculator.divide(dividend, divisor)
        );
    }
    
    @DisplayName("Test square() for multiple cases")
    @ParameterizedTest
    @CsvSource({"2.0, 4.0", "-3.0, 9.0", "0.0, 0,0"})
    void testSquare(double value, double expected) {
        double result = Calculator.square(value);
        assertEquals(expected, result);
    }
    
    @DisplayName("Test sqrt() for multiple cases")
    @ParameterizedTest
    @CsvSource({"9.0, 3.0", "1.0, 1.0", "0.0, 0.0"})
    void testSqrt(double first, double expected) {
        double result = Calculator.sqrt(first);
        assertEquals(expected, result);
    }
    
    @DisplayName("Test percent() for multiple cases")
    @ParameterizedTest
    @CsvSource({"10.0, 0.1", "0.0, 0.0", "-10.0, -0.1"})
    void testPercent(double value, double expected) {
        double result = Calculator.percent(value);
        assertEquals(expected, result);
    }
    
    @DisplayName("Test negate() for multiple cases")
    @ParameterizedTest
    @CsvSource({"1.0, -1.0", "-1.0, 1.0", "0.0, 0.0"})
    void testNegate(double value, double expected) {
        double result = Calculator.negate(value);
        assertEquals(expected, result);
    }
    
    @DisplayName("Test inverse() for multiple cases")
    @ParameterizedTest
    @CsvSource({"2.0, 0.5", "-4.0, -0.25"})
    void testInverse(double value, double expected) {
        double result = Calculator.inverse(value);
        assertEquals(expected, result);
    }
    
    @DisplayName("Test inverse() with division by zero")
    @Test
    void testInverseDivisionByZero() {
        ArithmeticException exception = assertThrows(
            ArithmeticException.class, () -> Calculator.inverse(0.0)
        );
        assertEquals("Cannot divide by zero", exception.getMessage());
        assertNotNull(exception);
    }
}
