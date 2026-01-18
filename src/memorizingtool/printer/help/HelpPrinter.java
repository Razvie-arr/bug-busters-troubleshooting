package memorizingtool.printer.help;

public final class HelpPrinter {

    private HelpPrinter() {
        // Prevent instantiation
    }

    public static void printHelp() {
        System.out.println(
                "===================================================================================================================\n" +
                        "Usage: COMMAND [<TYPE> PARAMETERS]\n" +
                        "===================================================================================================================\n" +
                        "General commands:\n" +
                        "===================================================================================================================\n" +
                        "/help - Display this help message\n" +
                        "/menu - Return to the menu\n" +
                        "\n" +
                        "/add [<T> ELEMENT] - Add the specified element to the list\n" +
                        "/remove [<int> INDEX] - Remove the element at the specified index from the list\n" +
                        "/replace [<int> INDEX] [<T> ELEMENT] - Replace the element at specified index with the new one\n" +
                        "/replaceAll [<T> OLD] [<T> NEW] - Replace all occurrences of specified element with the new " +
                        "one\n" +
                        "\n" +
                        "/index [<T> ELEMENT] - Get the index of the first specified element in the list\n" +
                        "/sort [ascending/descending] - Sort the list in ascending or descending order\n" +
                        "/frequency - The frequency count of each element in the list\n" +
                        "/print [<int> INDEX] - Print the element at the specified index in the list\n" +
                        "/printAll [asList/lineByLine/oneLine] - Print all elements in the list in specified format\n" +
                        "/getRandom - Get a random element from the list\n" +
                        "/count [<T> ELEMENT] - Count the number of occurrences of the specified element in the list\n" +
                        "/size - Get the number of elements in the list\n" +
                        "/equals [<int> INDEX1] [<int> INDEX2] - Check if two elements are equal\n" +
                        "/clear - Remove all elements from the list\n" +
                        "/compare [<int> INDEX1] [<int> INDEX2] Compare elements at the specified indices in the list\n" +
                        "/mirror - Mirror elements' positions in list\n" +
                        "/unique - Unique elements in the list\n" +
                        "/readFile [<string> FILENAME] - Import data from the specified file and add it to the list\n" +
                        "/writeFile [<string> FILENAME] - Export the list data to the specified file");
    }

}
