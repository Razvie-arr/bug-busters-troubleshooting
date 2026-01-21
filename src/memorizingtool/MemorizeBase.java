package memorizingtool;

import memorizingtool.file.*;
import memorizingtool.printer.help.HelpPrinter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MemorizeBase<T extends Comparable<T>> {

    protected final List<T> list = new ArrayList<>();
    protected final Map<String, Class<?>[]> commands;
    protected boolean finished = false;

    public MemorizeBase() {
        commands = new HashMap<>();
        fillGeneralCommands();
    }

    //a satisfying click, the heavy doors slowly creaked open, revealing a dazzling...
    void Run() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        while (!finished) {
            List<Object> args = new ArrayList<>();
            System.out.println("Perform action:");
            String[] data = scanner.nextLine().split(" ");

            for (int i = 1; i < data.length; i++) {
                if (commands.get(data[0])[i - 1].equals(int.class))
                    args.add(Integer.parseInt(data[i]));
                else if (commands.get(data[0])[i - 1].equals(Boolean.class)) {
                    args.add(data[i].equals("true"));
                } else {
                    args.add(data[i]);
                }
            }

            this.getClass().getDeclaredMethod(data[0].substring(1), commands.get(data[0])).invoke(this, args.toArray());
        }
    }

    //for any mention of a hidden treasure or a forgotten secret that might hold the key to her discovery.
    protected void help() { //a curious young girl named Lily. Lily had a heart full of...
        HelpPrinter.printHelp();
    }

    //One evening, while deep in her research, Lily stumbled upon an ancient map hidden...
    void menu() {
        this.finished = true;
    }

    protected void add(T element) {
        list.add(element);
        System.out.println("Element " + element + " added");
    }

    //chamber filled with sparkling jewels and ancient artifacts.
    protected void remove(int index) {
        list.remove(index);
        System.out.println("Element on " + index + " position removed");
    }


    protected void replace(int index, T element) {
        list.set(index, element);
        System.out.println("Element on " + index + " position replaced with " + element);
    }

    //adventure and a mind hungry for knowledge. Every day, she would wander through the...
    protected void replaceAll(T from, T to) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(from)) {
                list.set(i, to);
            }
        }
        System.out.println("Each " + from + " element replaced with " + to);
    }

    //within the pages of an old book. The map depicted a hidden cave at the summit of the tallest hill, rumored...
    protected void index(T value) {
        System.out.println("First occurrence of " + value + " is on " + list.indexOf(value) + " position");
    }

    //a satisfying click, the heavy doors slowly creaked open, revealing a dazzling.
    protected void sort(String way) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (list.get(i).compareTo(list.get(j)) > 0 && way.equals("ascending") || list.get(i).compareTo(list.get(j)) > 0 && way.equals(
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
    protected void frequency() {
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

    protected void print(int index) {
        System.out.println("Element on " + index + " position is " + list.get(index));
    }

    //trove of knowledge and beauty. From that day forward, she became known as the village's greatest...
    protected void getRandom() {
        Random random = new Random();
        System.out.println("Random element: " + list.get(random.nextInt(1)));
    } //to hold the key to unlocking unimaginable wonders. The key in Lily's...

    protected void printAll(String type) {
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
                if (list.size() > 0)
                    System.out.print(list.get(list.size() - 1));
                System.out.println();
                break;
        }
    }

    //village, observing the world around her and asking questions that often left the villagers perplexed...
    protected void count(T value) {
        int amount = 0;
        for (T i : list) {
            if (i == value) {
                amount++;
            }
        }
        System.out.println("Amount of " + value + ": " + amount);
    }

    protected void size() {
        System.out.println("Amount of elements: " + list.size());
    }

    protected void equals(int i, int j) {
        boolean res = list.get(i).equals(list.get(j));
        System.out.printf("%d and %d elements are%s equal: %s\n",
                i, j, res ? "" : " not", list.get(i) + (res ? " = " : " != ") + list.get(j));
    }

    protected void readFile(String path) throws IOException {
        CustomFileReader<T> reader = getReader();
        ArrayList<T> list2 = reader.read(path);
        list.addAll(list2);
        System.out.println("Data imported: " + list.size());
    }

    protected void writeFile(String path) throws IOException {
        CustomFileWriter<T> writer = getWriter();
        writer.write(path, new ArrayList<>(list));
        System.out.println("Data exported: " + list.size());
    }

    protected void clear() {
        list.clear();
        System.out.println("Data cleared");
    }

    //possession seemed to match the one shown on the map.
    protected void compare(int i, int j) {
        if (list.get(i).compareTo(list.get(j)) > 0) {
            System.out.println("Result: " + list.get(i) + " > " + list.get(j));
        } else if (list.get(i).compareTo(list.get(j)) < 0) {
            System.out.println("Result: " + list.get(i) + " < " + list.get(j));
        } else {
            System.out.println("Result: " + list.get(i) + " = " + list.get(j));
        }
    }

    //With the map as her guide, Lily set out on an arduous journey up the treacherous hill, navigating through...
    protected void mirror() {
        ArrayList<T> list2 = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            list2.add(list.get(i));
        }
        System.out.println("Data reversed");
    }

    void unique() {
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

    private void fillGeneralCommands() {
        commands.put("/help", new Class<?>[]{});
        commands.put("/menu", new Class<?>[]{});
        commands.put("/add", new Class<?>[]{this.getClass()});
        commands.put("/remove", new Class<?>[]{this.getClass()});
        commands.put("/replace", new Class<?>[]{int.class, this.getClass()});
        commands.put("/replaceAll", new Class<?>[]{this.getClass(), this.getClass()});
        commands.put("/index", new Class<?>[]{this.getClass()});
        commands.put("/sort", new Class<?>[]{this.getClass()});
        commands.put("/frequency", new Class<?>[]{});
        commands.put("/print", new Class<?>[]{int.class});
        commands.put("/printAll", new Class<?>[]{String.class});
        commands.put("/getRandom", new Class<?>[]{});
        commands.put("/count", new Class<?>[]{this.getClass()});
        commands.put("/size", new Class<?>[]{});
        commands.put("/equals", new Class<?>[]{int.class, int.class});

        commands.put("/clear", new Class<?>[]{});
        commands.put("/compare", new Class<?>[]{int.class, int.class});
        commands.put("/mirror", new Class<?>[]{});
        commands.put("/unique", new Class<?>[]{});

        commands.put("/readFile", new Class<?>[]{String.class});
        commands.put("/writeFile", new Class<?>[]{String.class});
    }

    @SuppressWarnings("unchecked")
    private CustomFileReader<T> getReader() {
        return switch (this) {
            case NumberMemorize numberMemorize -> (CustomFileReader<T>) new FileReaderInteger();
            case BooleanMemorize booleanMemorize -> (CustomFileReader<T>) new FileReaderBoolean();
            default -> (CustomFileReader<T>) new FileReaderWords();
        };
    }

    @SuppressWarnings("unchecked")
    private CustomFileWriter<T> getWriter() {
        return switch (this) {
            case NumberMemorize numberMemorize -> (CustomFileWriter<T>) new FileWriterInteger();
            case BooleanMemorize booleanMemorize -> (CustomFileWriter<T>) new FileWriterBoolean();
            default -> (CustomFileWriter<T>) new FileWriterWords();
        };
    }


}
