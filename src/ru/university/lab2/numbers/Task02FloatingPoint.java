package ru.university.lab2.numbers;

/**
 * Задание №2 — особенности вещественной арифметики.
 */
public class Task02FloatingPoint {

    public void run() {
        addFractions();
        sumPointOneTenTimes();
        epsilonComparisonDemo();
        infinityAndNaNDemo();
        roundingComparison();
        floatVsDoublePrecision();
    }

    private void addFractions() {
        System.out.println("=== 2.1 0.1 + 0.2 ===");
        double result = 0.1 + 0.2;
        System.out.println("0.1 + 0.2 = " + result);
        // 0.1 и 0.2 не представимы точно в двоичной системе с плавающей
        // точкой (как 1/3 не представим точно в десятичной), поэтому каждое
        // из них хранится с небольшой погрешностью округления. Сложение этих
        // приближённых значений даёт результат, чуть больший 0.3, а не само
        // 0.3 — отсюда 0.30000000000000004.
        System.out.println();
    }

    private void sumPointOneTenTimes() {
        System.out.println("=== 2.2 Сумма 0.1 десять раз против 1.0 ===");
        double sum = 0.0;
        for (int i = 0; i < 10; i++) {
            sum += 0.1;
        }
        System.out.println("sum = " + sum);
        System.out.println("sum == 1.0 ? " + (sum == 1.0));
        // Каждое сложение накапливает свою маленькую погрешность округления,
        // поэтому после десяти сложений итог отличается от математически
        // точной единицы на несколько последних бит мантиссы, и сравнение
        // через == даёт false.
        System.out.println();
    }

    private void epsilonComparisonDemo() {
        System.out.println("=== 2.3 Сравнение double с точностью epsilon ===");
        double a = 0.1 + 0.2;
        double b = 0.3;
        System.out.println("a == b ?             " + (a == b));
        System.out.println("almostEqual(a, b) ?  " + almostEqual(a, b, 1e-9));
        System.out.println();
    }

    /**
     * Корректное сравнение двух double: числа считаются равными, если
     * модуль их разности не превышает заданную точность epsilon.
     */
    private boolean almostEqual(double a, double b, double epsilon) {
        double diff = a - b;
        if (diff < 0) {
            diff = -diff;
        }
        return diff <= epsilon;
    }

    private void infinityAndNaNDemo() {
        System.out.println("=== 2.4 Infinity, -Infinity, NaN ===");
        double posInf = 1.0 / 0.0;
        double negInf = -1.0 / 0.0;
        double nan = 0.0 / 0.0;
        System.out.println("1.0 / 0.0  = " + posInf);
        System.out.println("-1.0 / 0.0 = " + negInf);
        System.out.println("0.0 / 0.0  = " + nan);
        System.out.println("nan != nan ? " + (nan != nan));
        // В отличие от целых чисел, деление double на ноль не приводит к
        // ошибке: стандарт IEEE 754 определяет специальные значения
        // Infinity/-Infinity для деления ненулевого числа на 0.0, и NaN
        // (Not a Number) для неопределённых операций вроде 0.0 / 0.0.
        // NaN по определению стандарта не равен даже самому себе.
        System.out.println();
    }

    private void roundingComparison() {
        System.out.println("=== 2.5 (int), Math.round, Math.floor, Math.ceil ===");
        double[] values = {2.7, -2.7, 2.3, -2.3};
        for (double v : values) {
            System.out.printf(
                    "v=%-6s (int)=%-3d round=%-3d floor=%-6.1f ceil=%-6.1f%n",
                    v, (int) v, Math.round(v), Math.floor(v), Math.ceil(v));
        }
        // (int) всегда отбрасывает дробную часть (округление к нулю), поэтому
        // (int) -2.7 == -2, а не -3. Math.round округляет к ближайшему целому
        // (для .5 — в сторону +бесконечности), Math.floor всегда округляет
        // вниз (к -бесконечности), а Math.ceil — всегда вверх (к
        // +бесконечности), поэтому для отрицательных чисел floor и ceil дают
        // не тот результат, что интуитивно ожидается от "округления вниз/вверх
        // по модулю".
        System.out.println();
    }

    private void floatVsDoublePrecision() {
        System.out.println("=== 2.6 Точность float и double ===");
        float floatResult = 1f / 3f;
        double doubleResult = 1.0 / 3.0;
        System.out.println("float  1/3 = " + floatResult);
        System.out.println("double 1/3 = " + doubleResult);
        // float хранит число в 32 битах (примерно 7 значащих десятичных
        // цифр), а double — в 64 битах (примерно 15-16 значащих цифр).
        // Поэтому для одного и того же иррационального в двоичной системе
        // значения 1/3 double сохраняет заметно больше верных цифр, чем float.
        System.out.println();
    }
}
