package Lecture_11;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        int typeOfWork = 0;
        while (typeOfWork != 3) {
            while (typeOfWork <= 0 || typeOfWork > 3) {
                System.out.println("Выберите что будем делать:");
                System.out.println("1 - Работа со строками.");
                System.out.println("2 - Работа с файлами и директориями.");
                System.out.println("3 - Завершить работу программы.");
                Scanner in = new Scanner(System.in);
                if (in.hasNextInt()) {
                    typeOfWork = in.nextInt();
                }
            }
            if (typeOfWork == 1) {
                String s = "";
                while (s.equals("")) {
                    System.out.println("Введите строку для обработки: ");
                    Scanner in = new Scanner(System.in);
                    s = in.nextLine();
                }
                WorkWithStrings ss = new WorkWithStrings(s);
                int whatToDo = 0;
                while (whatToDo <= 0 || whatToDo > 7) {
                    System.out.println("Выберите, что сделать со строкой.");
                    System.out.println("1 - развернуть строку как последовательность символов.");
                    System.out.println("2 - развернуть строку, используя StringBuilder.");
                    System.out.println("3 - удаление пробелов в начале и конце строки.");
                    System.out.println("4 - удаление пробелов в начале и конце строки, работая с последовательностью символов.");
                    System.out.println("5 - приведение всей строки к верхнему регистру.");
                    System.out.println("6 - извлечение подстроки с указанием начально и конечного индексов.");
                    Scanner in = new Scanner(System.in);
                    if (in.hasNextInt()) {
                        whatToDo = in.nextInt();
                    }
                }
                switch (whatToDo) {
                    case 1:
                        System.out.println(ss.reverseAsChars());
                        break;
                    case 2:
                        System.out.println(ss.reverseAsStringBuilder());
                        break;
                    case 3:
                        System.out.println(ss.removeSpaces());
                        break;
                    case 4:
                        System.out.println(ss.removeSpacesAsChars());
                        break;
                    case 5:
                        System.out.println(ss.upperCase());
                        break;
                    case 6:
                        int[] indexes = ss.askForIndexes();
                        System.out.println(ss.subString(indexes[0], indexes[1]));
                        break;
                }
                typeOfWork = 0;
                Thread.sleep(3000);
                System.out.println("********************************");
            } else if (typeOfWork == 2) {
                //работа с файлами и директориями
                typeOfWork = 0;
                while (typeOfWork <= 0 || typeOfWork > 5) {
                    Scanner in = new Scanner(System.in);
                    System.out.println("Выберите действие.");
                    System.out.println("1 - создание файла.");
                    System.out.println("2 - добавление строки в файл.");
                    System.out.println("3 - добавление нескольких строк в файл.");
                    System.out.println("4 - создание директории.");
                    System.out.println("5 - удаление директории.");
                    if (in.hasNextInt()) {
                        typeOfWork = in.nextInt();
                    }
                }
                switch (typeOfWork) {
                    case 1:
                        System.out.println("Создание файла.");
                        Path file = WorkWithFiles.askForPath();
                        WorkWithFiles.createFile(file);
                        break;
                    case 2:
                        System.out.println("Добавление строки в файл");
                        System.out.println(WorkWithFiles.addLineToFile());
                        break;
                    case 3:
                        System.out.println("Добавление нескольких строк в файл");
                        System.out.println(WorkWithFiles.addLinesToFile());
                        break;
                    case 4:
                        System.out.println("Создание директории");
                        Path dir = WorkWithFiles.askForPath();
                        WorkWithFiles.createDirectory(dir);
                        break;
                    case 5:
                        System.out.println("Удаление директории");
                        String[] path = WorkWithFiles.askForPathAndParam();
                        Path dirToRemove = Paths.get(path[0]);
                        String param = path[1];
                        int result = WorkWithFiles.deleteDirectory(dirToRemove, param);
                        if (result == 0){
                            System.out.println("Директория успешно удалена");
                        } else if (result == 1) {
                            System.out.println("Директория не существует.");
                        } else {
                            System.out.println("Директория не пуста или открыта в другой программе.");
                        }
                        break;
                }
                typeOfWork = 0;
                Thread.sleep(3000);
                System.out.println("********************************");
            }
        }
    }
}
