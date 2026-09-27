package ru.university.lab2.util;

/**
 * Задание №7 — перегрузка методов, varargs, рекурсия против итерации.
 */
public class Task07Methods {

    public void run() {
        overloadingDemo();
        varargsDemo();
        powerComparisonDemo();
    }

    private void overloadingDemo() {
        System.out.println("=== 7.1 Перегруженный метод print ===");
        print(42);
        print(3.14);
        print("hello");
        print(new int[] {1, 2, 3});
        System.out.println();
    }

    private void print(int value) {
        System.out.println("print(int):    " + value);
    }

    private void print(double value) {
        System.out.println("print(double): " + value);
    }

    private void print(String value) {
        System.out.println("print(String): " + value);
    }

    private void print(int[] value) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < value.length; i++) {
            sb.append(value[i]);
            if (i < value.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println("print(int[]):  " + sb);
    }

    private void varargsDemo() {
        System.out.println("=== 7.2 varargs: сумма произвольного числа аргументов ===");
        System.out.println("sum()             = " + sum());
        System.out.println("sum(1,2,3)        = " + sum(1, 2, 3));
        int[] values = {10, 20, 30, 40};
        System.out.println("sum(массив 10..40)= " + sum(values));
        System.out.println();
    }

    /** varargs фактически превращает произвольный список int в массив int[] внутри метода. */
    private int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }

    private void powerComparisonDemo() {
        System.out.println("=== 7.3-7.4 Возведение в степень: рекурсия vs итерация ===");
        double[] bases = {2.0, 3.0, 5.0, 10.0};
        int[] exponents = {0, 1, 5, 10};

        for (double base : bases) {
            for (int exp : exponents) {
                double recursive = powerRecursive(base, exp);
                double iterative = powerIterative(base, exp);
                double expected = Math.pow(base, exp);
                System.out.printf(
                        "base=%-5s exp=%-3d recursive=%-15s iterative=%-15s Math.pow=%-15s match=%s%n",
                        base, exp, recursive, iterative, expected,
                        almostEqual(recursive, iterative, 1e-6) && almostEqual(iterative, expected, 1e-6));
            }
        }
        // Предположение о скорости: итеративная реализация будет работать
        // быстрее рекурсивной. При каждом рекурсивном вызове JVM выделяет
        // новый кадр стека (сохраняет адрес возврата, локальные переменные,
        // параметры), а по завершении вызова этот кадр снимается со стека —
        // это дополнительные накладные расходы на каждый уровень рекурсии,
        // которых попросту нет в цикле: там переиспользуется один и тот же
        // набор локальных переменных без создания новых кадров стека.
        // Кроме того, глубокая рекурсия ограничена размером стека и может
        // привести к StackOverflowError, чего не может произойти в цикле.
        System.out.println();
    }

    /** Возведение в неотрицательную целую степень — рекурсивно. */
    private double powerRecursive(double base, int exponent) {
        if (exponent == 0) {
            return 1.0;
        }
        return base * powerRecursive(base, exponent - 1);
    }

    /** Возведение в неотрицательную целую степень — итеративно. */
    private double powerIterative(double base, int exponent) {
        double result = 1.0;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

    private boolean almostEqual(double a, double b, double epsilon) {
        double diff = a - b;
        if (diff < 0) diff = -diff;
        return diff <= epsilon;
    }
}
