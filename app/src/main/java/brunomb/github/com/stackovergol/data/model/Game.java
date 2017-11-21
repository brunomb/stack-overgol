package brunomb.github.com.stackovergol.data.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Game extends RealmObject {

    @Required
    private String name;

    @Required
    private Date date;

    @PrimaryKey
    private String gameId;

    @Required
    private String typeValue;

    public Game() {}

    public Game(String name, Date date, GameType type) {
        this.name = name;
        this.date = date;
        this.typeValue = type.getValue();
        generateId();
    }

    public String getName() {
        return name;
    }

    public String getGameId() {
        return gameId;
    }

    public GameType getType() {
        return GameType.getType(typeValue);
    }

    public String getDateString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        return dateFormatter.format(date);
    }

    private void generateId() {
        SimpleDateFormat datePrefixFormatter = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        String typePrefixFormatter = "";
        switch (getType()) {
            case CHAMPIONSHIP:
                typePrefixFormatter = "c";
                break;
            case ELIMINATION:
                typePrefixFormatter = "e";
                break;
        }
        this.gameId = typePrefixFormatter + datePrefixFormatter.format(date);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(GameType type) {
        this.typeValue = type.getValue();
    }
}