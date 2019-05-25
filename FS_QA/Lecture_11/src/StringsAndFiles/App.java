package StringsAndFiles;

import java.io.IOException;
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
                MyString string = MyString.getInstance();

                int whatToDo = 0;
                while (whatToDo <= 0 || whatToDo > 8) {
                    System.out.println("Текущая строка: " + string.getString());
                    System.out.println("Выберите, что сделать со строкой.");
                    System.out.println("1 - развернуть строку как последовательность символов.");
                    System.out.println("2 - развернуть строку, используя StringBuilder.");
                    System.out.println("3 - удаление пробелов в начале и конце строки.");
                    System.out.println("4 - удаление пробелов в начале и конце строки, работая с последовательностью символов.");
                    System.out.println("5 - приведение всей строки к верхнему регистру.");
                    System.out.println("6 - извлечение подстроки с указанием начально и конечного индексов.");
                    System.out.println("7 - ввести новую строку.");
                    System.out.println("8 - вернуться в главное меню.");
                    Scanner in = new Scanner(System.in);
                    if (in.hasNextInt()) {
                        whatToDo = in.nextInt();
                    }
                }
                switch (whatToDo) {
                    case 1:
                        System.out.println(string.reverseAsChars());
                        break;
                    case 2:
                        System.out.println(string.reverseAsStringBuilder());
                        break;
                    case 3:
                        System.out.println(string.removeSpaces());
                        break;
                    case 4:
                        System.out.println(string.removeSpacesAsChars());
                        break;
                    case 5:
                        System.out.println(string.upperCase());
                        break;
                    case 6:
                        int[] indexes = UserInteractions.askForIndexes(string);
                        System.out.println(string.subString(indexes[0], indexes[1]));
                        break;
                    case 7:
                        string.setString(UserInteractions.askForString());
                        break;
                    case 8:
                        typeOfWork = 0;
                        break;
                }
                Thread.sleep(1500);
                System.out.println("********************************");
            } else if (typeOfWork == 2) {
                //работа с файлами и директориями
                MyFile file = MyFile.getInstance();

                int whatToDo = 0;
                while (whatToDo <= 0 || whatToDo > 7) {
                    Scanner in = new Scanner(System.in);
                    System.out.println("Текущий путь к файлу/директории: " + file.getPath().toAbsolutePath());
                    System.out.println("Выберите действие.");
                    System.out.println("1 - создание файла.");
                    System.out.println("2 - добавление строки в файл.");
                    System.out.println("3 - добавление нескольких строк в файл.");
                    System.out.println("4 - создание директории.");
                    System.out.println("5 - удаление директории.");
                    System.out.println("6 - изменить путь к файлу.");
                    System.out.println("7 - вернуться к главному меню.");
                    if (in.hasNextInt()) {
                        whatToDo = in.nextInt();
                    }
                }
                switch (whatToDo) {
                    case 1:
                        System.out.println("Создание файла.");
                        file.createFile();
                        break;
                    case 2:
                        System.out.println("Добавление строки в файл");
                        System.out.println(file.addLineToFile());
                        break;
                    case 3:
                        System.out.println("Добавление нескольких строк в файл");
                        System.out.println(file.addLinesToFile());
                        break;
                    case 4:
                        System.out.println("Создание директории");
                        file.createDirectory();
                        break;
                    case 5:
                        System.out.println("Удаление директории");
                        String param = UserInteractions.askForParam();
                        file.deleteDirectory(null, param);
//                        if (result == 0){
//                            System.out.println("Директория успешно удалена");
//                        } else if (result == 1) {
//                            System.out.println("Директория не существует.");
//                        } else {
//                            System.out.println("Директория не пуста или открыта в другой программе.");
//                        }
                        break;
                    case 6:
                        file.setPath(UserInteractions.askToChangePath(file.getPath()));
                        break;
                    case 7:
                        typeOfWork = 0;
                        break;
                }
                Thread.sleep(1500);
                System.out.println("********************************");
            }
        }
    }
}
