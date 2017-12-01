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
import brunomb.github.com.stackovergol.data.DatabaseHelper;
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
        Game[] games = null;
        games = DatabaseHelper.loadAllGames(appDataBase);
        assertEquals(0, games.length);

        DatabaseHelper.addGame(appDataBase, gameChampionShip);
        DatabaseHelper.addGame(appDataBase, gameElimination);

        games = DatabaseHelper.loadAllGames(appDataBase);
        assertEquals(2, games.length);

        assertEquals("Game A", games[0].getName());
    }
}
