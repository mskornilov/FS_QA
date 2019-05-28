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

class ValidateSchema {

    /**
     * Принимает параметр полученный от метода askWhatToDo класса UserInteractions
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
            schemaFile = UserInteractions.askForPathToSchema();
        } else {
            schemaFile = Paths.get("/Animal.json");
        }
        Path json = UserInteractions.askForPathToJSON();
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
