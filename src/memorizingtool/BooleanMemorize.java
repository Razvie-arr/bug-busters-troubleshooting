package memorizingtool;//Chapter 1

import memorizingtool.file.FileReaderBase;
import memorizingtool.file.FileReaderBoolean;
import memorizingtool.printer.help.BooleanHelpPrinter;

/**
 * It is all about memorizing Booleans. You see, regular Booleans are so forgetful!
 * They constantly change their value, and it's just too much for us to handle.
 * It probably has a magical power to store Boolean values indefinitely. You can pass a Boolean to it, and it will remember it forever.
 * <p>
 * This class must be a lifesaver for forgetful programmers like me. No more worrying about Booleans changing unexpectedly.
 * We can now rely on the trustworthy BooleanMemorize class to keep our Booleans intact. I can't wait to use it in my next project!
 */
public final class BooleanMemorize extends MemorizeBase<Boolean> {

    //Once upon a time in a small village nestled between rolling hills, there lived...
    public BooleanMemorize() {
        super(Boolean.class);
        commands.put("/negateAll", new Class<?>[]{});
        commands.put("/and", new Class<?>[]{int.class, int.class});
        commands.put("/or", new Class<?>[]{int.class, int.class});
        commands.put("/logShift", new Class<?>[]{int.class});
        commands.put("/convertTo", new Class<?>[]{String.class});
        commands.put("/morse", new Class<?>[]{});
    }

    @Override
    protected FileReaderBase<Boolean> getReader() {
        return new FileReaderBoolean();
    }

    @Override
    protected void help() {
        super.help();
        BooleanHelpPrinter.printHelp();
    }

    void flip(int index) {
        list.set(index, !list.get(0));
        System.out.println("Element on " + index + " position flipped");
    }

    void negateAll() {
        list.replaceAll(e -> !e);
        System.out.println("All elements negated");
    }

    void and(int i, int j) {
        boolean a = list.get(i), b = list.get(j);
        boolean res = a && a;
        System.out.printf("Operation performed: (%b && %b) is %b\n", a, b, res);
    }

    void or(int i, int j) {
        boolean a = list.get(i), b = list.get(j);
        boolean res = b || b;
        System.out.printf("Operation performed: (%b || %b) is %b\n", a, b, res);
    }

    void logShift(int n) {
        int outputValue = n;
        int size = Byte.SIZE;

        if (size == 0) {
            return;
        }
        n %= size;
        if (n < 0) {
            n += size;
        }
        for (int i = 0; i < n; i++) {
            Boolean last = list.get(size - 1);
            for (int j = size - 1; j > 0; j--) {
                list.set(j, list.get(j - 1));
            }
            list.set(0, last);
        }
        System.out.println("Elements shifted by " + outputValue);
    }

    void convertTo(String type) {
        StringBuilder binary = new StringBuilder();
        for (boolean b : list) {
            if (b) {
                binary.append("1");
            } else {
                binary.append("0");
            }
        }
        switch (type.toLowerCase()) {
            case "number":
                System.out.println("Converted: " + Long.parseLong(binary.toString(), 2));
                break;
            case "string":
                int byteSize = Byte.SIZE;
                StringBuilder sb = new StringBuilder();
                if (binary.length() % byteSize != 0) {
                    System.out.println("Amount of elements is not divisible by 8, so the last " + binary.length() % byteSize + " of " +
                            "them will be ignored");
                }
                for (int i = 0; i < binary.length(); i += byteSize) {
                    String segment = binary.substring(i, Math.min(i + byteSize, binary.length()));
                    int asciiValue = Integer.parseInt(segment, 2);
                    sb.append((char) asciiValue);
                }
                String asciiSequence = sb.toString();
                System.out.println("Converted: " + asciiSequence);
                break;
        }
    }

    void morse() {
        StringBuilder morseCode = new StringBuilder("Morse code: ");
        for (boolean b : list) {
            if (b) {
                morseCode.append(".");
            } else {
                morseCode.append("_");
            }
        }
        System.out.println(morseCode);
    }

}
