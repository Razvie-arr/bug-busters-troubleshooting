package memorizingtool;//Chapter 5

import memorizingtool.file.FileReaderBase;
import memorizingtool.file.FileReaderWords;
import memorizingtool.printer.help.WordHelpPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Continuing with our theme of memorization, this class is designed to help us remember words or strings.
 * After all, words can be quite elusive, especially when working with large amounts of text.
 * The WordMemorize class provides methods to store and recall words.
 * <p>
 * This class goes a step further to offer additional functionalities specific to words.
 * It has methods like
 * "concatenate" to join multiple words together,
 * "length" to determine the length of a word
 * "reverse" to reverse the order of characters in a word.
 * <p>
 * With the WordMemorize class in our toolkit, we can confidently keep track of important words and manipulate them as needed.
 */
public final class WordMemorize extends MemorizeBase<String> {

    public WordMemorize() {
        super();
    }

    @Override
    protected FileReaderBase<String> getReader() {
        return new FileReaderWords();
    }

    @Override
    public void help() {
        super.help();
        WordHelpPrinter.printHelp();
    }

    @Override
    protected void registerTypeCommands(Map<String, Command> registry) {
        registry.put("/concat", parts -> concat(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        registry.put("/swapCase", parts -> swapCase(Integer.parseInt(parts[1])));
        registry.put("/upper", parts -> upper(Integer.parseInt(parts[1])));
        registry.put("/lower", parts -> lower(Integer.parseInt(parts[1])));
        registry.put("/reverse", parts -> reverse(Integer.parseInt(parts[1])));
        registry.put("/length", parts -> length(Integer.parseInt(parts[1])));
        registry.put("/join", parts -> join(parts[1]));
        registry.put("/regex", parts -> regex(parts[1]));
    }

    @Override
    protected String parseElement(String value) {
        return value;
    }

    //explorer, sharing her discoveries and inspiring others to pursue their own adventures.
    void concat(int i, int j) {
        System.out.println("Concatenated string: " + list.get(i) + list.get(j));
    }

    void swapCase(int i) {
        System.out.printf("\"%s\" string with swapped case: ", list.get(i));
        for (char c : (list.get(i)).toCharArray()) {
            if (Character.isUpperCase(c)) {
                System.out.print(Character.toLowerCase(c));
            } else if (Character.isLowerCase(c)) {
                System.out.print(Character.toUpperCase(c));
            } else {
                System.out.print(c);
            }
        }
        System.out.println();
    }

    void upper(int i) {
        System.out.printf("Uppercase \"%s\" string: %s\n", list.get(i), (list.get(i)).toUpperCase());
    }

    void lower(int i) {
        System.out.printf("Lowercase \"%s\" string: %s\n", list.get(i), (list.get(i)).toLowerCase());
    }

    void reverse(int i) {
        System.out.printf("Reversed \"%s\" string: %s\n", list.get(i), new StringBuilder(list.get(i)).reverse());
    }

    void length(int i) {
        System.out.printf("Length of \"%s\" string: %d\n", list.get(i), (list.get(i)).length());
    }

    void join(String delimiter) {
        System.out.printf("Joined string: %s\n", String.join(delimiter, list));
    }

    void regex(String regex) {
        List<String> matchingElements = new ArrayList<>();
        try {
            Pattern pattern = Pattern.compile(regex);
            for (String element : list) {
                if (pattern.matcher(element).matches()) {
                    matchingElements.add(element);
                }
            }
            
            if (matchingElements.isEmpty()) {
                System.out.println("There are no strings that match provided regex");
                return;
            }

            System.out.println("Strings that match provided regex:");
            System.out.println(Arrays.toString(matchingElements.toArray()));
        } catch (PatternSyntaxException e) {
            System.out.println("Incorrect regex pattern provided");
        }
    }

}
