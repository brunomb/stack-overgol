package brunomb.github.com.stackovergol.data.model;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Player extends RealmObject {

    @Required
    private String name;

    @Required
    private Double rating;

    public Player() {}

    public Player(String name, Double rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public Double getRating() {
        return rating;
    }
}