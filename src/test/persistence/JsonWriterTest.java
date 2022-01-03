package persistence;

import model.Player;
import model.PreviousPlayers;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// This code is taken from course material (JsonDemo)
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            PreviousPlayers pp = new PreviousPlayers();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            PreviousPlayers pp = new PreviousPlayers();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPreviousPlayers.json");
            writer.open();
            writer.write(pp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPreviousPlayers.json");
            pp = reader.read();
            assertTrue(pp.isEmpty());
            assertEquals(0, pp.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            PreviousPlayers pp = new PreviousPlayers();
            pp.addPlayer(new Player("Player 1", 0, false, 2015));
            pp.addPlayer(new Player("Player 2", 3, true, 2016));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPreviousPlayers.json");
            writer.open();
            writer.write(pp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPreviousPlayers.json");
            pp = reader.read();
            List<Player> players = pp.getPlayerList();
            assertEquals(2, players.size());
            checkPlayer("Player 1", 0, false, 2015, players.get(0));
            checkPlayer("Player 2", 3, true, 2016, players.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
