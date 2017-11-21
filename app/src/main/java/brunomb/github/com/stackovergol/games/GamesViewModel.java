package brunomb.github.com.stackovergol.games;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

import brunomb.github.com.stackovergol.data.DatabaseHelper;
import brunomb.github.com.stackovergol.data.model.Game;

public class GamesViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Game>> games;

    MutableLiveData<ArrayList<Game>> getGames() {
        if (games == null) {
            games = new MutableLiveData<>();
            games.setValue(DatabaseHelper.mockGames());
        }

        return games;
    }
}
