package ru.university.lab2.arrays;

import java.util.Arrays;
import java.util.Random;

/**
 * Задание №5 — одномерные массивы. Поиск и сортировка реализованы вручную,
 * без java.util.Arrays (Arrays используется только в п.5.4, где сравнение
 * с Arrays.equals — само содержание задания).
 */
public class Task05Arrays {

    public void run() {
        int[] numbers = createRandomArray(12);
        System.out.println("исходный массив: " + toDisplayString(numbers));

        printMinMaxAverage(numbers);
        sortAndPrint(numbers);
        compareArraysDemo();
    }

    private int[] createRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100); // 0..99
        }
        return array;
    }

    private void printMinMaxAverage(int[] array) {
        System.out.println("=== 5.2 Минимум, максимум, среднее (вручную) ===");
        int min = findMin(array);
        int max = findMax(array);
        double average = findAverage(array);
        System.out.println("min=" + min + " max=" + max + " avg=" + average);
        System.out.println();
    }

    private int findMin(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    private int findMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private double findAverage(int[] array) {
        long sum = 0;
        for (int value : array) {
            sum += value;
        }
        return (double) sum / array.length;
    }

    private void sortAndPrint(int[] array) {
        System.out.println("=== 5.3 Сортировка вставками (вручную) ===");
        System.out.println("до сортировки:  " + toDisplayString(array));
        // Выбран алгоритм сортировки вставками (insertion sort): массив
        // условно делится на отсортированную левую часть и неотсортированную
        // правую; на каждом шаге очередной элемент "вставляется" на своё
        // место в уже отсортированной части сдвигом больших элементов вправо.
        insertionSort(array);
        System.out.println("после сортировки: " + toDisplayString(array));
        System.out.println();
    }

    private void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    private void compareArraysDemo() {
        System.out.println("=== 5.4 ==, equals, Arrays.equals ===");
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = arr1;

        System.out.println("arr1 == arr2            -> " + (arr1 == arr2));
        System.out.println("arr1 == arr3            -> " + (arr1 == arr3));
        System.out.println("arr1.equals(arr2)       -> " + arr1.equals(arr2));
        System.out.println("Arrays.equals(arr1,arr2)-> " + Arrays.equals(arr1, arr2));
        // == сравнивает ссылки: arr1 и arr2 — это два разных объекта-массива
        // в куче с одинаковым содержимым, поэтому arr1 == arr2 -> false, а
        // arr1 == arr3 -> true, так как arr3 — это та же самая ссылка на
        // тот же объект, что и arr1.
        // Массивы не переопределяют equals(), поэтому arr1.equals(arr2)
        // фактически вызывает Object.equals() и тоже сравнивает по ссылке,
        // давая false, как и == (это частая ошибка новичков).
        // Arrays.equals(arr1, arr2) — единственный из трёх способов, который
        // действительно сравнивает содержимое массивов поэлементно, и
        // корректно возвращает true для одинаковых по содержимому массивов.
        System.out.println();
    }

    private String toDisplayString(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
