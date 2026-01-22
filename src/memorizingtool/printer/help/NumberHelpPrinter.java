package memorizingtool.printer.help;

public final class NumberHelpPrinter {

    private NumberHelpPrinter() {
        // Prevent instantiation
    }

    public static void printHelp() {
        System.out.println(
                "===================================================================================================================\n" +
                        "Number-specific commands:\n" +
                        "===================================================================================================================\n" +
                        "/sum [<int> INDEX1] [<int> INDEX2] - Calculate the sum of the two specified elements\n" +
                        "/subtract [<int> INDEX1] [<int> INDEX2] - Calculate the difference between the two specified " +
                        "elements\n" +
                        "/multiply [<int> INDEX1] [<int> INDEX2] - Calculate the product of the two specified elements\n" +
                        "/divide [<int> INDEX1] [<int> INDEX2] - Calculate the division of the two specified elements\n" +
                        "/pow [<int> INDEX1] [<int> INDEX2] - Calculate the power of the specified element to the " +
                        "specified exponent element\n" +
                        "/factorial [<int> INDEX] - Calculate the factorial of the specified element\n" +
                        "/sumAll - Calculate the sum of all elements\n" +
                        "/average - Calculate the average of all elements\n" +
                        "===================================================================================================================");
    }

}
