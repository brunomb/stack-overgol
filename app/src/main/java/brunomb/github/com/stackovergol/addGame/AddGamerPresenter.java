package brunomb.github.com.stackovergol.addGame;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import brunomb.github.com.stackovergol.data.model.GameType;
import brunomb.github.com.stackovergol.data.model.Player;
import brunomb.github.com.stackovergol.util.SOGLog;

public class AddGamerPresenter implements AddGameContract.Presenter {

    private static ArrayList<Player> players;

    private final AddGameContract.View mAddGameView;

    AddGamerPresenter(AddGameContract.View addGameView) {
        players = new ArrayList<>();
        Player player1 = new Player("Telles Nóbrega", 3.0);
        Player player2 = new Player("Allen Nepomuceno", 3.0);
        Player player3 = new Player("Raildo Mascena", 3.0);
        Player player4 = new Player("Igor Candeia", 3.0);
        Player player5 = new Player("Renan Diniz", 3.0);
        Player player6 = new Player("Joeu ", 3.0);
        Player player7 = new Player("Igor Neves", 3.0);
        Player player8 = new Player("Bruno Ribeiro", 3.0);
        Player player9 = new Player("Dunfrey Pires", 3.0);
        Player player10 = new Player("Fellype ", 3.0);
        Player player11 = new Player("Genilson Medeiros", 3.0);
        Player player12 = new Player("Bruno Brandão", 3.0);
        Player player13 = new Player("Theo Moura", 3.0);
        Player player14 = new Player("Arthur Souza Ribeiro", 3.0);
        Player player15 = new Player("Raiff Ramalho", 3.0);
        Player player16 = new Player("Alison Sousa", 3.0);
        Player player17 = new Player("Ramon Kieveer", 3.0);
        Player player18 = new Player("Gustavo Cavalcanti", 3.0);
        Player player19 = new Player("Fagner Martins", 3.0);
        Player player20 = new Player("Gilmar Batista", 3.0);
        Player player21 = new Player("Matheus Batista", 3.0);
        Player player22 = new Player("Igor Martins", 3.0);
        Player player23 = new Player("Erinaldo Alves", 3.0);
        Player player24 = new Player("Matheus Coelho", 3.0);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        players.add(player6);
        players.add(player7);
        players.add(player8);
        players.add(player9);
        players.add(player10);
        players.add(player11);
        players.add(player12);
        players.add(player13);
        players.add(player14);
        players.add(player15);
        players.add(player16);
        players.add(player17);
        players.add(player18);
        players.add(player19);
        players.add(player20);
        players.add(player21);
        players.add(player22);
        players.add(player23);
        players.add(player24);

        mAddGameView = addGameView;
        mAddGameView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void saveGame(String name, Date date, Integer duration, GameType type) {
        SimpleDateFormat format = new SimpleDateFormat("E, dd MMM yyyy", Locale.ENGLISH);
        SOGLog.i("name: " + name + ", Duration " + duration +", Type: " + type + ", Date: " + format.format(date));
    }

    @Override
    public void showPlayers() {
        for (Player p : players) {
            SOGLog.i(p.getName());
        }
    }
}
