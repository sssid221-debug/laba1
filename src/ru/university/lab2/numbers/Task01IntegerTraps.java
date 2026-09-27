package ru.university.lab2.numbers;

/**
 * Задание №1 — целочисленные ловушки: диапазоны, переполнение, деление,
 * приведение типов, арифметика над char.
 */
public class Task01IntegerTraps {

    public void run() {
        printMinMaxValues();
        incrementMaxInt();
        multiplicationOverflow();
        divisionAndModulo();
        longToIntNarrowing();
        charArithmetic();
        overflowCheckDemo();
    }

    private void printMinMaxValues() {
        System.out.println("=== 1.1 Диапазоны примитивных типов ===");
        System.out.println("byte:  " + Byte.MIN_VALUE + " .. " + Byte.MAX_VALUE);
        System.out.println("short: " + Short.MIN_VALUE + " .. " + Short.MAX_VALUE);
        System.out.println("int:   " + Integer.MIN_VALUE + " .. " + Integer.MAX_VALUE);
        System.out.println("long:  " + Long.MIN_VALUE + " .. " + Long.MAX_VALUE);
        // Диапазон типа определяется количеством бит, отведённых под него
        // (byte — 8 бит, short — 16, int — 32, long — 64) и тем, что все они
        // знаковые (используется дополнительный код), поэтому диапазон не
        // симметричен: отрицательных значений на одно больше, чем положительных.
        System.out.println();
    }

    private void incrementMaxInt() {
        System.out.println("=== 1.2 Integer.MAX_VALUE + 1 ===");
        int max = Integer.MAX_VALUE;
        int result = max + 1;
        System.out.println(max + " + 1 = " + result);
        // Результат — Integer.MIN_VALUE. int хранит 32 бита в дополнительном
        // коде, арифметика по модулю 2^32 "зацикливается": после самого
        // большого положительного значения идёт самое маленькое отрицательное.
        System.out.println();
    }

    private void multiplicationOverflow() {
        System.out.println("=== 1.3 Integer.MAX_VALUE * 2 ===");
        int asInt = Integer.MAX_VALUE * 2;
        long asLong = (long) Integer.MAX_VALUE * 2;
        System.out.println("в int:  " + asInt);
        System.out.println("в long: " + asLong);
        // В int умножение выполняется по модулю 2^32 и переполняется, давая
        // отрицательное число. В long результата умножения на 2 достаточно
        // разрядов (64 бита), переполнения не происходит, и получаем верное
        // математическое значение. Важно, что в long приводится именно
        // Integer.MAX_VALUE ДО умножения — иначе умножение всё равно
        // выполнится в int и переполнится прежде, чем результат станет long.
        System.out.println();
    }

    private void divisionAndModulo() {
        System.out.println("=== 1.4 Целочисленное деление и остаток ===");
        System.out.println("5 / 2   = " + (5 / 2));
        System.out.println("-5 / 2  = " + (-5 / 2));
        System.out.println("5 % 2   = " + (5 % 2));
        System.out.println("-5 % 2  = " + (-5 % 2));
        // Java округляет частное целочисленного деления к нулю (не вниз, как
        // в математике), поэтому -5 / 2 = -2, а не -3. Остаток определяется
        // тождеством a = (a / b) * b + (a % b), поэтому его знак совпадает
        // со знаком делимого a: -5 % 2 = -1, а не 1.
        System.out.println();
    }

    private void longToIntNarrowing() {
        System.out.println("=== 1.5 Приведение long -> int ===");
        long big = 10_000_000_000L; // больше Integer.MAX_VALUE
        int narrowed = (int) big;
        System.out.println("long:      " + big);
        System.out.println("(int) big: " + narrowed);
        // При явном сужении long -> int компилятор просто отбрасывает старшие
        // 32 бита из 64-битного представления числа и оставляет младшие 32,
        // интерпретируя их как int. Результат не имеет ничего общего с
        // исходным значением по модулю, кроме младших бит.
        System.out.println();
    }

    private void charArithmetic() {
        System.out.println("=== 1.6 Арифметика над char ===");
        char letter = 'a';
        char next = (char) (letter + 1);
        System.out.println("'a' + 1 как char: " + next);

        char c1 = 'A';
        char c2 = 'B';
        int sumAsNumber = c1 + c2; // char + char продвигается до int
        System.out.println("'A' + 'B' как число:  " + sumAsNumber);
        System.out.println("'A' + 'B' как символ: " + (char) sumAsNumber);
        // char хранит UTF-16 код символа как беззнаковое 16-битное число.
        // Любая арифметическая операция над char сначала расширяет его до
        // int (унарное числовое повышение), поэтому результат сложения двух
        // char — это int, а не char; чтобы снова получить символ, нужно явное
        // приведение (char).
        System.out.println();
    }

    private void overflowCheckDemo() {
        System.out.println("=== 1.7 Метод проверки переполнения при сложении ===");
        int[] sumHolder = new int[1];

        boolean overflow1 = addWithOverflowCheck(Integer.MAX_VALUE, 1, sumHolder);
        System.out.println("MAX_VALUE + 1: overflow=" + overflow1 + ", результат=" + sumHolder[0]);

        boolean overflow2 = addWithOverflowCheck(100, 250, sumHolder);
        System.out.println("100 + 250: overflow=" + overflow2 + ", результат=" + sumHolder[0]);
        System.out.println();
    }

    /**
     * Складывает a и b, кладёт сумму в resultHolder[0] и возвращает true,
     * если при сложении произошло переполнение int.
     *
     * Признак переполнения: переполнение при сложении двух int происходит
     * тогда и только тогда, когда оба слагаемых имеют одинаковый знак,
     * а знак результата отличается от их знака. Это классический
     * побитовый приём, без Math.addExact и без расширения до long.
     */
    private boolean addWithOverflowCheck(int a, int b, int[] resultHolder) {
        int sum = a + b;
        resultHolder[0] = sum;
        boolean sameSignOperands = (a >= 0) == (b >= 0);
        boolean resultChangedSign = (sum >= 0) != (a >= 0);
        return sameSignOperands && resultChangedSign;
    }
}
