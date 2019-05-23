import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import models.Animal;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static com.google.gson.stream.JsonToken.BEGIN_ARRAY;

public class Deserialize {

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

    static void deserializeAnimal() throws IOException {
        Path file = askForPathToJSON();
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(Files.newBufferedReader(file));
        if (reader.peek() == BEGIN_ARRAY){
            Animal[] jsonArray = gson.fromJson(reader, Animal[].class);
            for (Animal obj: jsonArray){
                System.out.println(obj);
            }
        } else {
            Animal vasya = null;
            vasya = gson.fromJson(reader, Animal.class);
            System.out.println(vasya);
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

