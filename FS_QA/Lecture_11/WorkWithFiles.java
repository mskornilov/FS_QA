package Lecture_11;

/*
2. Работа с файлами (отдельный класс):
  2.1 Запись одной строки в файл
  2.2 Запись списка строк в файл
  [В случае, если файла нет, то его нужно создать]
  [В случае, если файл есть, то нужно его перезаписать]
  [Необходимо добавить проверки на существование файла]
  прим. используйте try-catch
  2.3 Создание директории
  [В случае, если директория существует, то нужно вывести сообщение об этом]
  2.4 Удаление директории
  [В случае, если директории не существует, то нужно вывести сообщение об этом]
*/


import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class WorkWithFiles {
    /**
     * Запрашивает у пользователя путь к файлу
     * В случает, если указан невалидный путь, выводит в консоль сообщение и запрашивает путь заново
     * Возвращает объект типа Path
     * @return Path
     */
    static Path askForPath(){
        Path path;
        while (true){
            Scanner in = new Scanner(System.in);
            System.out.println("Введите путь к файлу: ");
            try {
                path = Paths.get(in.nextLine());
                break;
            } catch (InvalidPathException e) {
                System.out.println("Указан неверный путь");
            }
        }
        return path;
    }

    /**
     * Принимает объект типа Path
     * Создает файл по пути из полученного объекта
     * Выводит в консоль сообщение, если файл уже существует
     * Выводит в консоль сообщение, если файл успешно создан
     * @param file - Path type
     * @throws IOException - ошибка I/O
     */
    static void createFile(Path file) throws IOException{
        try {
            Files.createFile(file);
            System.out.println("Файл создан");
        } catch (FileAlreadyExistsException e){
            System.out.println("Файл уже существует.");
        }
    }

    /**
     * Принимает объект типа Path
     * Создает файл по пути из полученного объекта
     * Выводит в консоль сообщение, если директория уже существует
     * Выводит в консоль сообщение, если директория успешно создана
     * @param dir - Path type
     * @throws IOException - ошибка I/O
     */
    static void createDirectory(Path dir) throws IOException{
        try {
            Files.createDirectory(dir);
            System.out.println("Директория успешно создана.");
        } catch (FileAlreadyExistsException e){
            System.out.println("Директория или файл с таким именем уже существует.");
        }
    }

    /**
     * Запрашивает у пользователя путь к директории и опциональный параметр [-r]
     * @return String[]
     */
    static String[] askForPathAndParam(){
        String[] pp = new String[2];
        Scanner in = new Scanner(System.in);
        System.out.println("Введите путь к директории.");
        System.out.println("Добавьте параметр [-r], если хотите удалить директорию со всем содержимым.");
        String s = in.nextLine();
        if (s.contains(" -r")){
            pp[0] = s.substring(0, s.indexOf("-r")).trim();
            pp[1] = "-r";
        } else {
            pp[0] = s;
            pp[1] = "";
    }
    return pp;
    }

    /**
     * Принимает объект типа Path с путем к директории и опциональный параметр [-r]
     * Если параметр отсутствует удаляет только пустую директорию
     * При наличии параметра удаляет директорию со всем содержимым
     * Возвращает:
     * 0 - директория удалена
     * 1 - директория не существует
     * 2 - директория не пуста, не указан параметр [-r]
     * @param file - Path type
     * @param param - String ("-r")
     * @return boolean
     * @throws IOException - ошибка I/O
     */
    static int deleteDirectory(Path file, String param) throws IOException{
        while (true) {
            try {
                if (Files.deleteIfExists(file)) {
                    return 0;
                } else {
                    return 1;
                }
            } catch (DirectoryNotEmptyException e) {
                if (param.equals("-r")) {
                    DirectoryStream<Path> ds = Files.newDirectoryStream(file);
                    for (Path f : ds) {
                        try {
                            Files.deleteIfExists(f);
                        } catch (DirectoryNotEmptyException ex) {
                            WorkWithFiles.deleteDirectory(f, "-r");
                        }
                    }
                    ds.close();
                } else {
                    return 2;
                }
            }
        }
    }

    /**
     * Проверяет, что последним символом в файле является '\n'
     * Нужно, чтобы дописываемые в уже существующий файл строки не "слипались" с содержимым
     * Возвращает true, если последний символ в файле - '\n', false, если лююой другой
     * @param file - Path type
     * @return boolean
     * @throws IOException - ошибка I/O
     */
    private static boolean lastCharIsLinebreak(Path file) throws IOException{
        String content = Files.readString(file);
        if (content.length() > 0) {
            return content.charAt(content.length() - 1) == '\n';
        } else {
            return false;
        }
    }

    /**
     * Запрашивает у пользователя строку
     * Возвращает введенную строку
     * @return String
     */
    private static String askForLine(){
        System.out.println("Введите строку:");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Запрашивает у пользователя произвольное количество строк,
     * которые необходимо записать в файл
     * @return List<String>
     */
    private static List<String> askForLines(){
        String line;
        List<String> lines = new ArrayList<>();
        System.out.println("Enter - сохранить строку, начать ввод следующей, # - прекратить ввод строк, * - начать ввод заново.");
        System.out.println("Введите строки:");
        while (true){
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("*")){
                lines.clear();
                System.out.println("Список строк очищен.");
            } else if (line.equals("#")){
                break;
            } else {
                lines.add(line);
//                System.out.println(line);
            }
        }
        return lines;
    }

    /**
     * Принимаеи объект типа Path
     * Проверяет существует ли по указанному пути файл,
     * если существует, то предлагает пользователю выбор:
     * 1 - переписать содержимое новым контентом
     * 2 - добавить новый контент к существующему
     * 3 - выбрать другой файл
     * Возвращает результат выбора или 0, если файла не существует
     * @param file Path
     * @return int
     */
    private static int fileExists(Path file) {
        int whatToDo = 0;
        if (Files.exists(file)) {
            System.out.println("Файл существует, что делаем?");
            while (whatToDo <= 0 || whatToDo > 3) {
                System.out.println("1 - перезаписать, 2 - дописать, 3 - выбрать другой файл:");
                Scanner in = new Scanner(System.in);
                if (in.hasNextInt()) {
                    whatToDo = in.nextInt();
                }
            }
        }
        return whatToDo;
    }


    /**
     * Вызывает метод askForPath класса WorkWithFiles
     * Вызывает метод fileExists класса WorkWithFiles, для проверки существования файла
     * и выбора действия, если файл существует
     * Вызывает метод askForLine класса WorkWithFiles
     * В зависимости от выбранной опции перезаписывает файл или дополняет новой строкой
     * Если файла по указанному пути не существует, создает файл и записывает в него строку.
     * Возвращает строку с отчетом о работе
     */
    static String addLineToFile(){
        String line;
        String result = "";
        while (result.equals("")){
            Path file = WorkWithFiles.askForPath();
            int whatToDo = fileExists(file);
            switch(whatToDo) {
                case 0:
                    // создать и заполнить файл
                    line = WorkWithFiles.askForLine();
                    try {
                        Files.writeString(file, line);
                        result = "Файл создан, строка записаны.";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    // перезаписать файл
                    line = WorkWithFiles.askForLine();
                    try {
                        Files.writeString(file, line);
                        result = "Файл перезаписан новой строкой.";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // дописать строку в файл
                    line = WorkWithFiles.askForLine();
                    try {
                        if (!lastCharIsLinebreak(file)) {
                            line = "\n" + line;
                        }
                        Files.writeString(file, line, StandardOpenOption.APPEND);
                        result = "Строка добавлена в файл";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    // выбрать другой файл
                    break;
            }
        }
        return result;
    }

    /**
     * Вызывает метод askForPath класса WorkWithFiles
     * Вызывает метод fileExists класса WorkWithFiles, для проверки существования файла
     * и выбора действия, если файл существует
     * Вызывает метод askForLines класса WorkWithFiles
     * В зависимости от выбранной опции перезаписывает файл или дополняет новыми строками
     * Если файла по указанному пути не существует, создает файл и записывает в него строки.
     * Возвращает строку с отчетом о работе
     */
    static String addLinesToFile(){
        List<String> lines;
        String result = "";
        while (result.equals("")){
            Path file = WorkWithFiles.askForPath();
            int whatToDo = fileExists(file);
                switch(whatToDo){
                    case 0:
                        // создать и заполнить файл
                        lines = WorkWithFiles.askForLines();
                        try {
                            Files.write(file, lines);
                            result = "Файл создан, строки записаны.";
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 1:
                        // перезаписать файл
                        lines = WorkWithFiles.askForLines();
                        try {
                            Files.write(file, lines);
                            result = "Файл перезаписан новыми строками.";
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        // дописать строки в файл
                        lines = WorkWithFiles.askForLines();
                        try {
                            lines.add(0, "");
                            Files.write(file, lines, StandardOpenOption.APPEND);
                            result = "Строки добавлены в файл";
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        // выбрать другой файл
                        break;
                }
            }
        return result;
    }
}
