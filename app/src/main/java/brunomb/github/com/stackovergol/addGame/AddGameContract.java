package brunomb.github.com.stackovergol.addGame;

import java.util.Date;

import brunomb.github.com.stackovergol.BasePresenter;
import brunomb.github.com.stackovergol.BaseView;
import brunomb.github.com.stackovergol.data.model.GameType;

public class AddGameContract {

    interface View extends BaseView<Presenter> {
        void showSaveSuccess();
        void showSaveFailure();
    }

    interface Presenter extends BasePresenter {
        void saveGame(String name, Date date, Integer duration, GameType type);
        void showPlayers();
    }
}
