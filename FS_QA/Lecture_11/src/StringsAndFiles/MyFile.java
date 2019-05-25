package StringsAndFiles;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

public class MyFile {

    private static MyFile instance;
    private Path path;

    public static MyFile getInstance(){
        if (instance == null) {
            instance = new MyFile(UserInteractions.askForPath());
        }
        return instance;
    }

    private MyFile(Path path){
        this.path = path;
    }

    Path getPath(){
        return this.path;
    }
    void setPath(Path path){
        this.path = path;
    }

    /**
     * проверяет не является ли файл по указанному пути папкой,
     * если является - предлагает изменить путь к файлу.
     * Создает файл по сохраненному в экземпляре класса пути
     * Выводит в консоль сообщение, если файл уже существует
     * Выводит в консоль сообщение, если файл успешно создан
     * @throws IOException - ошибка I/O
     */
    void createFile() throws IOException {
        if (Files.isDirectory(this.path)){
            System.out.println("Директория с таким именем уже существует");
            setPath(UserInteractions.askToChangePath(this.path));
        } else {
            try {
                Files.createFile(this.path);
                System.out.println("Файл создан");
            } catch (FileAlreadyExistsException e){
                System.out.println("Файл уже существует.");
                setPath(UserInteractions.askToChangePath(this.path));
            }
        }
    }

    /**
     *
     * Создает файл по пути, сохраненному в экземпляре класса
     * Выводит в консоль сообщение, если директория уже существует
     * Выводит в консоль сообщение, если директория успешно создана
     * @throws IOException - ошибка I/O
     */
    void createDirectory() throws IOException{
        try {
            Files.createDirectory(this.path);
            System.out.println("Директория успешно создана.");
        } catch (FileAlreadyExistsException e){
            System.out.println("Директория или файл с таким именем уже существует.");
            setPath(UserInteractions.askToChangePath(this.path));
        }
    }
    /**
     * Принимает объект типа Path с путем к директории (может быть null) и параметр "y"/"n"
     * При наличии параметра "N" удаляет только пустую директорию
     * При наличии параметра "Y" удаляет директорию со всем содержимым
     * В зависимости от результата выполнения выводит в консоль сообщения:
     * - директория удалена
     * - директория не существует
     * - директория не пуста (Если не указан параметр "y")
     * @param path - Path type
     * @param param - String ("y", "n")
     * @throws IOException - ошибка I/O
     */

    void deleteDirectory(Path path, String param) throws IOException{
        path = path == null ? this.path : path;
        while (true) {
            try {
                if (Files.deleteIfExists(path)) {
                    if (path == this.path){
                        System.out.println("Директория удалена");
                    }
                    break;
                } else {
                    System.out.println("Директория не существует");
                    break;
                }
            } catch (DirectoryNotEmptyException e) {
                if (param.equals("y")) {
                    DirectoryStream<Path> ds = Files.newDirectoryStream(path);
                    for (Path f : ds) {
                        try {
                            Files.deleteIfExists(f);
                        } catch (DirectoryNotEmptyException ex) {
                            deleteDirectory(f, "y");
                        }
                    }
                    ds.close();
                } else {
                    System.out.println("Директория не пуста");
                    break;
                }
            }
        }
    }
    /**
     * Проверяет, что последним символом в файле является '\n'
     * Нужно, чтобы дописываемые в уже существующий файл строки не "слипались" с содержимым
     * Возвращает true, если последний символ в файле - '\n', false, если лююой другой
     * @return boolean
     * @throws IOException - ошибка I/O
     */
    private boolean lastCharIsLinebreak() throws IOException{
        String content = Files.readString(this.path);
        System.out.println(content);
        if (content.length() > 0) {
            return content.charAt(content.length() - 1) == '\n';
        } else {
            return false;
        }
    }

    /**
     *
     * Проверяет существует ли по сохраненному в экземпляре класса пути файл
     * если существует, то предлагает пользователю выбор:
     * 1 - переписать содержимое новым контентом
     * 2 - добавить новый контент к существующему
     * 3 - выбрать другой файл
     * Возвращает результат выбора или 0, если файла не существует
     * @return int
     */
    private int fileExists() {
        int whatToDo = 0;
        if (Files.exists(this.path)) {
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
     * Вызывает метод fileExists, для проверки существования файла
     * и выбора действия, если файл существует
     * Вызывает метод askForLine класса UserInteractions
     * В зависимости от выбранной опции перезаписывает файл или дополняет новой строкой
     * Если файла по указанному пути не существует, создает файл и записывает в него строку.
     * Возвращает строку с отчетом о работе
     */
    String addLineToFile(){
        String line;
        String result = "";
        while (result.equals("")){
            int whatToDo = fileExists();
            switch(whatToDo) {
                case 0:
                    // создать и заполнить файл
                    line = UserInteractions.askForLine();
                    try {
                        Files.writeString(this.path, line);
                        result = "Файл создан, строка записана.";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    // перезаписать файл
                    line = UserInteractions.askForLine();
                    try {
                        Files.writeString(this.path, line);
                        result = "Файл перезаписан новой строкой.";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // дописать строку в файл
                    line = UserInteractions.askForLine();
                    try {
                        if (!lastCharIsLinebreak()) {
                            line = System.getProperty("line.separator") + line;
                        }
                        Files.writeString(this.path, line, StandardOpenOption.APPEND);
                        result = "Строка добавлена в файл";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    // выбрать другой файл
                    setPath(UserInteractions.askToChangePath(path));
                    break;
            }
        }
        return result;
    }

    /**
     * Вызывает метод fileExists, для проверки существования файла
     * и выбора действия, если файл существует
     * Вызывает метод askForLines класса UserInteractions
     * В зависимости от выбранной опции перезаписывает файл или дополняет новыми строками
     * Если файла по указанному пути не существует, создает файл и записывает в него строки.
     * Возвращает строку с отчетом о работе
     */
    String addLinesToFile(){
        List<String> lines;
        String result = "";
        while (result.equals("")){
            int whatToDo = fileExists();
            switch(whatToDo){
                case 0:
                    // создать и заполнить файл
                    lines = UserInteractions.askForLines();
                    try {
                        Files.write(path, lines);
                        result = "Файл создан, строки записаны.";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    // перезаписать файл
                    lines = UserInteractions.askForLines();
                    try {
                        Files.write(path, lines);
                        result = "Файл перезаписан новыми строками.";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // дописать строки в файл
                    lines = UserInteractions.askForLines();
                    try {
                        lines.add(0, "");
                        Files.write(path, lines, StandardOpenOption.APPEND);
                        result = "Строки добавлены в файл";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    // выбрать другой файл
                    setPath(UserInteractions.askToChangePath(path));
                    break;
            }
        }
        return result;
    }
}
