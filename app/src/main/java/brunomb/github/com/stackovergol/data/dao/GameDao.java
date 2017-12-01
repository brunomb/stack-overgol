package brunomb.github.com.stackovergol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import brunomb.github.com.stackovergol.data.model.Game;

@Dao
public interface GameDao {

    @Insert
    void insert(Game game);

    @Insert
    void insertAll(Game... games);

    @Update
    void update(Game game);

    @Update
    void updateAll(Game... games);

    @Delete
    void delete(Game game);

    @Delete
    void deleteAll(Game... games);

    @Query("SELECT * FROM games")
    Game[] loadAllGames();
}
