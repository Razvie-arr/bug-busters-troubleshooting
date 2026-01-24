package memorizingtool;

import memorizingtool.exception.BooleanCannotBeParsedException;
import memorizingtool.file.CustomFileWriter;
import memorizingtool.file.FileReaderBase;
import memorizingtool.printer.help.HelpPrinter;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.*;

public abstract class MemorizeBase<T extends Comparable<T>> {

    private final Map<String, CommandWrapper> dispatcher = new HashMap<>();
    protected List<T> list = new ArrayList<>();
    protected boolean finished = false;

    public MemorizeBase() {
        registerBaseCommands();
        registerTypeCommands(dispatcher);
    }

    protected abstract T parseElement(String value);

    void Run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (!finished) {
            System.out.println("Perform action:");
            runCommand(scanner.nextLine());
        }
    }

    private void runCommand(String input) throws Exception {
        String[] data = input.split(" ");
        CommandWrapper commandWrapper = dispatcher.get(data[0]);
        if (commandWrapper == null) {
            System.out.println("No such command");
            return;
        }
        try {
            String[] args = Arrays.copyOfRange(data, 1, data.length); // extract command arguments
            commandWrapper.run(args);
        } catch (BooleanCannotBeParsedException | NumberFormatException e) {
            System.out.println("Some arguments can't be parsed!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds!");
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect amount of arguments");
        }
    }

    private void registerBaseCommands() {
        dispatcher.put("/help", new CommandWrapper(parts -> help(), 0));
        dispatcher.put("/menu", new CommandWrapper(parts -> menu(), 0));
        dispatcher.put("/add", new CommandWrapper(parts -> add(parseElement(parts[0])), 1));
        dispatcher.put("/remove", new CommandWrapper(parts -> remove(Integer.parseInt(parts[0])), 1));
        dispatcher.put("/replace", new CommandWrapper(parts -> replace(Integer.parseInt(parts[0]), parseElement(parts[1])), 2));
        dispatcher.put("/replaceAll", new CommandWrapper(parts -> replaceAll(parseElement(parts[0]), parseElement(parts[1])), 2));
        dispatcher.put("/index", new CommandWrapper(parts -> index(parseElement(parts[0])), 1));
        dispatcher.put("/sort", new CommandWrapper(parts -> sort(parts[0]), 1));
        dispatcher.put("/frequency", new CommandWrapper(parts -> frequency(), 0));
        dispatcher.put("/print", new CommandWrapper(parts -> print(Integer.parseInt(parts[0])), 1));
        dispatcher.put("/printAll", new CommandWrapper(parts -> printAll(parts[0]), 1));
        dispatcher.put("/getRandom", new CommandWrapper(parts -> getRandom(), 0));
        dispatcher.put("/count", new CommandWrapper(parts -> count(parseElement(parts[0])), 1));
        dispatcher.put("/size", new CommandWrapper(parts -> size(), 0));
        dispatcher.put("/equals", new CommandWrapper(parts -> equals(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])), 2));
        dispatcher.put("/clear", new CommandWrapper(parts -> clear(), 0));
        dispatcher.put("/compare", new CommandWrapper(parts -> compare(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])), 2));
        dispatcher.put("/mirror", new CommandWrapper(parts -> mirror(), 0));
        dispatcher.put("/unique", new CommandWrapper(parts -> unique(), 0));
        dispatcher.put("/readFile", new CommandWrapper(parts -> readFile(parts[0]), 1));
        dispatcher.put("/writeFile", new CommandWrapper(parts -> writeFile(parts[0]), 1));
    }

    protected abstract void registerTypeCommands(Map<String, CommandWrapper> registry);

    //for any mention of a hidden treasure or a forgotten secret that might hold the key to her discovery.
    public void help() { //a curious young girl named Lily. Lily had a heart full of...
        HelpPrinter.printHelp();
    }

    //One evening, while deep in her research, Lily stumbled upon an ancient map hidden...
    public void menu() {
        this.finished = true;
    }

    public void add(T element) {
        list.add(element);
        System.out.println("Element " + element + " added");
    }

    //chamber filled with sparkling jewels and ancient artifacts.
    public void remove(int index) {
        list.remove(index);
        System.out.println("Element on " + index + " position removed");
    }

    public void replace(int index, T element) {
        list.set(index, element);
        System.out.println("Element on " + index + " position replaced with " + element);
    }

    //adventure and a mind hungry for knowledge. Every day, she would wander through the...
    public void replaceAll(T from, T to) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(from)) {
                list.set(i, to);
            }
        }
        System.out.println("Each " + from + " element replaced with " + to);
    }

    //within the pages of an old book. The map depicted a hidden cave at the summit of the tallest hill, rumored...
    public void index(T value) {
        int index = list.indexOf(value);
        if (index == -1) {
            System.out.println("There is no such element");
            return;
        }

        System.out.println("First occurrence of " + value + " is on " + list.indexOf(value) + " position");
    }

    //a satisfying click, the heavy doors slowly creaked open, revealing a dazzling.
    public void sort(String way) {
        if (!"ascending".equals(way) && !"descending".equals(way)) {
            System.out.println("Incorrect argument, possible arguments: ascending, descending");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (list.get(i).compareTo(list.get(j)) > 0 && way.equals("ascending") || list.get(i).compareTo(list.get(j)) < 0 && way.equals(
                        "descending")) {
                    T temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        System.out.printf("Memory sorted %s\n", way);
    }

    //And so, Lily's unwavering curiosity and determination led her to a treasure...
    public void frequency() {
        if (list.isEmpty()) {
            System.out.println("There are no elements in a list");
            return;
        }

        Map<T, Long> counts = new HashMap<>();
        for (T element : list) {
            if (counts.get(element) == null) {
                counts.put(element, 1L);
            } else {
                counts.put(element, counts.get(element) + 1);
            }
        }

        System.out.println("Frequency:");
        for (Map.Entry<T, Long> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void print(int index) {
        System.out.println("Element on " + index + " position is " + list.get(index));
    }

    //trove of knowledge and beauty. From that day forward, she became known as the village's greatest...
    public void getRandom() {
        if (list.isEmpty()) {
            System.out.println("There are no elements memorized");
            return;
        }
        Random random = new Random();
        System.out.println("Random element: " + list.get(random.nextInt(list.size())));
    } //to hold the key to unlocking unimaginable wonders. The key in Lily's...

    public void printAll(String type) {
        switch (type) {
            case "asList": {
                System.out.println("List of elements:\n" +
                        Arrays.toString(list.toArray()));
                break;
            }
            case "lineByLine": {
                System.out.println("List of elements:\n");
                for (T i : list) {
                    System.out.println(i);
                }
                break;
            }
            case "oneLine": {
                System.out.println("List of elements:");
                for (int i = 0; i < list.size() - 1; i++) {
                    System.out.print(list.get(i) + " ");
                }
                if (!list.isEmpty())
                    System.out.print(list.getLast());
                System.out.println();
                break;
            }
            default: {
                System.out.println("Incorrect argument, possible arguments: asList, lineByLine, oneLine");
            }
        }
    }

    //village, observing the world around her and asking questions that often left the villagers perplexed...
    public void count(T value) {
        int amount = 0;
        for (T i : list) {
            if (i.equals(value)) {
                amount++;
            }
        }
        System.out.println("Amount of " + value + ": " + amount);
    }

    public void size() {
        System.out.println("Amount of elements: " + list.size());
    }

    public void equals(int i, int j) {
        boolean res = list.get(i).equals(list.get(j));
        System.out.printf("%d and %d elements are%s equal: %s\n",
                i, j, res ? "" : " not", list.get(i) + (res ? " = " : " != ") + list.get(j));
    }

    public void readFile(String path) {
        try {
            FileReaderBase<T> reader = getReader();
            List<T> list2 = reader.read(path);
            list.addAll(list2);
            System.out.println("Data imported: " + list2.size());
        } catch (NoSuchFileException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file");
        } catch (Exception e) {
            System.out.println("Some error occurred: " + e.getMessage());
        }
    }

    protected abstract FileReaderBase<T> getReader();

    public void writeFile(String path) {
        try {
            CustomFileWriter<T> writer = new CustomFileWriter<>();
            writer.write(path, new ArrayList<>(list));
            System.out.println("Data exported: " + list.size());
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file");
        } catch (Exception e) {
            System.out.println("Some error occurred: " + e.getMessage());
        }
    }

    public void clear() {
        list.clear();
        System.out.println("Data cleared");
    }

    //possession seemed to match the one shown on the map.
    public void compare(int i, int j) {
        if (list.get(i).compareTo(list.get(j)) > 0) {
            System.out.println("Result: " + list.get(i) + " > " + list.get(j));
        } else if (list.get(i).compareTo(list.get(j)) < 0) {
            System.out.println("Result: " + list.get(i) + " < " + list.get(j));
        } else {
            System.out.println("Result: " + list.get(i) + " = " + list.get(j));
        }
    }

    //With the map as her guide, Lily set out on an arduous journey up the treacherous hill, navigating through...
    public void mirror() {
        ArrayList<T> list2 = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            list2.add(list.get(i));
        }
        list = list2;
        System.out.println("Data reversed");
    }

    public void unique() {
        Map<T, Long> counts = new HashMap<>();
        for (T i : list) {
            if (counts.get(i) == null) {
                counts.put(i, 1L);
            } else {
                counts.put(i, counts.get(i) + 1);
            }
        }
        ArrayList<T> list2 = new ArrayList<>();
        for (Map.Entry<T, Long> entry : counts.entrySet()) {
            list2.add(entry.getKey());
        }
        System.out.println("Unique values: " + Arrays.toString(list2.toArray()));
    }

    @FunctionalInterface
    protected interface Command {

        void run(String... args) throws Exception;

    }

    protected static class CommandWrapper {

        private final Command command;
        private final int expectedArgumentsCount;

        public CommandWrapper(Command command, int expectedArgumentsCount) {
            this.command = command;
            this.expectedArgumentsCount = expectedArgumentsCount;
        }

        public void run(String... args) throws Exception {
            if (args.length != expectedArgumentsCount) {
                throw new IllegalArgumentException();
            }
            command.run(args);
        }

    }

}
