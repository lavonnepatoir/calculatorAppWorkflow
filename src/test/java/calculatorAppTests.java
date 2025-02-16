import org.junit.Test;
import static org.junit.Assert.*;

public class calculatorAppTests {

    private static final double DELTA = 0.0001;

    @Test
    public void testAdd() {
        assertEquals(10, calculatorApp.add(5, 5), DELTA);
    }

    @Test
    public void testSubtract() {
        assertEquals(3, calculatorApp.subtract(8, 5), DELTA);
    }

    @Test
    public void testMultiply() {
        assertEquals(20, calculatorApp.multiply(4, 5), DELTA);
    }

    @Test
    public void testDivide() {
        assertEquals(2, calculatorApp.divide(10, 5), DELTA);
    }

    @Test
    public void testZeroDivide() {
        assertTrue(Double.isNaN(calculatorApp.divide(5, 0)));
    }
}
