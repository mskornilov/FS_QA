package Lecture_11;

/*
1. Работа со строками (отдельный класс):
        1.1 Разворот строки 2мя способами:
        а) Работая со строкой как с последовательностью char символов
        б) Работая со строкой при помощи объекта StringBuilder
        1.2 Форматирование строки:
        а) Удаление лишних пробелов в начале и в конце строки
        * удаление, работая со строкой как с последовательностью char
        б) Приведение всех символов строки к верхнему регистру
        1.3 Извлечение подстроки с уакзанием начального индекса и конечного
*/
import java.util.Scanner;

class WorkWithStrings {
    private String s;
    WorkWithStrings(String arg){
        s = arg;
    }

    /**
     * Переворачивает строку
     * Работает со строкой как с последовательностью символов
     * @return String
     */
    String reverseAsChars(){
        char[] chars = s.toCharArray();
        int maxIndex = chars.length - 1;
        for (int i = 0; i < chars.length/2; i++){
            chars[i] = (char) (chars[i] + chars[maxIndex - i]);
            chars[maxIndex - i] = (char) (chars[i] - chars[maxIndex - i]);
            chars[i] = (char) (chars[i] - chars[maxIndex - i]);
        }
        return new String(chars);
    }

    /**
     * Переворачивает строку
     * @return String
     */
    String reverseAsStringBuilder(){
        StringBuilder builder = new StringBuilder(s);
        builder.reverse();
        return builder.toString();
    }

    /**
     * Удаляет пробелы из начала и из конца строки
     * @return String
     */
    String removeSpaces(){
        return s.trim();
    }

    /**
     * Удаляет пробелы из начала и из конца строки,
     * Работает со строкой как с последовательностью символов
     * @return String
     */
    String removeSpacesAsChars(){
        char[] chars = s.toCharArray();
        char[] charsNoSpaces;
        int counter = 0;
        int start = 0;
        for (int i = 0; i < chars.length; i++){
            if (chars[i] == ' '){
                start++;
                counter++;
            } else {
                break;
            }
        }
        // если в строке не только пробелы
        if (counter != chars.length) {
            for (int i = chars.length - 1; i >= 0; i--) {
                if (chars[i] == ' ') {
                    counter++;
                } else {
                    break;
                }
            }
        }
        charsNoSpaces = new char[chars.length - counter];
        System.arraycopy(chars, start, charsNoSpaces, 0, chars.length - counter);
        return new String(charsNoSpaces);
    }

    /**
     * Переводит символы в строке в верхний регистр
     * @return String
     */
    String upperCase(){
        return s.toUpperCase();
    }

    /**
     * Принимает два индекса
     * Извлекает подстроку от start до end
     * @return String
     */
    String subString(int start, int end){
        return s.substring(start, end);
    }

    /**
     * Запрашивает у пользователя два индекса поочередно,
     * первый индекс: от 0 до (длина строки - 1)
     * второй индекс: от (значение первого индекса + 1) до (длина строки)
     * Возвращает массив из двух индексов
     * @return String[]
     */
    int[] askForIndexes(){
        int[] indexes = {-1, -1};
        while(indexes[0] < 0 || indexes[0] >= s.length()){
            System.out.printf("Введите первый индекс от 0 до %d (включительно): \n", s.length()-1);
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()){
                indexes[0] = in.nextInt();
            }
        }
        while(indexes[1] < indexes[0] || indexes[1] > s.length()){
            System.out.printf("Введите второй индекс от %d до %d (включительно): \n", indexes[0] + 1, s.length());
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()){
                indexes[1] = in.nextInt();
            }
        }
    return indexes;
    }
}
