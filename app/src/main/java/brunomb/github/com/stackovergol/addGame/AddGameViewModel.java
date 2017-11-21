package brunomb.github.com.stackovergol.addGame;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Calendar;

import brunomb.github.com.stackovergol.data.model.Game;
import brunomb.github.com.stackovergol.data.model.GameType;

public class AddGameViewModel extends ViewModel {
    private MutableLiveData<Game> game;

    MutableLiveData<Game> getGame() {
        if (game == null) {
            game = new MutableLiveData<>();
            Calendar calendar = Calendar.getInstance();
            game.setValue(new Game("Stack Overgol", calendar.getTime(), GameType.CHAMPIONSHIP,
                                   8));
        }

        return game;
    }
}
