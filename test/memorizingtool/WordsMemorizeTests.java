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
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testConcatTwoStrings() {
        memorize.add("Hello");
        memorize.add("World");
        setOutContent();

        memorize.concat(0, 1);

        assertEquals("Concatenated string: HelloWorld\n", outContent.toString());
    }

    @Test
    public void testSwapCaseUpperToLower() {
        memorize.add("HeLLo");
        setOutContent();

        memorize.swapCase(0);

        assertEquals("\"HeLLo\" string with swapped case: hEllO\n", outContent.toString());
    }

    @Test
    public void testUppercase() {
        memorize.add("hello");
        setOutContent();

        memorize.upper(0);

        assertEquals("Uppercase \"hello\" string: HELLO\n", outContent.toString());
    }

    @Test
    public void testLowercase() {
        memorize.add("HELLO");
        setOutContent();

        memorize.lower(0);

        assertEquals("Lowercase \"HELLO\" string: hello\n", outContent.toString());
    }

    @Test
    public void testReverseString() {
        memorize.add("hello");
        setOutContent();

        memorize.reverse(0);

        assertEquals("Reversed \"hello\" string: olleh\n", outContent.toString());
    }

    @Test
    public void testLength() {
        memorize.add("hello");
        setOutContent();

        memorize.length(0);

        assertEquals("Length of \"hello\" string: 5\n", outContent.toString());
    }

    @Test
    public void testJoinWithDelimiter() {
        memorize.add("apple");
        memorize.add("banana");
        memorize.add("cherry");
        setOutContent();

        memorize.join(",");

        String output = outContent.toString();
        assertEquals("Joined string: apple,banana,cherry\n", output);
    }

    @Test
    public void testRegexMatching() {
        memorize.add("apple");
        memorize.add("apricot");
        memorize.add("banana");
        setOutContent();

        memorize.regex("a.*");

        String output = outContent.toString();
        assertTrue(output.contains("Strings that match provided regex:"));
        assertTrue(output.contains("apple"));
        assertTrue(output.contains("apricot"));
    }

    @Test
    public void testReadFile() throws Exception {
        memorize.add("alpha");
        memorize.add("beta");
        Path tempFile = Files.createTempFile("word-memorize-read", ".txt");
        try {
            Files.write(tempFile, Arrays.asList("apple", "banana", "cherry"), StandardCharsets.UTF_8);
            setOutContent();

            memorize.readFile(tempFile.toString());

            assertEquals(Arrays.asList("alpha", "beta", "apple", "banana", "cherry"), memorize.list);
            assertEquals("Data imported: 3\n", outContent.toString());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testWriteFile() throws Exception {
        memorize.add("foo");
        memorize.add("bar");
        Path tempFile = Files.createTempFile("word-memorize-write", ".txt");
        try {
            setOutContent();

            memorize.writeFile(tempFile.toString());

            List<String> lines = Files.readAllLines(tempFile, StandardCharsets.UTF_8);
            assertEquals(Arrays.asList("foo", "bar"), lines);
            assertEquals("Data exported: 2\n", outContent.toString());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testRegexNoMatches() {
        memorize.add("dog");
        memorize.add("cat");
        setOutContent();

        memorize.regex("z.*");

        assertTrue(outContent.toString().contains("There are no strings that match provided regex"));
    }

    @Test
    public void testRegexInvalidPattern() {
        memorize.add("test");
        setOutContent();

        memorize.regex("[invalid");

        assertTrue(outContent.toString().contains("Incorrect regex pattern provided"));
    }

    private void setOutContent() {
        System.setOut(new PrintStream(outContent));
    }

}
