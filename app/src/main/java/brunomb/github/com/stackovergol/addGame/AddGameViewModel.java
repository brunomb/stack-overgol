package brunomb.github.com.stackovergol.addGame;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Calendar;

import brunomb.github.com.stackovergol.data.DatabaseHelper;
import brunomb.github.com.stackovergol.data.model.Game;
import brunomb.github.com.stackovergol.data.model.GameType;
import brunomb.github.com.stackovergol.data.model.Team;

public class AddGameViewModel extends ViewModel {
    private MutableLiveData<Game> game;
    private MutableLiveData<ArrayList<Team>> teams;

    MutableLiveData<Game> getGame() {
        if (game == null) {
            game = new MutableLiveData<>();
            Calendar calendar = Calendar.getInstance();
            game.setValue(new Game("Stack Overgol", calendar.getTime(), GameType.CHAMPIONSHIP,
                                   8));
        }

        return game;
    }

    MutableLiveData<ArrayList<Team>> getTeams() {
        if (teams == null) {
            teams = new MutableLiveData<>();
            teams.setValue(DatabaseHelper.mockTeams());
        }

        return teams;
    }
}
