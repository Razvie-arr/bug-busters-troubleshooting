package memorizingtool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class MemoryTests {

    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    private InputStream originalIn;

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        originalIn = System.in;
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testIncorrectChoiceInMenu() throws Exception {
        String incorrectChoice = "/9";

        try (ByteArrayInputStream in = new ByteArrayInputStream(incorrectChoice.getBytes())) {
            System.setIn(in);
            System.setOut(new PrintStream(outContent));

            Memory.main(new String[0]);

            String expectedOutput = "Incorrect command";
            assertTrue(outContent.toString().contains(expectedOutput));
        }
    }

}
