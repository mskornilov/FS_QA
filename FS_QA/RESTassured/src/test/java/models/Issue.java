package models;


import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class Issue {

    private Integer id;
    private Integer number;
    private String body;
    private Boolean locked;

}
