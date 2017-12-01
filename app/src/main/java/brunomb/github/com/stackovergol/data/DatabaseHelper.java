package brunomb.github.com.stackovergol.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import brunomb.github.com.stackovergol.data.model.Game;
import brunomb.github.com.stackovergol.data.model.GameType;
import brunomb.github.com.stackovergol.data.model.Player;
import brunomb.github.com.stackovergol.data.model.Team;
import brunomb.github.com.stackovergol.data.model.TeamColor;

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

    public static ArrayList<Team> mockTeams() {
        ArrayList<Team> teamsMock = new ArrayList<>();
        ArrayList<Player> greenTeamPlayers = new ArrayList<>();
        greenTeamPlayers.add(new Player("Genilson", 3.8));
        greenTeamPlayers.add(new Player("Francisco Silva", 3.8));
        greenTeamPlayers.add(new Player("Raildo Mascena", 3.1));
        greenTeamPlayers.add(new Player("Breno Polanski", 3.0));
        greenTeamPlayers.add(new Player("Pedro Alves", 2.6));
        greenTeamPlayers.add(new Player("André Yuri", 2.0));
        Team greenTeam = new Team("Verde", TeamColor.GREEN, greenTeamPlayers);

        ArrayList<Player> blueTeamPlayers = new ArrayList<>();
        blueTeamPlayers.add(new Player("Joeu", 4.5));
        blueTeamPlayers.add(new Player("Gilmar Batista", 3.5));
        blueTeamPlayers.add(new Player("Wallison Fer", 3.0));
        blueTeamPlayers.add(new Player("Igor Martins", 2.8));
        blueTeamPlayers.add(new Player("Ramon Kieveer", 2.5));
        blueTeamPlayers.add(new Player("Theo Moura", 2.0));
        Team blueTeam = new Team("Azul", TeamColor.BLUE, blueTeamPlayers);

        ArrayList<Player> redTeamPlayers = new ArrayList<>();
        redTeamPlayers.add(new Player("Igor Neves", 4.3));
        redTeamPlayers.add(new Player("Raiff Ramalho", 3.5));
        redTeamPlayers.add(new Player("Júlio Andherson", 3.0));
        redTeamPlayers.add(new Player("Bruno Brandão", 2.9));
        redTeamPlayers.add(new Player("Fagner Martins", 2.6));
        redTeamPlayers.add(new Player("Igor Candeia", 1.8));
        Team redTeam = new Team("Vermelho", TeamColor.RED, redTeamPlayers);

        ArrayList<Player> whiteTeamPlayers = new ArrayList<>();
        whiteTeamPlayers.add(new Player("Telles Nóbrega",3.8));
        whiteTeamPlayers.add(new Player("Alison Sousa",3.7));
        whiteTeamPlayers.add(new Player("Ycaro Fernandes",3.5));
        whiteTeamPlayers.add(new Player("Marquinho",2.8));
        whiteTeamPlayers.add(new Player("Gustavo",2.4));
        whiteTeamPlayers.add(new Player("Renan Diniz",2.2));
        Team whiteTeam = new Team("Branco", TeamColor.WHITE, whiteTeamPlayers);

        teamsMock.add(greenTeam);
        teamsMock.add(blueTeam);
        teamsMock.add(redTeam);
        teamsMock.add(whiteTeam);

        return  teamsMock;
    }

    public static Game addGame(final AppDataBase db, Game game) {
        db.gameDao().insert(game);
        return game;
    }

    public static Game[] loadAllGames(final AppDataBase db) {
        return db.gameDao().loadAllGames();
    }

}
