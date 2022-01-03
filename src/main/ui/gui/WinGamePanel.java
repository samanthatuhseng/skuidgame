package ui.gui;


import javax.swing.*;
import java.awt.*;

// represents gui panel of when player wins
public class WinGamePanel extends SaveGamePanel {

    private ImageIcon crownImg;
    private JLabel crownImgLabel;

    // EFFECTS: construct panel with result of player status (that being alive and winning)
    public WinGamePanel() {
        super("won");
    }

    @Override
    // EFFECTS: adds win graphics to panel
    public void addGraphics() {
        crownImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
                "skuidgamecrown.jpeg")));
        crownImgLabel = new JLabel(crownImg);
        crownImgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(crownImgLabel);
    }
}
