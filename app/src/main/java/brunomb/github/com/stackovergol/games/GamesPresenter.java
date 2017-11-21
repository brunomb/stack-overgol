package brunomb.github.com.stackovergol.games;

import brunomb.github.com.stackovergol.data.DatabaseHelper;

public class GamesPresenter implements GamesContract.Presenter {

    private final GamesContract.View mGamesView;

    public GamesPresenter(GamesContract.View gamesView) {
        mGamesView = gamesView;
        mGamesView.setPresenter(this);
    }

    @Override
    public void start() {
        mGamesView.listGames(DatabaseHelper.mockGames());
    }
}
