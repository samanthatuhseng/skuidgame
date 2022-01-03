package ui.gui;

import javax.swing.*;
import java.awt.*;

// represents the panel that prompts player to save their attempt or not
public abstract class SaveGamePanel extends JPanel {

    private JButton yesSaveButton;
    private JButton noSaveButton;

    private String status;                          //string representing whether they won or died

    // EFFECTS: constructs the panel with appropriate graphics, message, and buttons
    public SaveGamePanel(String status) {
        add(Box.createRigidArea(new Dimension(50, 100)));
        this.status = status;
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        setAlignmentX(CENTER_ALIGNMENT);

        addGraphics();
        add(Box.createRigidArea(new Dimension(50, 20)));
        addMessage();
        add(Box.createRigidArea(new Dimension(50, 20)));
        addButtons();
    }

    // MODIFIES: this
    // EFFECTS: adds graphics to panel
    public abstract void addGraphics();

    // MODIFIES: this
    // EFFECTS: adds appropriate game status message
    public void addMessage() {
        JLabel message = new JLabel("You have " + status + "!");
        message.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        add(message);

        JLabel saveMessage = new JLabel("Would you like to record your attempt?");
        saveMessage.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
        add(saveMessage);

        message.add(Box.createRigidArea(new Dimension(50, 25)));
        saveMessage.add(Box.createRigidArea(new Dimension(50, 25)));

        message.setAlignmentX(CENTER_ALIGNMENT);
        saveMessage.setAlignmentX(CENTER_ALIGNMENT);
    }

    // MODIFIES: this
    // EFFECTS: adds yes or no to saving attempt buttons to panel
    public void addButtons() {
        yesSaveButton = new JButton("Yes");
        noSaveButton = new JButton("No");

        add(yesSaveButton);
        add(noSaveButton);

        yesSaveButton.setAlignmentX(CENTER_ALIGNMENT);
        noSaveButton.setAlignmentX(CENTER_ALIGNMENT);
    }

    public JButton getYesSaveButton() {
        return yesSaveButton;
    }

    public JButton getNoSaveButton() {
        return noSaveButton;
    }

}
