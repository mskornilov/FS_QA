import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaClient;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

class ValidateSchema {

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

    /**
     * Принимает параметр полученный от метода askWhatToDo
     * в зависимости от параметра запрашивает путь к JSON-схеме и JSON-файлу
     * или только к JSON-файлу
     * Валидирует JSON-файл JSON-схемой (для модели Animal или кастомной)
     * В случае ошибки валидации выводит в консоль информацию о несоответствиях схеме
     * @param param
     * @return true если валидация прошла успешно,
     * false, если произошла ошибка
     * @throws IOException
     */
    static boolean validate(int param) throws IOException {
        Path schemaFile;
        if (param == 2){
            schemaFile = ValidateSchema.askForPathToSchema();
        } else {
            schemaFile = Paths.get("/Animal.json");
        }
        Path json = askForPathToJSON();
        try (InputStream inputSchema = Files.newInputStream(schemaFile)) {
            SchemaLoader schemaLoader = SchemaLoader.builder()
                    .schemaClient(SchemaClient.classPathAwareClient())
                    .schemaJson(new JSONObject(new JSONTokener(inputSchema)))
                    .resolutionScope("classpath:/")
                    .build();
            Schema schema = schemaLoader.load().build();
            InputStream inputJson = Files.newInputStream(json);
            schema.validate(new JSONObject(new JSONTokener(inputJson)));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            e.getCausingExceptions().stream()
                    .map(ValidationException::getMessage)
                    .forEach(System.out::println);
            return false;
        }
        return true;
    }
}
