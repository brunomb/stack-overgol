package brunomb.github.com.stackovergol.data.model;

import java.util.ArrayList;

public class Team {
    private String name;

    private TeamColor color;

    private ArrayList<Player> players;

    public Team(String name, TeamColor color, ArrayList<Player> players) {
        this.name = name;
        this.color = color;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public TeamColor getColor() {
        return color;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(TeamColor color) {
        this.color = color;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
