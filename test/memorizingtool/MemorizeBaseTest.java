package memorizingtool;

import memorizingtool.file.FileReaderBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MemorizeBaseTest {

    private TestMemorizeImpl memorize;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @Before
    public void setUp() {
        memorize = new TestMemorizeImpl();
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testAdd() {
        outContent.reset();
        memorize.add("apple");

        assertEquals(1, memorize.list.size());
        assertEquals("apple", memorize.list.getFirst());
        assertEquals("Element apple added\n", outContent.toString());
    }

    @Test
    public void testRemove() {
        memorize.add("apple");
        memorize.add("banana");
        outContent.reset();

        memorize.remove(0);

        assertEquals(1, memorize.list.size());
        assertEquals("banana", memorize.list.getFirst());
        assertEquals("Element on 0 position removed\n", outContent.toString());
    }

    @Test
    public void testReplace() {
        memorize.add("apple");
        outContent.reset();

        memorize.replace(0, "cherry");

        assertEquals("cherry", memorize.list.getFirst());
        assertEquals("Element on 0 position replaced with cherry\n", outContent.toString());
    }

    @Test
    public void testReplaceAll() {
        memorize.add("apple");
        memorize.add("banana");
        memorize.add("apple");
        outContent.reset();

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
        outContent.reset();

        memorize.index("apple");

        assertEquals("First occurrence of apple is on 0 position\n", outContent.toString());
    }

    @Test
    public void testFrequency() {
        memorize.add("apple");
        memorize.add("banana");
        memorize.add("apple");
        outContent.reset();

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
        outContent.reset();

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
        outContent.reset();

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
        outContent.reset();

        memorize.count("apple");

        assertEquals("Amount of apple: 2\n", outContent.toString());
    }

    @Test
    public void testSize() {
        memorize.add("apple");
        memorize.add("banana");
        outContent.reset();

        memorize.size();

        assertEquals("Amount of elements: 2\n", outContent.toString());
    }

    @Test
    public void testEquals() {
        memorize.add("apple");
        memorize.add("apple");
        outContent.reset();

        memorize.equals(0, 1);

        String output = outContent.toString();
        assertTrue(output.contains("0 and 1 elements are equal:"));
    }

    @Test
    public void testClear() {
        memorize.add("apple");
        memorize.add("banana");
        outContent.reset();

        memorize.clear();

        assertEquals(0, memorize.list.size());
        assertEquals("Data cleared\n", outContent.toString());
    }

    @Test
    public void testMirror() {
        memorize.add("apple");
        memorize.add("banana");
        memorize.add("cherry");
        outContent.reset();

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
        outContent.reset();

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
        protected void registerTypeCommands(Map<String, Command> registry) {
            // No type-specific commands for testing
        }

        @Override
        protected FileReaderBase<String> getReader() {
            throw new UnsupportedOperationException("Not implemented for testing.");
        }

    }


}
