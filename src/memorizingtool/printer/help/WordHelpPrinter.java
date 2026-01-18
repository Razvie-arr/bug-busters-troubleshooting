package memorizingtool.printer.help;

public final class WordHelpPrinter {

    private WordHelpPrinter() {
        // Prevent instantiation
    }

    public static void printHelp() {
        System.out.println(
                "===================================================================================================================\n" +
                        "Word-specific commands:\n" +
                        "===================================================================================================================\n" +
                        "/concat [<int> INDEX1] [<int> INDEX2] Concatenate two specified strings\n" +
                        "/swapCase [<int> INDEX] Output swapped case version of the specified string\n" +
                        "/upper [<int> INDEX] Output uppercase version of the specified string\n" +
                        "/lower [<int> INDEX] Output lowercase version of the specified string\n" +
                        "/reverse [<int> INDEX] Output reversed version of the specified string\n" +
                        "/length [<int> INDEX] Get the length of the specified string\n" +
                        "/join [<string> DELIMITER] Join all the strings with the specified delimiter\n" +
                        "/regex [<string> PATTERN] Search for all elements that match the specified regular expression " +
                        "pattern\n" +
                        "===================================================================================================================");
    }

}
