package memorizingtool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class NumberMemorizeTests {

    private NumberMemorize numberMemorize;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @Before
    public void setUp() {
        numberMemorize = new NumberMemorize();
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testSort() {
        numberMemorize.add(5);
        numberMemorize.add(2);
        numberMemorize.add(8);
        numberMemorize.add(1);
        outContent.reset();

        numberMemorize.sort("ascending");

        assertEquals(1, (int) numberMemorize.list.get(0));
        assertEquals(2, (int) numberMemorize.list.get(1));
        assertEquals(5, (int) numberMemorize.list.get(2));
        assertEquals(8, (int) numberMemorize.list.get(3));
        assertEquals("Memory sorted ascending\n", outContent.toString());
    }

    @Test
    public void testCompare() {
        numberMemorize.add(10);
        numberMemorize.add(5);
        outContent.reset();

        numberMemorize.compare(0, 1);

        assertEquals("Result: 10 > 5\n", outContent.toString());
    }

    @Test
    public void testSum() {
        numberMemorize.add(7);
        numberMemorize.add(3);
        outContent.reset();

        numberMemorize.sum(0, 1);

        assertEquals("Calculation performed: 7 + 3 = 10\n", outContent.toString());
    }

    @Test
    public void testSubtract() {
        numberMemorize.add(10);
        numberMemorize.add(4);
        outContent.reset();

        numberMemorize.subtract(0, 1);

        assertEquals("Calculation performed: 10 - 4 = 6\n", outContent.toString());
    }

    @Test
    public void testMultiply() {
        numberMemorize.add(6);
        numberMemorize.add(7);
        outContent.reset();

        numberMemorize.multiply(0, 1);

        assertEquals("Calculation performed: 6 * 7 = 42\n", outContent.toString());
    }

    @Test
    public void testDivide() {
        numberMemorize.add(20);
        numberMemorize.add(4);
        outContent.reset();

        numberMemorize.divide(0, 1);

        assertEquals("Calculation performed: 20 / 4 = 5\n", outContent.toString());
    }

    @Test
    public void testPow() {
        numberMemorize.add(2);
        numberMemorize.add(8);
        outContent.reset();

        numberMemorize.pow(0, 1);

        assertEquals("Calculation performed: 2 ^ 8 = 256\n", outContent.toString());
    }

    @Test
    public void testFactorial() {
        numberMemorize.add(5);
        outContent.reset();

        numberMemorize.factorial(0);

        assertEquals("Calculation performed: 5! = 120\n", outContent.toString());
    }

    @Test
    public void testFactorialZero() {
        numberMemorize.add(0);
        outContent.reset();

        numberMemorize.factorial(0);

        assertEquals("Calculation performed: 0! = 1\n", outContent.toString());
    }

    @Test
    public void testFactorialOne() {
        numberMemorize.add(1);
        outContent.reset();

        numberMemorize.factorial(0);

        assertEquals("Calculation performed: 1! = 1\n", outContent.toString());
    }

    @Test
    public void testSumAll() {
        numberMemorize.add(5);
        numberMemorize.add(10);
        numberMemorize.add(15);
        outContent.reset();

        numberMemorize.sumAll();

        assertEquals("Sum of all elements: 30\n", outContent.toString());
    }

    @Test
    public void testAverage() {
        numberMemorize.add(10);
        numberMemorize.add(20);
        numberMemorize.add(30);
        outContent.reset();

        numberMemorize.average();

        assertEquals("Average of all elements: 20\n", outContent.toString());
    }

}
