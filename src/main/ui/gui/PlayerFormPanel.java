package ui.gui;

import javax.swing.*;
import java.awt.*;

// represents gui of player form panel
public class PlayerFormPanel extends JPanel {

    private JFormattedTextField nameTextField;
    private JFormattedTextField yearTextField;

    private JButton submitButton;

    private ImageIcon playerImage;
    private JLabel playerImageLabel;

    // EFFECTS: constructs panel with graphics, text fields, and buttons prompting player to enter their info
    public PlayerFormPanel() {
        add(Box.createRigidArea(new Dimension(50, 300)));
        addGraphics();
        createPlayerForm();
        addButtons();
    }

    // MODIFIES: this
    // EFFECTS: adds appropriate labels and text fields in panel
    public void createPlayerForm() {
        JLabel nameLabel = new JLabel("Name: ");
        nameTextField = new JFormattedTextField("your name");
        nameTextField.setColumns(10);

        JLabel yearLabel = new JLabel("What year is it?");
        yearTextField = new JFormattedTextField("current year");
        yearTextField.setColumns(10);

        //Lay out the labels in a panel.
        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(nameLabel);
        labelPane.add(yearLabel);

        //Layout the text fields in a panel.
        JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        fieldPane.add(nameTextField);
        fieldPane.add(yearTextField);

        //Put the panels in this panel, labels on left,
        //text fields on right.
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);

    }

    // MODIFIES: this
    // EFFECTS: add submit button to panel
    public void addButtons() {
        add(Box.createRigidArea(new Dimension(0, 50)));
        submitButton = new JButton("Submit");
        add(submitButton);
    }

    // MODIFIES: this
    // EFFECTS: add graphics to panel
    public void addGraphics() {

        playerImage = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
                "skuidgameicon3.png")));
        playerImageLabel = new JLabel(playerImage);
        playerImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(playerImageLabel);

    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public String getNameTextField() {
        return nameTextField.getText();
    }

    public int getYearTextField() {
        return Integer.parseInt(yearTextField.getText());
    }
}
