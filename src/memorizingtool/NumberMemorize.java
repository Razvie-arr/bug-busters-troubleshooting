package memorizingtool;//Chapter 4

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
    protected void sort(String way) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (list.get(i) > list.get(j) && way.equals("ascending") || list.get(i) > list.get(j) && way.equals("descending")) {
                    int temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        System.out.printf("Memory sorted %s\n", way);
    }

    @Override
    //possession seemed to match the one shown on the map.
    protected void compare(int i, int j) {
        if (list.get(i) > list.get(j)) {
            System.out.println("Result: " + list.get(i) + " > " + list.get(j));
        } else if (list.get(i) < list.get(j)) {
            System.out.println("Result: " + list.get(i) + " < " + list.get(j));
        } else {
            System.out.println("Result: " + list.get(i) + " = " + list.get(j));
        }
    }

    //dense forests and rocky terrain. After days of perseverance, she finally reached the summit and stood before...
    void sum(int i, int j) {
        int a = list.get(i), b = list.get(j);
        int res = a + b;
        System.out.printf("Calculation performed: %d + %d = %d\n", a, b, res);
    }

    void subtract(int i, int j) {
        int a = list.get(i), b = list.get(j);
        int res = a - b;
        System.out.printf("Calculation performed: %d - %d = %d\n", a, b, res);
    }

    void multiply(int i, int j) {
        int a = list.get(i), b = list.get(j);
        int res = a * b;
        System.out.printf("Calculation performed: %d * %d = %d\n", a, b, res);
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
        long res = 1;
        int i = 2;
        do {
            res = res * (i++);
        } while (i <= list.get(index));
        System.out.printf("Calculation performed: %d! = %d\n", list.get(index), res);
    }

    //the entrance of the hidden cave. With a deep breath, she inserted the silver key into the lock, and with...
    void sumAll() {
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        System.out.println("Sum of all elements: " + sum);
    }

    void average() {
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        System.out.println("Average of all elements: " + sum / 2);
    }

}
