package brunomb.github.com.stackovergol.data.model;

/**
 * Created by brunomb on 11/21/2017.
 */

public enum TeamColor {
    RED("red"),
    GREEN("green"),
    BLUE("blue"),
    WHITE("white");

    private final String value;

    TeamColor(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TeamColor getColor(String value) {
        switch (value) {
            case "red":
                return RED;
            case "green":
                return GREEN;
            case "blue":
                return BLUE;
            case "white":
                return WHITE;
            default:
                return RED;
        }
    }
}
