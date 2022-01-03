package ui.gui;

import javax.swing.*;
import java.awt.*;

// represents gui of coin toss game
public class CoinTossPanel extends GamePanel {

    private JButton headsButton;
    private JButton tailsButton;

    private ImageIcon coinImg;
    private JLabel coinImgLabel;

    // create and show coin toss game panel with gui
    public CoinTossPanel() {
        super("Coin Toss Game");
        createAndShowGUI();
    }

    @Override
    // MODIFIES: this
    // EFFECTS: create and show graphical interface for coin toss game
    public void createAndShowGUI() {
        setBackground(Color.white);
        addButtons();
    }

    @Override
    // Flip the coin to be heads or tails
    // MODIFIES: this
    // EFFECTS: this updated with result, result being heads (0) or tails (1).
    protected void randomNumberGenerator() {
        upperBound = 2;
        result = rd.nextInt(upperBound);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds button to panel
    public void addButtons() {
        JPanel layoutPanel = new JPanel(new GridLayout());

        headsButton = new JButton("Heads");
        tailsButton = new JButton("Tails");

        add(Box.createRigidArea(new Dimension(50, 50)));

        layoutPanel.add(headsButton);

        addGraphics();
        layoutPanel.add(coinImgLabel);

        layoutPanel.add(tailsButton);

        add(layoutPanel);

        add(Box.createRigidArea(new Dimension(50, 250)));
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds graphics to the panel
    public void addGraphics() {
        coinImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
                "skuidgamecoin.jpeg")));
        coinImgLabel = new JLabel(coinImg);
        coinImgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public JButton getHeadsButton() {
        return headsButton;
    }

    public JButton getTailsButton() {
        return tailsButton;
    }

    // MODIFIES: GamePanel
    // EFFECTS: set player choice and compare player to computer generated random choice
    public void headsButtonAction() {
        playerChoice = 0;
        randomNumberGenerator();
        comparePlayerToRandom();

    }

    // MODIFIES: GamePanel
    // EFFECTS: set player choice and compare player to computer generated random choice
    public void tailsButtonAction() {
        playerChoice = 1;
        randomNumberGenerator();
        comparePlayerToRandom();

    }

    @Override
    // EFFECTS: return computer answer, 'Heads' if 0, 'Tails' if 1.
    protected String toStringComputerResult() {
        if (result == 0) {
            return "Heads";
        } else {
            return "Tails";
        }
    }

}
