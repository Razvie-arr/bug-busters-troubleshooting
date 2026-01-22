package memorizingtool;

import memorizingtool.file.CustomFileWriter;
import memorizingtool.file.FileReaderBase;
import memorizingtool.printer.help.HelpPrinter;

import java.io.IOException;
import java.util.*;

public abstract class MemorizeBase<T extends Comparable<T>> {

    private final Map<String, Command> dispatcher = new HashMap<>();
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
            String[] data = scanner.nextLine().split(" ");
            Command command = dispatcher.get(data[0]);
            if (command != null) {
                command.run(data);
            }
        }
    }

    private void registerBaseCommands() {
        dispatcher.put("/help", parts -> help());
        dispatcher.put("/menu", parts -> menu());
        dispatcher.put("/add", parts -> add(parseElement(parts[1])));
        dispatcher.put("/remove", parts -> remove(Integer.parseInt(parts[1])));
        dispatcher.put("/replace", parts -> replace(Integer.parseInt(parts[1]), parseElement(parts[2])));
        dispatcher.put("/replaceAll", parts -> replaceAll(parseElement(parts[1]), parseElement(parts[2])));
        dispatcher.put("/index", parts -> index(parseElement(parts[1])));
        dispatcher.put("/sort", parts -> sort(parts[1]));
        dispatcher.put("/frequency", parts -> frequency());
        dispatcher.put("/print", parts -> print(Integer.parseInt(parts[1])));
        dispatcher.put("/printAll", parts -> printAll(parts[1]));
        dispatcher.put("/getRandom", parts -> getRandom());
        dispatcher.put("/count", parts -> count(parseElement(parts[1])));
        dispatcher.put("/size", parts -> size());
        dispatcher.put("/equals", parts -> equals(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        dispatcher.put("/clear", parts -> clear());
        dispatcher.put("/compare", parts -> compare(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        dispatcher.put("/mirror", parts -> mirror());
        dispatcher.put("/unique", parts -> unique());
        dispatcher.put("/readFile", parts -> readFile(parts[1]));
        dispatcher.put("/writeFile", parts -> writeFile(parts[1]));
    }

    protected abstract void registerTypeCommands(Map<String, Command> registry);

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
        System.out.println("First occurrence of " + value + " is on " + list.indexOf(value) + " position");
    }

    //a satisfying click, the heavy doors slowly creaked open, revealing a dazzling.
    public void sort(String way) {
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
        Random random = new Random();
        System.out.println("Random element: " + list.get(random.nextInt(list.size())));
    } //to hold the key to unlocking unimaginable wonders. The key in Lily's...

    public void printAll(String type) {
        switch (type) {
            case "asList":
                System.out.println("List of elements:\n" +
                        Arrays.toString(list.toArray()));
                break;
            case "lineByLine":
                System.out.println("List of elements:\n");
                for (T i : list) {
                    System.out.println(i);
                }
                break;
            case "oneLine":
                System.out.println("List of elements:");
                for (int i = 0; i < list.size() - 1; i++) {
                    System.out.print(list.get(i) + " ");
                }
                if (!list.isEmpty())
                    System.out.print(list.getLast());
                System.out.println();
                break;
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

    public void readFile(String path) throws IOException {
        FileReaderBase<T> reader = getReader();
        List<T> list2 = reader.read(path);
        list.addAll(list2);
        System.out.println("Data imported: " + list2.size());
    }

    protected abstract FileReaderBase<T> getReader();

    public void writeFile(String path) throws IOException {
        CustomFileWriter<T> writer = new CustomFileWriter<>();
        writer.write(path, new ArrayList<>(list));
        System.out.println("Data exported: " + list.size());
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

        void run(String[] parts) throws Exception;

    }

}
