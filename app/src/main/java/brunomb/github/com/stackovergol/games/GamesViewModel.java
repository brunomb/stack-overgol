package brunomb.github.com.stackovergol.games;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import brunomb.github.com.stackovergol.data.AppDataBase;
import brunomb.github.com.stackovergol.data.model.Game;

public class GamesViewModel extends AndroidViewModel {
    private MutableLiveData<Game[]> games;

    private AppDataBase appDataBase;

    public GamesViewModel(@NonNull Application application) {
        super(application);

        appDataBase = AppDataBase.getAppDatabase(this.getApplication());
    }

    MutableLiveData<Game[]> getGames() {
        if (games == null) {
            games = new MutableLiveData<>();
            loadData();
        }

        return games;
    }

    @SuppressLint("StaticFieldLeak")
    private void loadData() {
        new AsyncTask<Void, Void, Game[]>() {
            @Override
            protected Game[] doInBackground(Void... params) {
                return appDataBase.gameDao().loadAllGames();
            }
            @Override
            protected void onPostExecute(Game[] data) {
                games.setValue(data);
            }
        }.execute();
    }
}