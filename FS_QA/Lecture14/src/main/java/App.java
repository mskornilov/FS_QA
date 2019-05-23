import models.Animal;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        int typeOfWork = 0;
        while (typeOfWork != 4){
            while (typeOfWork <=0 || typeOfWork > 4){
                System.out.println("What are we gonna do:");
                System.out.println("1 - deserialize JSON (object or array of objects)");
                System.out.println("2 - serialize object to JSON");
                System.out.println("3 - validate JSON against JSONSchema");
                System.out.println("4 - exit program");

                Scanner in = new Scanner(System.in);
                if (in.hasNextInt()){
                    typeOfWork = in.nextInt();
                }
                switch (typeOfWork){
                    case 1:
                        Deserialize.deserializeAnimal();
                        typeOfWork = 0;
                        Thread.sleep(3000);
                        System.out.println("********************************");
                        break;
                    case 2:
                        Animal animal = Serialize.createNewAnimal();
                        Serialize.serialize(animal);
                        typeOfWork = 0;
                        Thread.sleep(3000);
                        System.out.println("********************************");
                        break;
                    case 3:
                        if (ValidateSchema.validate(ValidateSchema.askWhatToDo())) {
                            System.out.println("Validation successful");
                        } else {
                            System.out.println("Validation error");
                        }
                        Thread.sleep(3000);
                        System.out.println("********************************");
                        typeOfWork = 0;
                        break;
                    case 4:
                        break;
                }
            }
        }
    }
}
