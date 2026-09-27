package ru.university.lab2.strings;

/**
 * Задание №4 — обработка текста без использования коллекций:
 * только String и char[].
 */
public class Task04TextProcessing {

    public void run() {
        palindromeDemo();
        reverseWordsDemo();
        countCharacterKindsDemo();
        caesarCipherDemo();
        longestWordDemo();
    }

    private void palindromeDemo() {
        System.out.println("=== 4.1 Проверка палиндрома ===");
        String[] samples = {
                "А роза упала на лапу Азора",
                "Hello, world!",
                "Madam, I'm Adam"
        };
        for (String s : samples) {
            System.out.println("\"" + s + "\" -> " + isPalindrome(s));
        }
        System.out.println();
    }

    /**
     * Строка — палиндром, если она читается одинаково в обе стороны,
     * если игнорировать регистр и все символы, кроме букв и цифр.
     * Сравнение идёт двумя указателями с концов строки навстречу друг другу.
     */
    private boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            if (!Character.isLetterOrDigit(chars[left])) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(chars[right])) {
                right--;
                continue;
            }
            char leftChar = Character.toLowerCase(chars[left]);
            char rightChar = Character.toLowerCase(chars[right]);
            if (leftChar != rightChar) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private void reverseWordsDemo() {
        System.out.println("=== 4.2 Разворот порядка слов ===");
        String sentence = "кот съел мышь";
        System.out.println("\"" + sentence + "\" -> \"" + reverseWordsOrder(sentence) + "\"");
        System.out.println();
    }

    /**
     * Разворачивает порядок слов в предложении, сами слова не переворачивает.
     * Слова разделяются одним и более пробелом. Реализовано вручную на
     * char[], без String.split (метод возвращал бы массив строк — тоже
     * массив, но здесь показан явный проход по символам).
     */
    private String reverseWordsOrder(String sentence) {
        char[] chars = sentence.toCharArray();
        StringBuilder result = new StringBuilder();
        int end = chars.length; // конец текущего (справа налево) слова, эксклюзивно
        int i = chars.length - 1;
        while (i >= 0) {
            // пропускаем пробелы справа
            while (i >= 0 && chars[i] == ' ') {
                i--;
            }
            end = i + 1;
            // ищем начало слова
            while (i >= 0 && chars[i] != ' ') {
                i--;
            }
            int start = i + 1;
            if (start < end) {
                if (result.length() > 0) {
                    result.append(' ');
                }
                result.append(chars, start, end - start);
            }
        }
        return result.toString();
    }

    private void countCharacterKindsDemo() {
        System.out.println("=== 4.3 Подсчёт гласных, согласных, цифр и пробелов ===");
        String text = "Java 25 - мощная platform, 2024 год!";
        int[] counts = countCharacterKinds(text);
        System.out.println("текст: \"" + text + "\"");
        System.out.println("гласных=" + counts[0]
                + " согласных=" + counts[1]
                + " цифр=" + counts[2]
                + " пробелов=" + counts[3]);
        System.out.println();
    }

    /**
     * За один проход по строке считает количество гласных, согласных, цифр
     * и пробелов. Поддерживает русские и латинские буквы.
     * Возвращает массив {vowels, consonants, digits, spaces}.
     */
    private int[] countCharacterKinds(String text) {
        String vowels = "aeiouAEIOUаеёиоуыэюяАЕЁИОУЫЭЮЯ";
        int vowelCount = 0;
        int consonantCount = 0;
        int digitCount = 0;
        int spaceCount = 0;

        char[] chars = text.toCharArray();
        for (char c : chars) {
            if (Character.isWhitespace(c)) {
                spaceCount++;
            } else if (Character.isDigit(c)) {
                digitCount++;
            } else if (Character.isLetter(c)) {
                if (vowels.indexOf(c) >= 0) {
                    vowelCount++;
                } else {
                    consonantCount++;
                }
            }
        }
        return new int[] {vowelCount, consonantCount, digitCount, spaceCount};
    }

    private void caesarCipherDemo() {
        System.out.println("=== 4.4 Шифр Цезаря ===");
        String original = "Hello, World!";
        int shift = 5;
        String encrypted = caesarEncrypt(original, shift);
        String decrypted = caesarDecrypt(encrypted, shift);
        System.out.println("исходная строка:     " + original);
        System.out.println("зашифрованная (k=" + shift + "): " + encrypted);
        System.out.println("расшифрованная:      " + decrypted);
        System.out.println();
    }

    /** Шифрование Цезаря: сдвиг только латинских букв на k позиций, регистр сохраняется. */
    private String caesarEncrypt(String text, int k) {
        return caesarShift(text, k);
    }

    /** Расшифровка — это сдвиг в обратную сторону. */
    private String caesarDecrypt(String text, int k) {
        return caesarShift(text, -k);
    }

    private String caesarShift(String text, int k) {
        // Приводим сдвиг к диапазону [0, 26), чтобы корректно работать
        // и с отрицательным k (расшифровка), и с k по модулю больше 26.
        int normalizedShift = ((k % 26) + 26) % 26;
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                chars[i] = (char) ('a' + (c - 'a' + normalizedShift) % 26);
            } else if (c >= 'A' && c <= 'Z') {
                chars[i] = (char) ('A' + (c - 'A' + normalizedShift) % 26);
            }
            // прочие символы (пунктуация, цифры, не латинские буквы) не трогаем
        }
        return new String(chars);
    }

    private void longestWordDemo() {
        System.out.println("=== 4.5 Самое длинное слово в строке ===");
        String text = "Быстрая коричневая лиса перепрыгивает через ленивую собаку";
        System.out.println("текст: \"" + text + "\"");
        System.out.println("самое длинное слово: \"" + longestWord(text) + "\"");
        System.out.println();
    }

    /** Ищет самое длинное слово (разделитель — пробел) за один проход. */
    private String longestWord(String text) {
        char[] chars = text.toCharArray();
        int bestStart = 0;
        int bestLength = 0;
        int currentStart = 0;
        int i = 0;
        while (i <= chars.length) {
            boolean atSeparator = (i == chars.length) || chars[i] == ' ';
            if (atSeparator) {
                int currentLength = i - currentStart;
                if (currentLength > bestLength) {
                    bestLength = currentLength;
                    bestStart = currentStart;
                }
                currentStart = i + 1;
            }
            i++;
        }
        return text.substring(bestStart, bestStart + bestLength);
    }
}
