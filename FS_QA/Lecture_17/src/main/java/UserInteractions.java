import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInteractions {
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
            System.out.println("Введите путь к файлу или директории: ");
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
     * Запрашивает у пользователя путь к директории и опциональный параметр "y" / "n"
     * @return String[]
     */
    static String askForParam(){
        String param = "";
        while(!param.equals("y") && !param.equals("n")){
            System.out.println("Хотите удалить содержимое директории? Y/N: ");
            Scanner in = new Scanner(System.in);
            param = in.nextLine().toLowerCase();
        }
        return param;
    }
    /**
     * Запрашивает у пользователя строку
     * Возвращает введенную строку
     * @return String
     */
    static String askForLine(){
        System.out.println("Введите строку:");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    static String askForString(){
        System.out.println("Введите строку для обработки:");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }


    /**
     * Запрашивает у пользователя произвольное количество строк,
     * которые необходимо записать в файл
     * @return List<String>
     */
    static List<String> askForLines(){
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
    static Path askToChangePath(Path file){
        String choice = "";
        while(!choice.equals("y") && !choice.equals("n")){
            System.out.println("Изменить путь к файлу или директории? Y/N: ");
            Scanner in = new Scanner(System.in);
            choice = in.nextLine().toLowerCase();
        }
        if (choice.equals("y")) {
            return askForPath();
        }
        return file;
    }
    /**
     * Запрашивает у пользователя два индекса поочередно,
     * первый индекс: от 0 до (длина строки - 1)
     * второй индекс: от (значение первого индекса + 1) до (длина строки)
     * Возвращает массив из двух индексов
     * @return String[]
     */
    static int[] askForIndexes(MyString string){
        String s = string.getString();
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
