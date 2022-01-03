package ui.gui;

import javax.swing.*;
import java.awt.*;

// represents gui of rock paper scissors game
public class RockPaperScissorsPanel extends GamePanel {

    private JButton rockButton;
    private JButton scissorsButton;
    private JButton paperButton;

    private ImageIcon rockImg;
    private ImageIcon scissorsImg;
    private ImageIcon paperImg;
    private JLabel rockImgLabel;
    private JLabel scissorsImgLabel;
    private JLabel paperImgLabel;

    // create and show rock paper scissors game panel with gui
    public RockPaperScissorsPanel() {
        super("Rock Paper Scissors");
        createAndShowGUI();
    }

    @Override
    // MODIFIES: this
    // EFFECTS: create and show graphical interface for rock paper scissors game
    public void createAndShowGUI() {
        addGraphics();
        addButtons();
        add(Box.createRigidArea(new Dimension(50, 100)));
    }

    @Override
    // Random chance of rock, paper, or scissors
    // MODIFIES: this
    // EFFECTS: this updated with result, result being rock (0), paper (1), scissors (2).
    protected void randomNumberGenerator() {
        upperBound = 3;
        result = rd.nextInt(upperBound);
    }

    @Override
    protected String toStringComputerResult() {
        if (result == 0) {
            return "Rock";
        } else if (result == 1) {
            return "Paper";
        } else {
            return "Scissors";
        }
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds rock, paper, scissors button to panel
    public void addButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout());

        rockButton = new JButton("Rock");
        scissorsButton = new JButton("Scissors");
        paperButton = new JButton("Paper");

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        add(buttonPanel);
    }

    // MODIFIES: this
    // EFFECTS: sets player choice appropriate to user input rock (0)
    public void rockButtonAction() {
        playerChoice = 0;
        randomNumberGenerator();
        comparePlayerToRandom();
    }

    // MODIFIES: this
    // EFFECTS: sets player choice appropriate to user input scissors (2)
    public void scissorsButtonAction() {
        playerChoice = 1;
        randomNumberGenerator();
        comparePlayerToRandom();
    }

    // MODIFIES: this
    // EFFECTS: sets player choice appropriate to user input paper (1)
    public void paperButtonAction() {
        playerChoice = 2;
        randomNumberGenerator();
        comparePlayerToRandom();
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds rock paper scissors graphics to panel
    public void addGraphics() {
        JPanel graphicPanel = new JPanel(new GridLayout());

        rockImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
                "skuidgamerock.jpeg")));
        rockImgLabel = new JLabel(rockImg);
        rockImgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        graphicPanel.add(rockImgLabel);

        paperImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
                "skuidgamepaper.jpeg")));
        paperImgLabel = new JLabel(paperImg);
        paperImgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        graphicPanel.add(paperImgLabel);

        scissorsImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
                "skuidgamescissors.jpeg")));
        scissorsImgLabel = new JLabel(scissorsImg);
        scissorsImgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        graphicPanel.add(scissorsImgLabel);

        add(graphicPanel);
    }

    public JButton getRockButton() {
        return rockButton;
    }

    public JButton getScissorsButton() {
        return scissorsButton;
    }

    public JButton getPaperButton() {
        return paperButton;
    }
}
