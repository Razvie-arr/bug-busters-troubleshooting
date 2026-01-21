package memorizingtool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WordsMemorizeTests {

    private WordMemorize memorize;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @Before
    public void setUp() {
        memorize = new WordMemorize();
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testConcatTwoStrings() {
        memorize.add("Hello");
        memorize.add("World");
        outContent.reset();

        memorize.concat(0, 1);

        assertEquals("Concatenated string: HelloWorld\n", outContent.toString());
    }

    @Test
    public void testSwapCaseUpperToLower() {
        memorize.add("HeLLo");
        outContent.reset();

        memorize.swapCase(0);

        assertEquals("\"HeLLo\" string with swapped case: hEllO\n", outContent.toString());
    }

    @Test
    public void testUppercase() {
        memorize.add("hello");
        outContent.reset();

        memorize.upper(0);

        assertEquals("Uppercase \"hello\" string: HELLO\n", outContent.toString());
    }

    @Test
    public void testLowercase() {
        memorize.add("HELLO");
        outContent.reset();

        memorize.lower(0);

        assertEquals("Lowercase \"HELLO\" string: hello\n", outContent.toString());
    }

    @Test
    public void testReverseString() {
        memorize.add("hello");
        outContent.reset();

        memorize.reverse(0);

        assertEquals("Reversed \"hello\" string: olleh\n", outContent.toString());
    }

    @Test
    public void testLength() {
        memorize.add("hello");
        outContent.reset();

        memorize.length(0);

        assertEquals("Length of \"hello\" string: 5\n", outContent.toString());
    }

    @Test
    public void testJoinWithDelimiter() {
        memorize.add("apple");
        memorize.add("banana");
        memorize.add("cherry");
        outContent.reset();

        memorize.join(",");

        String output = outContent.toString();
        assertEquals("Joined string: apple,banana,cherry\n", output);
    }

    @Test
    public void testRegexMatching() {
        memorize.add("apple");
        memorize.add("apricot");
        memorize.add("banana");
        outContent.reset();

        memorize.regex("a.*");

        String output = outContent.toString();
        assertTrue(output.contains("Strings that match provided regex:"));
        assertTrue(output.contains("apple"));
        assertTrue(output.contains("apricot"));
    }

    @Test
    public void testCompareGreater() {
        memorize.add("apple");
        memorize.add("banana");
        outContent.reset();

        memorize.compare(1, 0);

        assertEquals("Result: banana > apple\n", outContent.toString());
    }

    @Test
    public void testCompareLess() {
        memorize.add("apple");
        memorize.add("banana");
        outContent.reset();

        memorize.compare(0, 1);

        assertEquals("Result: apple < banana\n", outContent.toString());
    }

    @Test
    public void testCompareEqual() {
        memorize.add("apple");
        memorize.add("apple");
        outContent.reset();

        memorize.compare(0, 1);

        assertEquals("Result: apple = apple\n", outContent.toString());
    }

    @Test
    public void testSortAscending() {
        memorize.add("cherry");
        memorize.add("apple");
        memorize.add("banana");
        outContent.reset();

        memorize.sort("ascending");

        assertEquals("apple", memorize.list.get(0));
        assertEquals("banana", memorize.list.get(1));
        assertEquals("cherry", memorize.list.get(2));
        assertTrue(outContent.toString().contains("ascending"));
    }

    @Test
    public void testSortDescending() {
        memorize.add("cherry");
        memorize.add("apple");
        memorize.add("banana");
        outContent.reset();

        memorize.sort("descending");

        assertEquals("cherry", memorize.list.get(0));
        assertEquals("banana", memorize.list.get(1));
        assertEquals("apple", memorize.list.get(2));
        assertTrue(outContent.toString().contains("descending"));
    }

}
