package ui.gui;

import javax.swing.*;
import java.awt.*;

// represents gui of start menu panel
public class StartMenuPanel extends JPanel {

    private JButton playButton;
    private JButton loadButton;
    private JButton viewButton;
    private JButton quitButton;

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;

    private ImageIcon skuidGameLogo;
    private JLabel skuidGameImageLabel;

    // EFFECTS: constructs the start menu panel and show it
    public StartMenuPanel() {
        showStartMenu();
    }

    // MODIFIES: this
    // EFFECTS: display the start menu with the correct buttons
    public void showStartMenu() {
        setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createRigidArea(new Dimension(50, 25)));

        addWelcomeMessage();
        addLogo();
        addButtonPanel();
    }

    // MODIFIES: this
    // EFFECTS: add welcome message on start menu panel
    public void addWelcomeMessage() {
        JLabel welcomeMessage = new JLabel("Welcome to Skuid Game");
        welcomeMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        welcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welcomeDescription = new JLabel("You must survive all three mini-games to win."
                + " Otherwise, you will be eliminated.");
        welcomeDescription.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(welcomeMessage);
        add(welcomeDescription);
    }

    // MODIFIES: this
    // EFFECTS: adds skuid game logo to panel
    public void addLogo() {
        skuidGameLogo = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
                "skuidgameproj.jpeg")));
        skuidGameImageLabel = new JLabel(skuidGameLogo);
        skuidGameImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(skuidGameImageLabel);
    }

    // MODIFIES: this
    // EFFECTS: add buttons on start menu panel
    public void addButtonPanel() {
        JPanel menuButtonPanel = new JPanel();
        menuButtonPanel.setLayout(new BoxLayout(menuButtonPanel, BoxLayout.PAGE_AXIS));
        menuButtonPanel.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 2));

        playButton = new JButton("Play");
        viewButton = new JButton("View Previous Players");
        loadButton = new JButton("Load Previous Players");
        quitButton = new JButton("Quit Game");

        menuButtonPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        menuButtonPanel.add(playButton);
        menuButtonPanel.add(viewButton);
        menuButtonPanel.add(loadButton);
        menuButtonPanel.add(quitButton);

        menuButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(menuButtonPanel);
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getViewButton() {
        return viewButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

}
