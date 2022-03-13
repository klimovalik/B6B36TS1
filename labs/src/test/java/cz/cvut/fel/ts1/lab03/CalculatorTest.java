package cz.cvut.fel.ts1.lab03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    static Calculator calculator;

    @BeforeAll
    public static void initCalculator() {
        calculator = new Calculator();
    }

    @ParameterizedTest(name = "{0} plus {1} should be equal to {2}")
    @CsvSource({"1, 2, 3", "-5, -50, -55", "90, -80, 10"})
    public void add_addsAandB_returnsC(int a, int b, int c) {
// arrange
        int expectedResult = c;
// act
        int actualResult = calculator.add(a, b);
// assert
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "{0} minus {1} should be equal to {2}")
    @CsvSource({"3, 2, 1", "-55, -50, -5", "10, -80, 90"})
    public void subtract_subtractsBfromA_returnsC(int a, int b, int c) {
// arrange
        int expectedResult = c;
// act
        int actualResult = calculator.subtract(a, b);
// assert
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "{0} times {1} should be equal to {2}")
    @CsvSource({"0, 1, 0", "-5, 5, -25", "60, 8, 480"})
    public void multiply_multipliesAandB_returnsC(int a, int b, int c) {
// arrange
        int expectedResult = c;
// act
        int actualResult = calculator.multiply(a, b);
// assert
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "{0} divided by {1} should be equal to {2}")
    @CsvSource({"0, 1, 0", "-25, 5, -5", "480, 8, 60"})
    public void divide_dividesAbyB_returnsC(int a, int b, int c) {
// arrange
        int expectedResult = c;
// act
        int actualResult;
        try {
            actualResult = calculator.divide(a, b);
        } catch (Exception e) {
            actualResult = -100;
        }
// assert
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "{0} divided by 0 should be equal to {2}")
    @CsvSource({"0", "7"})
    public void divide_dividesAby0_throwsException(int a) {
// arrange
        String expectedResult = "Division by 0 is not allowed!";
// act
        Exception exception = Assertions.assertThrows(Exception.class, () -> calculator.divide(a, 0));
        String actualResult = exception.getMessage();
// assert
        assertEquals(expectedResult, actualResult);
    }
}
