package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTests {

    @Test
    @DisplayName("Test addition for multiple cases")
    void testAddition() {
        assertEquals(3.0, Calculator.add(1.0, 2.0));
        assertEquals(1.0, Calculator.add(-1.0, 2.0));
        assertEquals(0.0, Calculator.add(0.0, 0.0));
        assertEquals(4.0, Calculator.add(1.5, 2.5));
        assertEquals(-3.0, Calculator.add(2.0, -5.0));
    }
    
    @Test
    @DisplayName("Test subtraction for multiple cases")
    void testSubtraction() {
        assertEquals(3.0, Calculator.subtract(5.0, 2.0));
        assertEquals(-3.0, Calculator.subtract(-1.0, 2.0));
        assertEquals(2.0, Calculator.subtract(-1.0, -3.0));
    }
    
    @Test
    @DisplayName("Test multiplication for multiple cases")
    void testMultiplication() {
        assertEquals(4.0, Calculator.multiply(2.0, 2.0));
        assertEquals(-4.0, Calculator.multiply(2.0, -2.0));
        assertEquals(0.0, Calculator.multiply(2.0, 0.0));
    }
    
    @Test
    @DisplayName("Test division for multiple cases")
    void testDivision() {
        assertEquals(2.0, Calculator.divide(4.0, 2.0));
        assertEquals(-2.0, Calculator.divide(-4.0, 2.0));
        assertEquals(-2.0, Calculator.divide(4.0, -2.0));
        ArithmeticException exception = assertThrows(
            ArithmeticException.class, () -> Calculator.divide(4.0, 0.0)
        );
        assertNotNull(exception);
        assertEquals("Cannot divide by zero", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test square() for multiple cases")
    void testSquare() {
        assertEquals(4.0, Calculator.square(2.0));
        assertEquals(9.0, Calculator.square(-3.0));
        assertEquals(0.0, Calculator.square(0.0));
    }
    
    @Test
    @DisplayName("Test sqrt() for multiple cases")
    void testSqrt() {
        assertEquals(3.0, Calculator.sqrt(9.0));
        assertEquals(5.0, Calculator.sqrt(25.0));
        assertEquals(0.0, Calculator.sqrt(0.0));
    }
    
    @Test
    @DisplayName("Test percent() for multiple cases")
    void testPercent() {
        assertEquals(0.1, Calculator.percent(10.0));
        assertEquals(0.0, Calculator.percent(0.0));
        assertEquals(-0.5, Calculator.percent(-50.0));
    }
    
    @Test
    @DisplayName("Test inverse() for multiple cases")
    void testInverse() {
        assertEquals(0.5, Calculator.inverse(2.0));
        assertEquals(-0.25, Calculator.inverse(-4.0));
        ArithmeticException exception = assertThrows(
            ArithmeticException.class, () -> Calculator.inverse(0.0)
        );
        assertNotNull(exception);
        assertEquals("Cannot divide by zero", exception.getMessage());
        }

    @Test
    @DisplayName("Test negate() for multiple cases")
    void testNegate() {
        assertEquals(-5.0, Calculator.negate(5.0));
        assertEquals(10.0, Calculator.negate(-10.0));
        assertEquals(0.0, Calculator.negate(0.0));
    }

}
