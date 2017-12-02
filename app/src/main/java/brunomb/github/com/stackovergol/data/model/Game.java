package brunomb.github.com.stackovergol.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "games")
public class Game {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private String gameId;

    @ColumnInfo(name = "name")
    private String name;

    @Ignore
    private Date date;

    @ColumnInfo(name = "type")
    private String typeValue;

    @ColumnInfo(name = "default_duration")
    private int duration;

    public Game() {}

    public Game(String name, Date date, GameType type, int duration) {
        this.name = name;
        this.date = date;
        this.typeValue = type.getValue();
        this.duration = duration;
        generateId();
    }

    public String getName() {
        return name;
    }

    @NonNull
    public String getGameId() {
        return gameId;
    }

    public GameType getType() {
        return GameType.getType(typeValue);
    }

    public Date getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
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

    public String getTypeValue() {
        return typeValue;
    }

    public void setGameId(@NonNull String gameId) {
        this.gameId = gameId;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}