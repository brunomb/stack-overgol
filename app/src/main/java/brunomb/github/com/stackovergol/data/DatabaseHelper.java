package brunomb.github.com.stackovergol.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import brunomb.github.com.stackovergol.data.model.Game;
import brunomb.github.com.stackovergol.data.model.GameType;

public class DatabaseHelper {

    public static ArrayList<Game> mockGames() {
        ArrayList<Game> gamesMock = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(2017, 11, 25);
        Date dateA = cal.getTime();
        cal.set(2017, 5, 30);
        Date dateB = cal.getTime();
        Game gameChampionShip = new Game("Game A", dateA, GameType.CHAMPIONSHIP, 8);
        Game gameElimination = new Game("Game B", dateB, GameType.ELIMINATION, 8);
        gamesMock.add(gameChampionShip);
        gamesMock.add(gameElimination);
        return gamesMock;
    }

}
