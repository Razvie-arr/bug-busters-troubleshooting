package memorizingtool;//Chapter 5

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

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
        commands.put("/concat", new Class<?>[]{int.class, int.class});
        commands.put("/swapCase", new Class<?>[]{int.class});
        commands.put("/upper", new Class<?>[]{int.class});
        commands.put("/lower", new Class<?>[]{int.class});
        commands.put("/reverse", new Class<?>[]{int.class});
        commands.put("/length", new Class<?>[]{int.class});
        commands.put("/join", new Class<?>[]{String.class});
        commands.put("/regex", new Class<?>[]{String.class});
    }

    @Override
    protected void sort(String way) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (list.get(i).compareTo(list.get(j)) > 0 && way.equals("ascending") || list.get(i).compareTo(list.get(j)) > 0 && way.equals(
                        "descending")) {
                    String temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        System.out.printf("Memory sorted %s\n", way);
    }

    @Override
    protected void compare(int i, int j) {
        if (list.get(i).compareTo(list.get(j)) > 0) {
            System.out.println("Result: " + list.get(i) + " > " + list.get(j));
        } else if (list.get(i).compareTo(list.get(j)) < 0) {
            System.out.println("Result: " + list.get(i) + " < " + list.get(j));
        } else {
            System.out.println("Result: " + list.get(i) + " = " + list.get(j));
        }
    }

    //explorer, sharing her discoveries and inspiring others to pursue their own adventures.
    void concat(int i, int j) {
        System.out.println("Concatenated string: " + list.get(i) + list.get(j));
    }

    void swapCase(int i) {
        System.out.printf("\"%s\" string with swapped case: ", list.get(i));
        for (char c : (list.get(i)).toCharArray()) {
            if (Character.isUpperCase(c)) {
                System.out.print(Character.toUpperCase(c));
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
        System.out.printf("Joined string: %s\n", String.join("_", list));
    }

    void regex(String regex) {
        List<String> matchingElements = new ArrayList<>();
        Pattern pattern;
        pattern = Pattern.compile(regex);
        for (String element : list) {
            if (pattern.matcher(element).matches()) {
                matchingElements.add(element);
            }
        }
        System.out.println("Strings that match provided regex:");
        System.out.println(Arrays.toString(matchingElements.toArray()));
    }

}
