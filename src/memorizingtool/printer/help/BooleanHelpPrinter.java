package memorizingtool.printer.help;

public final class BooleanHelpPrinter {

    private BooleanHelpPrinter() {
        // Prevent instantiation
    }

    public static void printHelp() {
        System.out.println(
                "===================================================================================================================\n" +
                        "Boolean-specific commands:\n" +
                        "===================================================================================================================\n" +
                        "/flip [<int> INDEX] - Flip the specified boolean\n" +
                        "/negateAll - Negate all the booleans in memory\n" +
                        "/and [<int> INDEX1] [<int> INDEX2] - Calculate the bitwise AND of the two specified elements\n" +
                        "/or [<int> INDEX1] [<int> INDEX2] - Calculate the bitwise OR of the two specified elements\n" +
                        "/logShift [<int> NUM] - Perform a logical shift of elements in memory by the specified amount\n" +
                        "/convertTo [string/number] - Convert the boolean(bit) sequence in memory to the specified type\n" +
                        "/morse - Convert the boolean(bit) sequence to Morse code\n" +
                        "===================================================================================================================");
    }

}
