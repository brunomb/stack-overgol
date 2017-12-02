package brunomb.github.com.stackovergol.games;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import brunomb.github.com.stackovergol.data.AppDataBase;
import brunomb.github.com.stackovergol.data.model.Game;

public class GamesViewModel extends AndroidViewModel {
    private LiveData<Game[]> games;

    public GamesViewModel(@NonNull Application application) {
        super(application);

        AppDataBase appDataBase = AppDataBase.getAppDatabase(this.getApplication());
        games = appDataBase.gameDao().loadAllGamesData();
    }

    LiveData<Game[]> getGames() {
        return games;
    }
//    @SuppressLint("StaticFieldLeak")
//    public void addGame(Game game) {
//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... params) {
//                appDataBase.gameDao().insert(game);
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void result) {
////                loadData();
//            }
//        }.execute();
//    }
}