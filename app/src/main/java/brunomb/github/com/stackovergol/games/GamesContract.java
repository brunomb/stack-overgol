package brunomb.github.com.stackovergol.games;

import java.util.ArrayList;

import brunomb.github.com.stackovergol.BasePresenter;
import brunomb.github.com.stackovergol.BaseView;
import brunomb.github.com.stackovergol.data.model.Game;

public class GamesContract {

    interface View extends BaseView<Presenter> {
        void listGames(ArrayList<Game> games);
    }

    interface Presenter extends BasePresenter {
    }
}
