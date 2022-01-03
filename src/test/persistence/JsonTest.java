package persistence;

import model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPlayer(String name, int score, Boolean status, int participationYear, Player player) {
        assertEquals(name, player.getName());
        assertEquals(score, player.getScore());
        assertEquals(status, player.getStatus());
        assertEquals(participationYear, player.getParticipationYear());
    }
}
