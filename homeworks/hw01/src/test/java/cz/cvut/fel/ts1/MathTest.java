package cz.cvut.fel.ts1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MathTest {
    @Test
    public void factorialRecursiveTest() {
        Math math = new Math();
        assertEquals(math.factorialRecursive(3), 6);
    }

    @Test
    public void factorialIterativeTest() {
        Math math = new Math();
        assertEquals(math.factorialIterative(3), 6);
    }
}
