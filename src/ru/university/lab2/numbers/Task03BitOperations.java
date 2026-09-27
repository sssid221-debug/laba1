package ru.university.lab2.numbers;

/**
 * Задание №3 — побитовые операции.
 */
public class Task03BitOperations {

    public void run() {
        bitwiseOperatorsDemo();
        shiftOperatorsDemo();
        bitTricksDemo();
        swapWithXorDemo();
    }

    private void bitwiseOperatorsDemo() {
        System.out.println("=== 3.1 &, |, ^, ~, <<, >>, >>> ===");
        int a = 0b1010; // 10
        int b = 0b0110; // 6
        System.out.println("a = " + a + " (" + Integer.toBinaryString(a) + ")");
        System.out.println("b = " + b + " (" + Integer.toBinaryString(b) + ")");
        System.out.println("a & b  = " + (a & b));
        System.out.println("a | b  = " + (a | b));
        System.out.println("a ^ b  = " + (a ^ b));
        System.out.println("~a     = " + (~a));
        System.out.println("a << 2 = " + (a << 2));
        System.out.println("a >> 1 = " + (a >> 1));
        System.out.println("a >>> 1= " + (a >>> 1));
        System.out.println();
    }

    private void shiftOperatorsDemo() {
        System.out.println("=== 3.2 Разница >> и >>> на отрицательном числе ===");
        int negative = -8;
        System.out.println("negative        = " + negative
                + " (" + Integer.toBinaryString(negative) + ")");
        System.out.println("negative >> 1   = " + (negative >> 1));
        System.out.println("negative >>> 1  = " + (negative >>> 1));
        // >> — арифметический сдвиг вправо: освобождающиеся слева биты
        // заполняются копией знакового бита, поэтому у отрицательного числа
        // они заполняются единицами, и знак (отрицательность) сохраняется.
        // >>> — логический (беззнаковый) сдвиг вправо: освобождающиеся биты
        // всегда заполняются нулями, независимо от знака, поэтому
        // отрицательное число после >>> превращается в большое положительное
        // число — интерпретация бит меняется с "числа со знаком" на
        // "числа без знака".
        System.out.println();
    }

    private void bitTricksDemo() {
        System.out.println("=== 3.3 Чётность, степень двойки, количество единичных бит ===");
        int[] samples = {0, 1, 2, 3, 4, 15, 16, 1024, 1023};
        for (int n : samples) {
            System.out.println("n=" + n
                    + " even=" + isEven(n)
                    + " powerOfTwo=" + isPowerOfTwo(n)
                    + " setBits=" + countSetBits(n));
        }
        System.out.println();
    }

    /** Число чётно, если его младший бит равен 0. */
    private boolean isEven(int n) {
        return (n & 1) == 0;
    }

    /**
     * n является степенью двойки (и n > 0) тогда и только тогда, когда у него
     * ровно один установленный бит. В этом случае n и (n - 1) не имеют общих
     * единичных битов, поэтому n & (n - 1) == 0.
     */
    private boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /** Подсчёт количества единичных битов алгоритмом Брайана Кернигана. */
    private int countSetBits(int n) {
        int count = 0;
        // Каждая итерация n & (n - 1) стирает самый младший установленный бит.
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    private void swapWithXorDemo() {
        System.out.println("=== 3.4 Обмен значений через XOR без временной переменной ===");
        int x = 5;
        int y = 12;
        System.out.println("до: x=" + x + " y=" + y);
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.println("после: x=" + x + " y=" + y);
        // Идея: x ^ y хранит "разницу" между исходными x и y в виде битовой
        // маски. Во второй строке y = (x^y) ^ y = x — исходный x восстановлен
        // и записан в y. В третьей строке x = (x^y) ^ x(новый, т.е. старый y)
        // = старый y. В сумме значения переставились без дополнительной
        // памяти под временную переменную.
        System.out.println();
    }
}
