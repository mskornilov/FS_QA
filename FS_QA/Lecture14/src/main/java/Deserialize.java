import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import models.Animal;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.google.gson.stream.JsonToken.BEGIN_ARRAY;

class Deserialize {

    /**
     * Десериализует JSON в объекты модели Animal
     * Вызывает метод askPathToJSON класса UserInteractions
     * получает из него путь к JSON файлу
     * Если файл начинается с "[", считает, что это массив JSON-объектов
     * и сериализует в массив объектов типа Animal
     * выводит в консоль результат десериализации
     * @throws IOException
     */
    static void deserializeAnimal() throws IOException {
        Path file = UserInteractions.askForPathToJSON();
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(Files.newBufferedReader(file));
        if (reader.peek() == BEGIN_ARRAY){
            Animal[] jsonArray = gson.fromJson(reader, Animal[].class);
            for (Animal obj: jsonArray){
                System.out.println(obj);
            }
        } else {
            Animal animal;
            animal = gson.fromJson(reader, Animal.class);
            System.out.println(animal);
        }
    }




















//    public static void main(String[] args) throws FileNotFoundException, IOException {
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        JsonReader reader = new JsonReader(new FileReader("C:\\Users\\Mo\\Desktop\\Lecture14\\src\\main\\resources\\1.json"));
//        Animal Vasya = gson.fromJson(reader, Animal.class);
//        System.out.println(Vasya);
//
////        try (InputStream inputStream = Deserialize.class.getResourceAsStream("/Animal.json")){
//////            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
//////            Schema schema = SchemaLoader.load(rawSchema);
//////            System.out.println(schema);
////            SchemaLoader schemaLoader = SchemaLoader.builder()
////                    .schemaClient(SchemaClient.classPathAwareClient())
////                    .schemaJson(new JSONObject(new JSONTokener(inputStream)))
////                    .resolutionScope("classpath:/") // setting the default resolution scope
////                    .build();
////            Schema schema = schemaLoader.load().build();
////            InputStream inputJson = Deserialize.class.getResourceAsStream("/1.json");
////            schema.validate(new JSONObject(new JSONTokener(inputJson)));
////        } catch (ValidationException e) {
////            System.out.println(e.toJSON());
////        }
//    }
}

