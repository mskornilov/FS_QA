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
     * Вызывает метод askPathToJSON класса ValidateSchema
     * получает из него путь к JSON файлу
     * Если файл начинается с "[", считает, что это массив JSON-объектов
     * и сериализует в массив объектов типа Animal
     * выводит в консоль результат десериализации
     * @throws IOException
     */
    static void deserializeAnimal() throws IOException {
        Path file = ValidateSchema.askForPathToJSON();
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
}

