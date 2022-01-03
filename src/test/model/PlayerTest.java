package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player testPlayer;
    Player deadPlayer;

    @BeforeEach
    public void setup() {
        testPlayer = new Player("Bob", 0, true, 2015);
        deadPlayer = new Player("Dead Person", 0, false, 2017);
    }

    @Test
    public void testConstructor() {
        assertEquals("Bob", testPlayer.getName());
        assertEquals(0, testPlayer.getScore());
        assertTrue(testPlayer.getStatus());
        assertEquals(2015, testPlayer.getParticipationYear());
    }

    @Test
    public void testSetParticipationYear() {
        testPlayer.setParticipationYear(2021);

        assertEquals(2021, testPlayer.getParticipationYear());
    }

    @Test
    public void testSetName() {
        assertEquals("Bob", testPlayer.getName());

        testPlayer.setName("Joe");

        assertEquals("Joe", testPlayer.getName());
    }

    @Test
    public void testElimination() {
        assertTrue(testPlayer.getStatus());

        testPlayer.eliminate();

        assertFalse(testPlayer.getStatus());
        assertEquals("Dead", testPlayer.getStatusToString());
    }

    @Test
    public void testAddPointToScoreAlivePlayer() {
        assertEquals(0, testPlayer.getScore());

        testPlayer.addPointToScore();

        assertEquals(1, testPlayer.getScore());
        assertTrue(testPlayer.getStatus());
        assertEquals("Alive", testPlayer.getStatusToString());
    }

    @Test
    public void testAddPointsToScoreAlivePlayer() {
        assertEquals(0, testPlayer.getScore());

        testPlayer.addPointToScore();
        testPlayer.addPointToScore();
        testPlayer.addPointToScore();

        assertEquals(3, testPlayer.getScore());
        assertTrue(testPlayer.getStatus());
    }

    @Test
    public void testAddPointToScoreDeadPlayer() {
        assertEquals(0, deadPlayer.getScore());
        assertFalse(deadPlayer.getStatus());

        deadPlayer.addPointToScore();

        assertEquals(0, deadPlayer.getScore());
        assertFalse(deadPlayer.getStatus());
    }

    @Test
    public void testToString() {
        assertEquals("Player Name: Bob | Score: 0 | Status: Alive | Participation Year: "
                + "2015\n", testPlayer.toString());

        testPlayer.addPointToScore();

        assertEquals("Player Name: Bob | Score: 1 | Status: Alive | Participation Year: "
                + "2015\n", testPlayer.toString());

        testPlayer.eliminate();

        assertEquals("Player Name: Bob | Score: 1 | Status: Dead | Participation Year: "
                + "2015\n", testPlayer.toString());
    }
}