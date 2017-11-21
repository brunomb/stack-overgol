package brunomb.github.com.stackovergol.data.model;

public enum GameType {
    CHAMPIONSHIP("championship"),
    ELIMINATION("elimination");

    private final String value;

    GameType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static GameType getType(String value) {
        switch (value) {
            case "championship":
                return CHAMPIONSHIP;
            case "elimination":
                return ELIMINATION;
            default:
                return ELIMINATION;
        }
    }
}
