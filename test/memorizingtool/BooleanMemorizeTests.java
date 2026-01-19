package memorizingtool;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BooleanMemorizeTests {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final BooleanMemorize booleanMemorize = new BooleanMemorize();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAddTrue() {
        booleanMemorize.add(true);

        assertOutputEquals("Element  true  added");
        assertEquals(List.of(true), BooleanMemorize.list);
    }

    @Test
    public void testAddFalse() {
        booleanMemorize.add(false);

        assertOutputEquals("Element  false  added");
        assertBooleanListEquals(List.of(false));
    }

    @Test
    public void testRemove() {
        booleanMemorize.add(true);
        booleanMemorize.add(false);
        booleanMemorize.remove(0);

        assertOutputHas("");
        assertBooleanListEquals(List.of(false));
    }

    @Test
    public void testReplace() {
        booleanMemorize.add(true);
        assertBooleanListEquals(List.of(true));

        booleanMemorize.replace(0, false);

        assertOutputHas("Element on 0 position replaced with false");
        assertBooleanListEquals(List.of(false));
    }

    private void assertOutputEquals(String expected) {
        String actual = outContent.toString().trim();
        assertEquals(expected, actual);
    }

    private void assertOutputHas(String expectedSubstring) {
        String actual = outContent.toString().trim();
        assertTrue(actual.contains(expectedSubstring));
    }

    private void assertBooleanListEquals(List<Boolean> expectedList) {
        assertEquals(expectedList, BooleanMemorize.list);
    }

}
