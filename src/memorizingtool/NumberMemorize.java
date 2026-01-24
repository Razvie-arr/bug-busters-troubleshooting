package memorizingtool;//Chapter 4

import memorizingtool.file.FileReaderBase;
import memorizingtool.file.FileReaderInteger;
import memorizingtool.printer.help.NumberHelpPrinter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Map;

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
        super();
    }

    @Override
    protected FileReaderBase<Integer> getReader() {
        return new FileReaderInteger();
    }

    @Override
    public void help() {
        super.help();
        NumberHelpPrinter.printHelp();
    }

    @Override
    protected void registerTypeCommands(Map<String, CommandWrapper> registry) {
        registry.put("/sum", new CommandWrapper(parts -> sum(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])), 2));
        registry.put("/subtract", new CommandWrapper(parts -> subtract(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])), 2));
        registry.put("/multiply", new CommandWrapper(parts -> multiply(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])), 2));
        registry.put("/divide", new CommandWrapper(parts -> divide(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])), 2));
        registry.put("/pow", new CommandWrapper(parts -> pow(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])), 2));
        registry.put("/factorial", new CommandWrapper(parts -> factorial(Integer.parseInt(parts[0])), 1));
        registry.put("/sumAll", new CommandWrapper(parts -> sumAll(), 0));
        registry.put("/average", new CommandWrapper(parts -> average(), 0));
    }

    @Override
    protected Integer parseElement(String value) {
        return Integer.valueOf(value);
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
        BigDecimal a = BigDecimal.valueOf(list.get(i));
        BigDecimal b = BigDecimal.valueOf(list.get(j));
        if (b.equals(BigDecimal.ZERO)) {
            System.out.println("Division by zero");
            return;
        }

        BigDecimal res = a.divide(b, 6, RoundingMode.HALF_UP).stripTrailingZeros();
        System.out.printf("Calculation performed: %s / %s = %s%n", a.toPlainString(), b.toPlainString(), res.toPlainString());
    }

    void pow(int i, int j) {
        BigDecimal base = BigDecimal.valueOf(list.get(i));
        int exponent = list.get(j);
        BigDecimal res;
        if (exponent >= 0) {
            res = base.pow(exponent);
        } else {
            res = BigDecimal.ONE.divide(base.pow(-exponent), 6, RoundingMode.HALF_UP);
        }
        res = res.stripTrailingZeros();
        System.out.printf("Calculation performed: %s ^ %d = %s%n", base.toPlainString(), exponent, res.toPlainString());
    }

    void factorial(int index) {
        int number = list.get(index);
        if (number < 0) {
            System.out.println("\"undefined\"");
            return;
        }
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
        BigDecimal avg = sum.divide(BigDecimal.valueOf(list.size()), 6, RoundingMode.HALF_UP).stripTrailingZeros();
        System.out.println("Average of all elements: " + avg.toPlainString());
    }

}
