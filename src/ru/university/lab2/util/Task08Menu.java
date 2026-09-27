package ru.university.lab2.util;

import java.util.Scanner;

import ru.university.lab2.numbers.Task01IntegerTraps;
import ru.university.lab2.numbers.Task02FloatingPoint;
import ru.university.lab2.numbers.Task03BitOperations;
import ru.university.lab2.strings.Task04TextProcessing;
import ru.university.lab2.arrays.Task05Arrays;
import ru.university.lab2.arrays.Task06Matrices;

/**
 * Задание №8 — консольное меню выбора задания.
 * Использует do-while, switch-выражение со стрелочным синтаксисом,
 * Scanner с hasNextInt() (без исключений) и текстовый блок для справки.
 */
public class Task08Menu {

    private static final int EXIT_CHOICE = 0;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            printMenu();
            choice = readMenuChoice(scanner);
            handleChoice(choice);
        } while (choice != EXIT_CHOICE);
        System.out.println("Выход из меню.");
    }

    private void printMenu() {
        System.out.println(helpText());
    }

    private String helpText() {
        // Текстовый блок (Java 15+) — удобен для многострочного текста
        // без экранирования кавычек и символов переноса строки.
        return """
                ====================================
                 Лабораторная работа №1 — меню
                ====================================
                 1 - Целочисленные ловушки
                 2 - Вещественная арифметика
                 3 - Побитовые операции
                 4 - Обработка текста
                 5 - Одномерные массивы
                 6 - Многомерные массивы
                 0 - Выход
                ====================================
                Введите номер задания: """;
    }

    /**
     * Читает выбор пользователя из меню. Если введены не цифры или число
     * вне допустимого диапазона, программа не падает: печатает сообщение
     * об ошибке и просит ввести число заново.
     */
    private int readMenuChoice(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value >= 0 && value <= 6) {
                    return value;
                }
                System.out.println("Число вне диапазона [0..6], попробуйте снова.");
            } else {
                // hasNextInt() == false: во входном потоке не число.
                // Считываем и отбрасываем "плохой" токен, чтобы не зациклиться.
                String bad = scanner.next();
                System.out.println("\"" + bad + "\" — это не число, попробуйте снова.");
            }
        }
    }

    private void handleChoice(int choice) {
        // switch как выражение со стрелочным синтаксисом: каждая ветка
        // выполняет ровно одно действие, без "проваливания" (fall-through)
        // и без break.
        switch (choice) {
            case 1 -> new Task01IntegerTraps().run();
            case 2 -> new Task02FloatingPoint().run();
            case 3 -> new Task03BitOperations().run();
            case 4 -> new Task04TextProcessing().run();
            case 5 -> new Task05Arrays().run();
            case 6 -> new Task06Matrices().run();
            case EXIT_CHOICE -> System.out.println("Завершение работы...");
            default -> System.out.println("Неизвестный пункт меню: " + choice);
        }
    }
}
