package memorizingtool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BooleanMemorizeTests {

    private BooleanMemorize booleanMemorize;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @Before
    public void setUp() {
        booleanMemorize = new BooleanMemorize();
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testFlip() {
        booleanMemorize.add(false);
        booleanMemorize.add(true);
        outContent.reset();

        booleanMemorize.flip(1);

        assertEquals(false, booleanMemorize.list.get(1));
        assertEquals("Element on 1 position flipped\n", outContent.toString());
    }

    @Test
    public void testNegateAll() {
        booleanMemorize.add(true);
        booleanMemorize.add(false);
        outContent.reset();

        booleanMemorize.negateAll();

        assertEquals(false, booleanMemorize.list.get(0));
        assertEquals(true, booleanMemorize.list.get(1));
        assertEquals("All elements negated\n", outContent.toString());
    }

    @Test
    public void testAnd() {
        booleanMemorize.add(true);
        booleanMemorize.add(false);
        outContent.reset();

        booleanMemorize.and(0, 1);

        assertEquals("Operation performed: (true && false) is false\n", outContent.toString());
    }

    @Test
    public void testOr() {
        booleanMemorize.add(true);
        booleanMemorize.add(false);
        outContent.reset();

        booleanMemorize.or(0, 1);

        assertEquals("Operation performed: (true || false) is true\n", outContent.toString());
    }

    @Test
    public void testLogShift() {
        booleanMemorize.add(true);
        for (int i = 0; i < 7; i++) {
            booleanMemorize.add(false);
        }
        outContent.reset();

        booleanMemorize.logShift(1);

        assertEquals(false, booleanMemorize.list.get(0));
        assertEquals(true, booleanMemorize.list.get(7));
        assertEquals("Elements shifted by 1\n", outContent.toString());
    }

    @Test
    public void testConvertToNumber() {
        booleanMemorize.add(true);
        booleanMemorize.add(true);
        booleanMemorize.add(true);
        outContent.reset();

        booleanMemorize.convertTo("number");

        assertEquals("Converted: 7\n", outContent.toString());
    }

    @Test
    public void testConvertToString() {
        for (int i = 0; i < 8; i++) {
            booleanMemorize.add(false);
        }
        booleanMemorize.add(true);
        outContent.reset();

        booleanMemorize.convertTo("string");

        String output = outContent.toString();
        assertTrue(output.startsWith("Amount of elements is not divisible by 8"));
        assertTrue(output.contains("Converted:"));
    }

    @Test
    public void testMorse() {
        booleanMemorize.add(true);
        booleanMemorize.add(false);
        booleanMemorize.add(true);
        outContent.reset();

        booleanMemorize.morse();

        assertEquals("Morse code: ._.\n", outContent.toString());
    }

    @Test
    public void testSortAscending() {
        booleanMemorize.add(false);
        booleanMemorize.add(true);
        booleanMemorize.add(false);
        outContent.reset();

        booleanMemorize.sort("ascending");

        assertEquals(false, booleanMemorize.list.get(0));
        assertEquals(false, booleanMemorize.list.get(1));
        assertEquals(true, booleanMemorize.list.get(2));
        assertEquals("Memory sorted ascending\n", outContent.toString());
    }

    @Test
    public void testSortDescending() {
        booleanMemorize.add(false);
        booleanMemorize.add(true);
        booleanMemorize.add(false);
        outContent.reset();

        booleanMemorize.sort("descending");

        assertEquals(true, booleanMemorize.list.get(0));
        assertEquals(false, booleanMemorize.list.get(1));
        assertEquals(false, booleanMemorize.list.get(2));
        assertEquals("Memory sorted descending\n", outContent.toString());
    }

    @Test
    public void testCompareGreaterThan() {
        booleanMemorize.add(true);
        booleanMemorize.add(false);
        outContent.reset();

        booleanMemorize.compare(0, 1);

        assertEquals("Result: true > false\n", outContent.toString());
    }

    @Test
    public void testCompareLessThan() {
        booleanMemorize.add(false);
        booleanMemorize.add(true);
        outContent.reset();

        booleanMemorize.compare(0, 1);

        assertEquals("Result: false < true\n", outContent.toString());
    }

    @Test
    public void testCompareEqual() {
        booleanMemorize.add(true);
        booleanMemorize.add(true);
        outContent.reset();

        booleanMemorize.compare(0, 1);

        assertEquals("Result: true = true\n", outContent.toString());
    }

}