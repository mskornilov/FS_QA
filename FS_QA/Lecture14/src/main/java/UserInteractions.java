import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

class UserInteractions {
    /**
     * Запрашивает у пользователя ввод, для выбора одного из вариантов валидации:
     * 1 - валидация схемой, описывающей модель Animal
     * 2 - валидация JSON'a кастомной схемой
     * @return пользовательский выбор
     */
    static int askWhatToDo(){
        int whatToDo = 0;
        while (whatToDo < 1 || whatToDo > 2){
            System.out.println("What schema should we use?");
            System.out.println("1 - Use default Animal Schema.");
            System.out.println("2 - Enter path to the new one.");
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()){
                whatToDo = in.nextInt();
            }
        }
        return whatToDo;
    }
    /**
     * Запрашивает у пользователя ввод пути к файлу с JSON
     * @return объект типа Path
     */
    static Path askForPathToJSON(){
        String path = "";
        while (path.equals("")){
            System.out.println("Enter path to JSON");
            Scanner in = new Scanner(System.in);
            path = in.nextLine();
            Path file = Paths.get(path);
            if (!Files.exists(file)) {
                System.out.println("File does not exists");
                path = "";
            }
        }
        return Paths.get(path);
    }
    /**
     * Запрашивает у пользователя путь к файлу с JSON-схемой
     * @return объект типа Path
     */
    static Path askForPathToSchema(){
        String path = "";
        while (path.equals("")){
            System.out.println("Enter path to JSON Schema: ");
            Scanner in = new Scanner(System.in);
            path = in.nextLine();
            Path file = Paths.get(path);
            if (!Files.exists(file)) {
                System.out.println("File does not exists");
                path = "";
            }
        }
        return Paths.get(path);
    }

}
