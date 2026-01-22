package memorizingtool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void testAverageFractional() {
        numberMemorize.add(5);
        numberMemorize.add(6);
        outContent.reset();

        numberMemorize.average();

        assertEquals("Average of all elements: 5.5\n", outContent.toString());
    }

    @Test
    public void testSumOverflow() {
        numberMemorize.add(Integer.MAX_VALUE);
        numberMemorize.add(1);
        outContent.reset();

        numberMemorize.sum(0, 1);

        assertEquals("Calculation performed: 2147483647 + 1 = 2147483648\n", outContent.toString());
    }

    @Test
    public void testSubtractUnderflow() {
        numberMemorize.add(Integer.MIN_VALUE);
        numberMemorize.add(1);
        outContent.reset();

        numberMemorize.subtract(0, 1);

        assertEquals("Calculation performed: -2147483648 - 1 = -2147483649\n", outContent.toString());
    }

    @Test
    public void testMultiplyOverflow() {
        numberMemorize.add(100000);
        numberMemorize.add(100000);
        outContent.reset();

        numberMemorize.multiply(0, 1);

        assertEquals("Calculation performed: 100000 * 100000 = 10000000000\n", outContent.toString());
    }

    @Test
    public void testDivideFractionalResult() {
        numberMemorize.add(10);
        numberMemorize.add(3);
        outContent.reset();

        numberMemorize.divide(0, 1);

        assertEquals("Calculation performed: 10 / 3 = 3.333333\n", outContent.toString());
    }

    @Test
    public void testPowOverflow() {
        numberMemorize.add(1000);
        numberMemorize.add(10);
        outContent.reset();

        numberMemorize.pow(0, 1);

        assertEquals("Calculation performed: 1000 ^ 10 = 1000000000000000000\n", outContent.toString());
    }

    @Test
    public void testPowFractionalBase() {
        numberMemorize.add(2);
        numberMemorize.add(-2);
        outContent.reset();

        numberMemorize.pow(0, 1);

        assertEquals("Calculation performed: 2 ^ -2 = 0.25\n", outContent.toString());
    }

    @Test
    public void testSumAllOverflow() {
        numberMemorize.add(Integer.MAX_VALUE);
        numberMemorize.add(Integer.MAX_VALUE);
        outContent.reset();

        numberMemorize.sumAll();

        assertEquals("Sum of all elements: 4294967294\n", outContent.toString());
    }

    @Test
    public void testReadFile() throws Exception {
        numberMemorize.add(1);
        numberMemorize.add(2);
        Path tempFile = Files.createTempFile("number-memorize-read", ".txt");
        try {
            Files.write(tempFile, Arrays.asList("1", "2", "3"), StandardCharsets.UTF_8);
            outContent.reset();

            numberMemorize.readFile(tempFile.toString());

            assertEquals(Arrays.asList(1, 2, 3), numberMemorize.list);
            assertEquals("Data imported: 3\n", outContent.toString());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testWriteFile() throws Exception {
        numberMemorize.add(4);
        numberMemorize.add(5);
        Path tempFile = Files.createTempFile("number-memorize-write", ".txt");
        try {
            outContent.reset();

            numberMemorize.writeFile(tempFile.toString());

            List<String> lines = Files.readAllLines(tempFile, StandardCharsets.UTF_8);
            assertEquals(Arrays.asList("4", "5"), lines);
            assertEquals("Data exported: 2\n", outContent.toString());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

}
