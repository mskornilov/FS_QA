package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Friends {

    private int count;

    @Override
    public String toString(){
        return String.valueOf(count);
    }

}
