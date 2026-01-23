package memorizingtool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testFlip() {
        booleanMemorize.add(false);
        booleanMemorize.add(true);
        setOutContent();

        booleanMemorize.flip(1);

        assertEquals(false, booleanMemorize.list.get(1));
        assertEquals("Element on 1 position flipped\n", outContent.toString());
    }

    @Test
    public void testNegateAll() {
        booleanMemorize.add(true);
        booleanMemorize.add(false);
        setOutContent();

        booleanMemorize.negateAll();

        assertEquals(false, booleanMemorize.list.get(0));
        assertEquals(true, booleanMemorize.list.get(1));
        assertEquals("All elements negated\n", outContent.toString());
    }

    @Test
    public void testAnd() {
        booleanMemorize.add(true);
        booleanMemorize.add(false);
        setOutContent();

        booleanMemorize.and(0, 1);

        assertEquals("Operation performed: (true && false) is false\n", outContent.toString());
    }

    @Test
    public void testOr() {
        booleanMemorize.add(true);
        booleanMemorize.add(false);
        setOutContent();

        booleanMemorize.or(0, 1);

        assertEquals("Operation performed: (true || false) is true\n", outContent.toString());
    }

    @Test
    public void testLogShift() {
        booleanMemorize.add(true);
        for (int i = 0; i < 7; i++) {
            booleanMemorize.add(false);
        }
        setOutContent();

        // expected result after rotating right by 1
        List<Boolean> expected = new ArrayList<>(booleanMemorize.list);
        Collections.rotate(expected, 1);

        booleanMemorize.logShift(1);

        assertEquals(expected, booleanMemorize.list);
        assertEquals("Elements shifted by 1\n", outContent.toString());
    }

    @Test
    public void testConvertToNumber() {
        booleanMemorize.add(true);
        booleanMemorize.add(true);
        booleanMemorize.add(true);
        setOutContent();

        booleanMemorize.convertTo("number");

        assertEquals("Converted: 7\n", outContent.toString());
    }

    @Test
    public void testConvertToString() {
        for (int i = 0; i < 8; i++) {
            booleanMemorize.add(false);
        }
        booleanMemorize.add(true);
        setOutContent();

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
        setOutContent();

        booleanMemorize.morse();

        assertEquals("Morse code: ._.\n", outContent.toString());
    }

    @Test
    public void testReadFile() throws Exception {
        booleanMemorize.add(true);
        booleanMemorize.add(true);
        Path tempFile = Files.createTempFile("boolean-memorize-read", ".txt");
        try {
            Files.write(tempFile, Arrays.asList("true", "false", "true"), StandardCharsets.UTF_8);
            setOutContent();

            booleanMemorize.readFile(tempFile.toString());

            assertEquals(Arrays.asList(true, true, true, false, true), booleanMemorize.list);
            assertEquals("Data imported: 3\n", outContent.toString());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    private void setOutContent() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testWriteFile() throws Exception {
        booleanMemorize.add(true);
        booleanMemorize.add(false);
        Path tempFile = Files.createTempFile("boolean-memorize-write", ".txt");
        try {
            setOutContent();

            booleanMemorize.writeFile(tempFile.toString());

            List<String> lines = Files.readAllLines(tempFile, StandardCharsets.UTF_8);
            assertEquals(Arrays.asList("true", "false"), lines);
            assertEquals("Data exported: 2\n", outContent.toString());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

}