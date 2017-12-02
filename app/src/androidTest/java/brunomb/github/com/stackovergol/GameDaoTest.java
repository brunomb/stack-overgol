package brunomb.github.com.stackovergol;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;

import brunomb.github.com.stackovergol.data.AppDataBase;
import brunomb.github.com.stackovergol.data.model.Game;
import brunomb.github.com.stackovergol.data.model.GameType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class GameDaoTest {

    private AppDataBase appDataBase;
    private Game gameChampionShip;
    private Game gameElimination;

    @Before
    public void before() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        appDataBase = Room.inMemoryDatabaseBuilder(context, AppDataBase.class).build();

        Calendar cal = Calendar.getInstance();
        cal.set(2017, 11, 25);
        Date dateA = cal.getTime();
        cal.set(2017, 5, 30);
        Date dateB = cal.getTime();
        gameChampionShip = new Game("Game A", dateA, GameType.CHAMPIONSHIP, 8);
        gameElimination = new Game("Game B", dateB, GameType.ELIMINATION, 8);
    }

    @After
    public void after() throws Exception {
        appDataBase.close();
    }

    @Test
    public void shouldCreateDatabase() {
        assertNotNull(appDataBase);
    }

    @Test
    public void shouldCreateDao() {
        assertNotNull(appDataBase.gameDao());
    }

    @Test
    public void shouldInsertGame() {
        Game[] games;

        games = appDataBase.gameDao().loadAllGames();
        assertEquals(0, games.length);

        appDataBase.gameDao().insert(gameChampionShip);
        appDataBase.gameDao().insert(gameElimination);

        games = appDataBase.gameDao().loadAllGames();
        assertEquals(2, games.length);

        assertEquals("Game A", games[0].getName());
    }

    @Test
    public void shouldGetGame() {
        appDataBase.gameDao().insert(gameChampionShip);

        Game testGame = appDataBase.gameDao().getGame(gameChampionShip.getGameId());
        assertNotNull(testGame);
        assertEquals("Game A", testGame.getName());
    }

    @Test
    public void shouldUpdateGame() {
        appDataBase.gameDao().insert(gameChampionShip);
        Game testGame;
        testGame = appDataBase.gameDao().getGame(gameChampionShip.getGameId());

        assertEquals("Game A", testGame.getName());

        testGame.setName("New name");
        appDataBase.gameDao().update(testGame);

        testGame = appDataBase.gameDao().getGame(gameChampionShip.getGameId());
        assertEquals("New name", testGame.getName());
    }

    @Test
    public void shouldUpdateAllGames() {
        appDataBase.gameDao().insert(gameChampionShip);
        appDataBase.gameDao().insert(gameElimination);
        Game testGame1;
        Game testGame2;

        testGame1 = appDataBase.gameDao().getGame(gameChampionShip.getGameId());
        testGame2 = appDataBase.gameDao().getGame(gameElimination.getGameId());

        assertEquals("Game A", testGame1.getName());
        assertEquals("Game B", testGame2.getName());

        testGame1.setName("New name 1");
        testGame2.setName("New name 2");

        appDataBase.gameDao().updateAll(testGame1, testGame2);

        testGame1 = appDataBase.gameDao().getGame(gameChampionShip.getGameId());
        testGame2 = appDataBase.gameDao().getGame(gameElimination.getGameId());

        assertEquals("New name 1", testGame1.getName());
        assertEquals("New name 2", testGame2.getName());
    }

    @Test
    public void shouldInsertAllGames() {
        Game[] games;
        games = appDataBase.gameDao().loadAllGames();
        assertEquals(0, games.length);

        appDataBase.gameDao().insertAll(gameChampionShip, gameElimination);

        games = appDataBase.gameDao().loadAllGames();
        assertEquals(2, games.length);
    }

    @Test
    public void shouldDeleteGame() {
        Game[] games;
        appDataBase.gameDao().insert(gameChampionShip);

        games = appDataBase.gameDao().loadAllGames();
        assertEquals(1, games.length);

        appDataBase.gameDao().delete(gameChampionShip);

        games = appDataBase.gameDao().loadAllGames();
        assertEquals(0, games.length);
    }

    @Test
    public void shouldDeleteAllGames() {
        Game[] games;

        appDataBase.gameDao().insertAll(gameChampionShip, gameElimination);

        games = appDataBase.gameDao().loadAllGames();
        assertEquals(2, games.length);

        appDataBase.gameDao().deleteAll(gameChampionShip, gameElimination);

        games = appDataBase.gameDao().loadAllGames();
        assertEquals(0, games.length);
    }
}