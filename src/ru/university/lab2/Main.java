package ru.university.lab2;

import ru.university.lab2.numbers.Task01IntegerTraps;
import ru.university.lab2.numbers.Task02FloatingPoint;
import ru.university.lab2.numbers.Task03BitOperations;
import ru.university.lab2.strings.Task04TextProcessing;
import ru.university.lab2.arrays.Task05Arrays;
import ru.university.lab2.arrays.Task06Matrices;
import ru.university.lab2.util.Task07Methods;
import ru.university.lab2.util.Task08Menu;

/**
 * Единственная точка входа в приложение.
 * Никакой вычислительной логики здесь нет: только разбор номера задания
 * и вызов соответствующего run().
 */
public class Main {

    public static void main(String[] args) {
        if (args.length > 0) {
            runByNumber(args[0]);
        } else {
            // Без аргументов запускаем интерактивное меню (задание №8),
            // из которого можно выбрать и запустить любое задание.
            new Task08Menu().run();
        }
    }

    public static void runByNumber(String arg) {
        int number = parseNumber(arg);
        switch (number) {
            case 1 -> new Task01IntegerTraps().run();
            case 2 -> new Task02FloatingPoint().run();
            case 3 -> new Task03BitOperations().run();
            case 4 -> new Task04TextProcessing().run();
            case 5 -> new Task05Arrays().run();
            case 6 -> new Task06Matrices().run();
            case 7 -> new Task07Methods().run();
            case 8 -> new Task08Menu().run();
            default -> System.out.println("Неизвестный номер задания: " + arg);
        }
    }

    // Разбор числа без использования исключений (Integer.parseInt бросает
    // NumberFormatException, поэтому проверяем строку вручную посимвольно).
    private static int parseNumber(String arg) {
        if (arg == null || arg.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < arg.length(); i++) {
            if (!Character.isDigit(arg.charAt(i))) {
                return -1;
            }
        }
        int result = 0;
        for (int i = 0; i < arg.length(); i++) {
            result = result * 10 + (arg.charAt(i) - '0');
        }
        return result;
    }
}
