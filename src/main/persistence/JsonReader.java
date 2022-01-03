package persistence;

import model.Player;
import model.PreviousPlayers;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//represents a reader that reads PreviousPlayers from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads previous players from file and returns it
    // throws IOException if an error occurs reading data from file
    public PreviousPlayers read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePreviousPlayers(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // throws IOException if an error occurs reading data from file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach((s -> contentBuilder.append(s)));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses previousplayers from JSON object and return it
    private PreviousPlayers parsePreviousPlayers(JSONObject jsonObject) {
        PreviousPlayers previousPlayers = new PreviousPlayers();
        addPlayers(previousPlayers, jsonObject);
        return previousPlayers;
    }

    // MODIFIES: previousPlayers
    // EFFECTS: parses players from JSON object and adds them to previous players
    private void addPlayers(PreviousPlayers previousPlayers, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(previousPlayers, nextPlayer);
        }
    }

    // MODIFIES: previousPlayers
    // EFFECTS: parses player from JSON object and adds it to previous players
    private void addPlayer(PreviousPlayers previousPlayers, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int score = jsonObject.getInt("score");
        Boolean status = jsonObject.getBoolean("status");
        int participationYear = jsonObject.getInt("participation year");

        Player player = new Player(name, score, status, participationYear);
        previousPlayers.addPlayer(player);
    }

}