package models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Animal {

    @SerializedName("animal_type")
    private String animalType;
    private String name;
    private int age;
    @SerializedName("has_tail")
    private boolean hasTail;
    private String[] colors;
    @SerializedName(value="Friends", alternate={"cat_friends", "friends"})
    private Friends FriendsCount;
}
