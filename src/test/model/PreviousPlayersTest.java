package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PreviousPlayersTest {

    PreviousPlayers testPlayerListEmpty;
    PreviousPlayers testPlayerListNotEmpty;
    Player player1;
    Player player2;
    Player player3;

    @BeforeEach
    public void setup() {
        testPlayerListEmpty = new PreviousPlayers();
        testPlayerListNotEmpty = new PreviousPlayers();
        player1 = new Player("Bob", 0, false, 2015);
        player2 = new Player("Bill", 0, false, 2015);
        player3 = new Player("Joe", 0, false, 2017);

        testPlayerListNotEmpty.addPlayer(player1);
    }

    @Test
    public void testAddPlayerEmpty() {
        List<Player> emptyTest = testPlayerListEmpty.getPlayerList();
        assertTrue(emptyTest.isEmpty());
        assertTrue(testPlayerListEmpty.isEmpty());

        testPlayerListEmpty.addPlayer(player1);

        assertFalse(testPlayerListEmpty.isEmpty());
        assertEquals(1, testPlayerListEmpty.length());
        assertEquals(player1, testPlayerListEmpty.getPlayer(0));
    }

    @Test
    public void testAddPlayer() {
        List<Player> notEmptyTest = testPlayerListNotEmpty.getPlayerList();
        assertFalse(notEmptyTest.isEmpty());
        assertFalse(testPlayerListNotEmpty.isEmpty());
        assertEquals(1, testPlayerListNotEmpty.length());

        testPlayerListNotEmpty.addPlayer(player2);
        testPlayerListNotEmpty.addPlayer(player3);

        assertFalse(testPlayerListNotEmpty.isEmpty());
        assertEquals(3, testPlayerListNotEmpty.length());
        assertEquals(player1, testPlayerListNotEmpty.getPlayer(0));
        assertEquals(player2, testPlayerListNotEmpty.getPlayer(1));
        assertEquals(player3, testPlayerListNotEmpty.getPlayer(2));
    }
}
