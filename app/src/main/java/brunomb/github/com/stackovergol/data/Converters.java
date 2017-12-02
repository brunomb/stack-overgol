package brunomb.github.com.stackovergol.data;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

import brunomb.github.com.stackovergol.data.model.GameType;

public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String gameTypeToString(GameType type) {
        return type == null ? null : type.getValue();
    }

    @TypeConverter
    public static GameType stringToGameType(String value) {
        return value == null ? null : GameType.getType(value);
    }
}
