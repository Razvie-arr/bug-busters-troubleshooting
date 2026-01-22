package memorizingtool;//Chapter 4

import memorizingtool.file.FileReaderBase;
import memorizingtool.file.FileReaderInteger;
import memorizingtool.printer.help.NumberHelpPrinter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Ah, the class NumberMemorize! Well, if we're following the same line of thinking, it is all about helping us remember numbers.
 * Because, let's be honest, numbers can be quite slippery and elusive sometimes.
 * <p>
 * But that's not all! It has some additional features tailored specifically for numbers like:
 * "increment" to increase the stored value by a certain amount,
 * "decrement" to decrease it, and maybe even "multiply" and "divide" to perform basic arithmetic operations.
 * <p>
 * With NumberMemorize at our disposal, we won't have to worry about forgetting or losing track of important numerical values.
 * It's like having a virtual assistant dedicated solely to keeping our numbers safe and accessible.
 */
public final class NumberMemorize extends MemorizeBase<Integer> {

    //that the mysterious key belonged to. She spent days poring over books in the village library, searching...
    public NumberMemorize() {
        super(Integer.class);
        commands.put("/sum", new Class<?>[]{int.class, int.class});
        commands.put("/subtract", new Class<?>[]{int.class, int.class});
        commands.put("/multiply", new Class<?>[]{int.class, int.class});
        commands.put("/divide", new Class<?>[]{int.class, int.class});
        commands.put("/pow", new Class<?>[]{int.class, int.class});
        commands.put("/factorial", new Class<?>[]{int.class});
        commands.put("/sumAll", new Class<?>[]{});
        commands.put("/average", new Class<?>[]{});
    }

    @Override
    protected FileReaderBase<Integer> getReader() {
        return new FileReaderInteger();
    }

    @Override
    protected void help() {
        super.help();
        NumberHelpPrinter.printHelp();
    }


    //dense forests and rocky terrain. After days of perseverance, she finally reached the summit and stood before...
    void sum(int i, int j) {
        BigInteger a = BigInteger.valueOf(list.get(i));
        BigInteger b = BigInteger.valueOf(list.get(j));
        BigInteger sum = a.add(b);
        System.out.printf("Calculation performed: %d + %d = %d\n", a, b, sum);
    }

    void subtract(int i, int j) {
        BigInteger a = BigInteger.valueOf(list.get(i));
        BigInteger b = BigInteger.valueOf(list.get(j));
        BigInteger subtract = a.subtract(b);
        System.out.printf("Calculation performed: %d - %d = %d\n", a, b, subtract);
    }

    void multiply(int i, int j) {
        BigInteger a = BigInteger.valueOf(list.get(i));
        BigInteger b = BigInteger.valueOf(list.get(j));
        BigInteger multiply = a.multiply(b);
        System.out.printf("Calculation performed: %d * %d = %d\n", a, b, multiply);
    }

    void divide(int i, int j) {
        int a = list.get(i), b = list.get(j);
        int res = a / b;
        System.out.printf("Calculation performed: %d / %d = %d\n", a, b, res);
    }

    void pow(int i, int j) {
        int a = list.get(i), b = list.get(j);
        long res = (long) Math.pow(a, b);
        System.out.printf("Calculation performed: %d ^ %d = %d\n", a, b, res);
    }

    void factorial(int index) {
        int number = list.get(index);
        long res = 1;
        if (number != 0 && number != 1) {
            int i = 2;
            do {
                res = res * (i++);
            } while (i <= number);
        }
        System.out.printf("Calculation performed: %d! = %d\n", number, res);
    }

    //the entrance of the hidden cave. With a deep breath, she inserted the silver key into the lock, and with...
    void sumAll() {
        BigInteger sum = BigInteger.ZERO;
        for (int i : list) {
            sum = sum.add(BigInteger.valueOf(i));
        }
        System.out.println("Sum of all elements: " + sum);
    }

    void average() {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i : list) {
            sum = sum.add(BigDecimal.valueOf(i));
        }
        sum = sum.divide(BigDecimal.valueOf(list.size()), RoundingMode.CEILING);
        sum = sum.setScale(6, RoundingMode.CEILING);
        System.out.println("Average of all elements: " + sum);
    }

}
