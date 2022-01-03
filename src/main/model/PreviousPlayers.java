package model;

import org.json.*;
import persistence.Writable;

import java.util.*;

// represents a record of previous players that have played the game
public class PreviousPlayers implements Writable {

    private List<Player> playerList;

    //constructs empty list of players
    public PreviousPlayers() {
        playerList = new ArrayList<>();
    }


    // REQUIRES: player must not be on the list already
    // MODIFIES: this
    // EFFECTS: add player to playerList
    public void addPlayer(Player player) {
        EventLog.getInstance().logEvent(new Event(player.getName()
                + " has been added to the previous players list"));
        playerList.add(player);
    }

    //EFFECTS: returns true if there are no previous players; false otherwise.
    public boolean isEmpty() {
        return playerList.isEmpty();
    }

    //EFFECTS: returns the number of players in playerList
    public int length() {
        return playerList.size();
    }

    // EFFECTS: return the list of players
    public List<Player> getPlayerList() {
        return playerList;
    }

    // REQUIRES: player must exist in that index
    // EFFECTS: return the player in the playerList with that index
    public Player getPlayer(int index) {
        return playerList.get(index);
    }

    @Override
    // this code is taken from course material
    // EFFECTS: returns players field as JSON
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("players", playersToJson());
        return json;
    }

    // EFFECTS: returns players in this previous players list as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : playerList) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
