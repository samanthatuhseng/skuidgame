package model;


import org.json.*;
import persistence.Writable;

// Represents a player having a player number, name, participation year, score, and status
public class Player implements Writable {
    private String name;                     // the player's name
    private int participationYear;           // the year the player participated in the games
    private int score;                       // the player's score on how many games won
    private boolean status;                  // true if player is alive, false if player has been eliminated

    /*
     * REQUIRES: name has a non-zero length
     * EFFECTS: name for player is set to name; status for player is true;
     *          score is score; participationYear of player is set to participationYear
     */
    public Player(String name, int score, Boolean status, int participationYear) {
        this.name = name;
        this.score = score;
        this.status = status;
        this.participationYear = participationYear;
    }

    // EFFECTS: return the player's name
    public String getName() {
        return name;
    }

    // EFFECTS: returns the player's score
    public int getScore() {
        return score;
    }

    // EFFECTS: returns the player's participation year
    public int getParticipationYear() {
        return participationYear;
    }

    // EFFECTS: returns the player's status
    public boolean getStatus() {
        return status;
    }

    // EFFECTS: returns 'Alive' if status true, 'Dead' otherwise
    public String getStatusToString() {
        if (status) {
            return "Alive";
        } else {
            return "Dead";
        }
    }

    // MODIFIES: this
    // EFFECTS: eliminates the player
    public void eliminate() {
        EventLog.getInstance().logEvent(new Event(name + " has been eliminated."));
        status = false;
    }

    // MODIFIES: this
    // EFFECTS: sets the participation year
    public void setParticipationYear(int year) {
        participationYear = year;
    }

    // MODIFIES: this
    // EFFECTS: change the player's name
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: add point to player's score if player's status is alive, otherwise score stays the same
    public void addPointToScore() {
        EventLog.getInstance().logEvent(new Event(name
                + " has won this round and will be moved to next game."));
        if (status) {
            score = score + 1;
        }
    }

    @Override
    // EFFECTS: return a string of player's information
    public String toString() {
        return "Player Name: " + name + " | Score: " + score + " | Status: "
                + getStatusToString() + " | Participation Year: " + participationYear + "\n";
    }

    @Override
    // EFFECTS: returns player field as JSON
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("score", score);
        json.put("status", status);
        json.put("participation year", participationYear);
        return json;
    }


}

