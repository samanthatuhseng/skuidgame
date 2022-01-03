package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

// represents gui of games
public abstract class GamePanel extends JPanel {

    protected Integer playerChoice;         // Player's choice of input
    protected Integer result;               // Computer randomization of a number
    protected Boolean gameStatus;           // Whether the player wins (true) or loses (false)
    protected Random rd = new Random();     // Creating a Random object
    protected int upperBound;               // Highest random number that can be generated
    private JLabel gameLabel;
    private String computerResult;
    protected boolean gameListenersAdded;

    // EFFECTS: constructs game panel with name of game and appropriate labels
    public GamePanel(String nameOfGame) {
        new JPanel();
        add(Box.createRigidArea(new Dimension(50, 100)));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        gameListenersAdded = false;

        gameLabel = new JLabel("The Game is: " + nameOfGame);
        gameLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        gameLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(gameLabel);

    }

    // EFFECTS: shows instructions and gui of game, prompts user to choose button
    public abstract void createAndShowGUI();

    // EFFECTS: return game status
    public Boolean getGameStatus() {
        return gameStatus;
    }

    // Randomly generates a number appropriate to the present game
    // MODIFIES: this
    // EFFECTS: this updated with result
    protected abstract void randomNumberGenerator();

    // MODIFIES: this
    // EFFECTS: determines whether player response/guess is same as random generated number
    public void comparePlayerToRandom() {
        if (Objects.equals(result, playerChoice)) {
            gameStatus = true;
            computerResult = toStringComputerResult();
        } else {
            computerResult = toStringComputerResult();
            gameStatus = false;
        }
    }

    // EFFECTS: return randomly generated result
    protected abstract String toStringComputerResult();

    public String getComputerResult() {
        return computerResult;
    }

    // MODIFIES: this
    // EFFECTS: add appropriate buttons of game to panel
    public abstract void addButtons();

    // MODIFIES: this
    // EFFECTS: add appropriate graphics of game to panel
    public abstract void addGraphics();
}
