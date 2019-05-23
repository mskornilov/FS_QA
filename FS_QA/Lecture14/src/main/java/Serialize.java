import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Animal;
import models.Friends;

class Serialize {


    /**
     * создает новый экземпляр класса Animal для последующей сериализации
     * @return объект типа Animal
     */
    static Animal createNewAnimal(){
        Animal vasilij = new Animal();
        Friends f = new Friends();
        f.setCount(4);
        String[] colors = {"Orange", "White", "Purple"};
        vasilij.setName("Vasilij");
        vasilij.setAnimalType("Cat");
        vasilij.setAge(4);
        vasilij.setHasTail(true);
        vasilij.setColors(colors);
        vasilij.setFriendsCount(f);

        return vasilij;
    }


    /**
     * сериализует объект типа Animal
     * и выводит в консоль JSON отфарматированный pretty printer'ом
     * @param obj
     */
    static void serialize(Animal obj){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(obj);
        System.out.println(json);
    }
}
