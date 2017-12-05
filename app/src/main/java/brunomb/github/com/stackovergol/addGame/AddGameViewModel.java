package brunomb.github.com.stackovergol.addGame;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import brunomb.github.com.stackovergol.data.AppDataBase;
import brunomb.github.com.stackovergol.data.DatabaseHelper;
import brunomb.github.com.stackovergol.data.model.Game;
import brunomb.github.com.stackovergol.data.model.GameType;
import brunomb.github.com.stackovergol.data.model.Team;
import brunomb.github.com.stackovergol.util.SOGLog;

public class AddGameViewModel extends AndroidViewModel {
    private MutableLiveData<Game> game;
    private MutableLiveData<ArrayList<Team>> teams;
    private AppDataBase appDataBase;
    public Boolean itsGameValid = false;

    public AddGameViewModel(@NonNull Application application) {
        super(application);

        appDataBase = AppDataBase.getAppDatabase(this.getApplication());
    }

    MutableLiveData<Game> getGame() {
        if (game == null) {
            game = new MutableLiveData<>();
            Calendar calendar = Calendar.getInstance();
            game.setValue(new Game("Stack Overgol", calendar.getTime(), GameType.CHAMPIONSHIP,
                    8));
            checkGame();
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

    public void setName(String name) {
        if (game.getValue() != null) {
            game.getValue().setName(name);
            SOGLog.v("---------------");
            SOGLog.v("New name: " + game.getValue().getName());
            SOGLog.v("---------------");
        }
    }

    public void setDate(Date date) {
        if (game.getValue() != null) {
            game.getValue().setDate(date);
            game.getValue().generateId();
            SOGLog.v("---------------");
            SOGLog.v("New date: " +  game.getValue().getDateString());
            SOGLog.v("---------------");
        }
    }

    public void setDuration(int duration) {
        if (game.getValue() != null) {
            game.getValue().setDuration(duration);
            SOGLog.v("---------------");
            SOGLog.v("New duration: " + game.getValue().getDuration());
            SOGLog.v("---------------");
        }
    }

    public void setType(GameType type) {
        if (game.getValue() != null) {
            game.getValue().setType(type);
            SOGLog.v("---------------");
            SOGLog.v("New game type: " + game.getValue().getType().getValue());
            SOGLog.v("---------------");
        }
    }

    @SuppressLint("StaticFieldLeak")
    public void checkGame() {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                return appDataBase.gameDao().getGame(game.getValue().getGameId()) == null;
            }
            @Override
            protected void onPostExecute(Boolean isValid) {
                SOGLog.v("---------------");
                SOGLog.v("Null? " + isValid);
                SOGLog.v("---------------");
                itsGameValid = isValid;
            }
        }.execute();
    }
}
