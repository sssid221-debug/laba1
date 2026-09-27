package ru.university.lab2.arrays;

import java.util.Random;

/**
 * Задание №6 — многомерные массивы: заполнение, вывод таблицей,
 * транспонирование, умножение матриц.
 */
public class Task06Matrices {

    public void run() {
        int[][] matrix = createAndFillMatrix(3, 4);
        System.out.println("=== 6.1 Матрица 3x4 ===");
        printMatrix(matrix);

        System.out.println("=== 6.2 Транспонированная матрица ===");
        int[][] transposed = transpose(matrix);
        printMatrix(transposed);

        multiplyDemo();
    }

    private int[][] createAndFillMatrix(int rows, int cols) {
        Random random = new Random();
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(90) + 10; // двузначные числа для ровной таблицы
            }
        }
        return matrix;
    }

    /** Выводит матрицу в виде таблицы с выравниванием по ширине столбца. */
    private void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            StringBuilder sb = new StringBuilder();
            for (int value : row) {
                sb.append(String.format("%5d", value));
            }
            System.out.println(sb);
        }
        System.out.println();
    }

    /** Транспонирование: элемент (i, j) становится элементом (j, i). */
    private int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    private void multiplyDemo() {
        System.out.println("=== 6.3 Умножение матриц ===");
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6}
        }; // 2x3
        int[][] b = {
                {7, 8},
                {9, 10},
                {11, 12}
        }; // 3x2
        System.out.println("A (2x3):");
        printMatrix(a);
        System.out.println("B (3x2):");
        printMatrix(b);

        int[][] product = multiplyMatrices(a, b);
        if (product != null) {
            System.out.println("A x B (2x2):");
            printMatrix(product);
        }

        // Пример несогласованных размеров: пытаемся умножить A (2x3) на A (2x3)
        System.out.println("Попытка умножить A (2x3) на A (2x3):");
        multiplyMatrices(a, a);
    }

    /**
     * Умножение матриц с проверкой согласованности размеров: количество
     * столбцов первой матрицы должно совпадать с количеством строк второй.
     * Если размеры не согласованы, метод сообщает об этом и возвращает null,
     * не выполняя вычисление (исключения не используются).
     */
    private int[][] multiplyMatrices(int[][] a, int[][] b) {
        int aRows = a.length;
        int aCols = a[0].length;
        int bRows = b.length;
        int bCols = b[0].length;

        if (aCols != bRows) {
            System.out.println("Умножение невозможно: у первой матрицы "
                    + aCols + " столбцов, а у второй " + bRows + " строк.");
            return null;
        }

        int[][] result = new int[aRows][bCols];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                int sum = 0;
                for (int k = 0; k < aCols; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }
}
