package brunomb.github.com.stackovergol;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import brunomb.github.com.stackovergol.data.model.Game;
import brunomb.github.com.stackovergol.data.model.GameType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameTest {

    private Game gameChampionShip;
    private Game gameElimination;

    @Before
    public void before() {
        Calendar cal = Calendar.getInstance();
        cal.set(2017, 11, 25);
        Date dateA = cal.getTime();
        cal.set(2017, 5, 30);
        Date dateB = cal.getTime();
        gameChampionShip = new Game("Game A", dateA, GameType.CHAMPIONSHIP);
        gameElimination = new Game("Game B", dateB, GameType.ELIMINATION);
    }

    @Test
    public void testConstructor() throws Exception {
        assertNotNull(gameChampionShip);
        assertNotNull(gameElimination);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Game A", gameChampionShip.getName());
        assertEquals("Game B", gameElimination.getName());
    }

    @Test
    public void testGetGameId() throws Exception {
        assertEquals("c20171225", gameChampionShip.getGameId());
        assertEquals("e20170630", gameElimination.getGameId());
    }

    @Test
    public void testGetGameType() throws  Exception {
        assertEquals(GameType.CHAMPIONSHIP, gameChampionShip.getType());
        assertEquals(GameType.ELIMINATION, gameElimination.getType());
    }

    @Test
    public void testGetDateString() throws Exception {
        assertEquals("25/12/2017", gameChampionShip.getDateString());
        assertEquals("30/06/2017", gameElimination.getDateString());
    }

    @Test
    public void testSetName() throws Exception {
        gameChampionShip.setName("Game C");
        assertEquals("Game C", gameChampionShip.getName());
    }

    @Test
    public void testSetDate() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(2020, 11, 25);
        Date newDate = cal.getTime();
        gameChampionShip.setDate(newDate);
        assertEquals("25/12/2020", gameChampionShip.getDateString());
    }

    @Test
    public void testSetType() throws Exception {
        gameChampionShip.setType(GameType.ELIMINATION);
        assertEquals(GameType.ELIMINATION, gameChampionShip.getType());
    }
}
