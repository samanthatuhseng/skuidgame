package ui.gui;

import javax.swing.*;
import java.awt.*;

// represents gui panel of when player loses
public class LoseGamePanel extends SaveGamePanel {

    private ImageIcon deathImg;
    private JLabel deathImgLabel;

    // EFFECTS: construct panel with result of player status (that being dead)
    public LoseGamePanel() {
        super("died");
    }

    @Override
    // EFFECTS: adds losing graphics to panel
    public void addGraphics() {
        deathImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
                "skuidgameskull.png")));
        deathImgLabel = new JLabel(deathImg);
        deathImgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(deathImgLabel);
    }
}
