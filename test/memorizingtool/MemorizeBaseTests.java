package memorizingtool;

import memorizingtool.file.FileReaderBase;
import memorizingtool.file.FileReaderWords;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MemorizeBaseTests {

    private TestMemorizeImpl memorize;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    private InputStream originalIn;

    @Before
    public void setUp() {
        memorize = new TestMemorizeImpl();
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
    public void testAdd() {
        setOutContent();
        memorize.add("apple");

        assertEquals(1, memorize.list.size());
        assertEquals("apple", memorize.list.getFirst());
        assertEquals("Element apple added\n", outContent.toString());
    }

    @Test
    public void testRemove() {
        memorize.add("apple");
        memorize.add("banana");
        setOutContent();

        memorize.remove(0);

        assertEquals(1, memorize.list.size());
        assertEquals("banana", memorize.list.getFirst());
        assertEquals("Element on 0 position removed\n", outContent.toString());
    }

    @Test
    public void testReplace() {
        memorize.add("apple");
        setOutContent();

        memorize.replace(0, "cherry");

        assertEquals("cherry", memorize.list.getFirst());
        assertEquals("Element on 0 position replaced with cherry\n", outContent.toString());
    }

    @Test
    public void testReplaceAll() {
        memorize.add("apple");
        memorize.add("banana");
        memorize.add("apple");
        setOutContent();

        memorize.replaceAll("apple", "date");

        assertEquals(3, memorize.list.size());
        assertEquals("date", memorize.list.get(0));
        assertEquals("date", memorize.list.get(2));
        assertEquals("banana", memorize.list.get(1));
        assertEquals("Each apple element replaced with date\n", outContent.toString());
    }

    @Test
    public void testIndex() {
        memorize.add("apple");
        memorize.add("banana");
        setOutContent();

        memorize.index("apple");

        assertEquals("First occurrence of apple is on 0 position\n", outContent.toString());
    }

    @Test
    public void testFrequency() {
        memorize.add("apple");
        memorize.add("banana");
        memorize.add("apple");
        setOutContent();

        memorize.frequency();

        String output = outContent.toString();
        assertTrue(output.contains("Frequency:"));
        assertTrue(output.contains("apple: 2"));
        assertTrue(output.contains("banana: 1"));
    }

    @Test
    public void testPrint() {
        memorize.add("apple");
        memorize.add("banana");
        setOutContent();

        memorize.print(1);

        assertEquals("Element on 1 position is banana\n", outContent.toString());
    }

    @Test
    public void testGetRandom() {
        memorize.add("apple");
        memorize.add("banana");
        memorize.add("cherry");
        memorize.add("date");
        memorize.add("elderberry");
        setOutContent();

        boolean foundDifferent = false;
        String firstResult = null;

        for (int i = 0; i < 20; i++) {
            outContent.reset();
            memorize.getRandom();

            String output = outContent.toString();
            assertTrue(output.startsWith("Random element:"));

            if (firstResult == null) {
                firstResult = output;
            } else if (!output.equals(firstResult)) {
                foundDifferent = true;
                break;
            }
        }

        assertTrue("getRandom should return different elements, not always the same", foundDifferent);
    }

    @Test
    public void testPrintAll() {
        memorize.add("apple");
        memorize.add("banana");
        setOutContent();

        memorize.printAll("oneLine");

        String output = outContent.toString();
        assertTrue(output.startsWith("List of elements:"));
        assertTrue(output.contains("apple"));
        assertTrue(output.contains("banana"));
    }

    @Test
    public void testCount() {
        memorize.add("apple");
        memorize.add("apple");
        memorize.add("banana");
        setOutContent();

        // to force equals method usage, to avoid getting it from string pool
        memorize.count(new String("apple"));

        assertEquals("Amount of apple: 2\n", outContent.toString());
    }

    @Test
    public void testSize() {
        memorize.add("apple");
        memorize.add("banana");
        setOutContent();

        memorize.size();

        assertEquals("Amount of elements: 2\n", outContent.toString());
    }

    @Test
    public void testEquals() {
        memorize.add("apple");
        memorize.add("apple");
        setOutContent();

        memorize.equals(0, 1);

        String output = outContent.toString();
        assertTrue(output.contains("0 and 1 elements are equal:"));
    }

    @Test
    public void testClear() {
        memorize.add("apple");
        memorize.add("banana");
        setOutContent();

        memorize.clear();

        assertEquals(0, memorize.list.size());
        assertEquals("Data cleared\n", outContent.toString());
    }

    @Test
    public void testMirror() {
        memorize.add("apple");
        memorize.add("banana");
        memorize.add("cherry");
        setOutContent();

        memorize.mirror();

        assertEquals("cherry", memorize.list.get(0));
        assertEquals("banana", memorize.list.get(1));
        assertEquals("apple", memorize.list.get(2));
        assertEquals("Data reversed\n", outContent.toString());
    }

    @Test
    public void testUnique() {
        memorize.add("apple");
        memorize.add("banana");
        memorize.add("apple");
        setOutContent();

        memorize.unique();

        String output = outContent.toString();
        assertTrue(output.startsWith("Unique values:"));
        assertTrue(output.contains("apple"));
        assertTrue(output.contains("banana"));
    }

    @Test
    public void testSortAscending() {
        memorize.add("cherry");
        memorize.add("apple");
        memorize.add("banana");
        setOutContent();

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
        setOutContent();

        memorize.sort("descending");

        assertEquals("cherry", memorize.list.get(0));
        assertEquals("banana", memorize.list.get(1));
        assertEquals("apple", memorize.list.get(2));
        assertTrue(outContent.toString().contains("descending"));
    }

    @Test
    public void testIndexOfNonExistingElement() {
        setOutContent();

        memorize.index("nonexistent");

        assertTrue(outContent.toString().contains("There is no such element"));
    }

    @Test
    public void testSortWithInvalidOrder() {
        memorize.add("apple");
        memorize.add("banana");
        setOutContent();

        memorize.sort("invalidOrder");

        assertTrue(outContent.toString().contains("Incorrect argument, possible arguments: ascending, descending"));
    }

    @Test
    public void testFrequencyEmptyList() {
        setOutContent();

        memorize.frequency();

        assertTrue(outContent.toString().contains("There are no elements in a list"));
    }

    @Test
    public void testGetRandomEmptyList() {
        setOutContent();

        memorize.getRandom();

        assertTrue(outContent.toString().contains("There are no elements memorized"));
    }

    @Test
    public void testReadNonExistingFile() {
        setOutContent();

        memorize.readFile("non_existing_file.txt");

        assertTrue(outContent.toString().contains("File not found!"));
    }

    @Test
    public void testPrintAllWithInvalidFormat() {
        memorize.add("apple");
        memorize.add("banana");
        setOutContent();

        memorize.printAll("invalidFormat");

        assertTrue(outContent.toString().contains("Incorrect argument, possible arguments: asList, lineByLine, oneLine"));
    }

    @Test
    public void testNoSuchCommand() throws Exception {
        String input = "/nonExistingCommand 123\n/menu\n";
        setInContent(input);
        setOutContent();

        memorize.Run();

        assertTrue(outContent.toString().contains("No such command"));
    }

    @Test
    public void testIncorrectAmountOfArguments() throws Exception {
        String input = "/add hello world\n/menu\n"; // should be 1 argument only
        setInContent(input);
        setOutContent();

        memorize.Run();

        assertTrue(outContent.toString().contains("Incorrect amount of arguments"));
    }

    @Test
    public void testIndexOutOfBounds() throws Exception {
        memorize.add("apple");
        String input = "/print 100\n/menu\n";
        setInContent(input);
        setOutContent();

        memorize.Run();

        assertTrue(outContent.toString().contains("Index out of bounds!"));
    }

    @Test
    public void testNumberFormatExceptionHandled() throws Exception {
        String input = "/print apple\n/menu\n";
        setInContent(input);
        setOutContent();

        memorize.Run();

        assertTrue(outContent.toString().contains("Some arguments can't be parsed!"));
    }


    private void setInContent(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    }

    private void setOutContent() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Concrete implementation of MemorizeBase for testing purposes.
     * Uses String as the generic type for simplicity and flexibility.
     */
    static final class TestMemorizeImpl extends MemorizeBase<String> {

        public TestMemorizeImpl() {
            super();
        }

        @Override
        protected String parseElement(String value) {
            return value;
        }

        @Override
        protected void registerTypeCommands(Map<String, CommandWrapper> registry) {
            // No type-specific commands for testing
        }

        @Override
        protected FileReaderBase<String> getReader() {
            return new FileReaderWords();
        }

    }


}
