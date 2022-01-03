package persistence;

import model.Player;
import model.PreviousPlayers;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

// This code is taken from course material (JsonDemo)
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PreviousPlayers pp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPreviousPlayers() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPreviousPlayers.json");
        try {
            PreviousPlayers pp = reader.read();
            assertTrue(pp.isEmpty());
            assertEquals(0, pp.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPreviousPlayers() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPreviousPlayers.json");
        try {
            PreviousPlayers pp = reader.read();
            List<Player> players = pp.getPlayerList();
            assertEquals(2, players.size());
            checkPlayer("Player 1", 0, false, 2015, players.get(0));
            checkPlayer("Player 2", 3, true, 2016, players.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
