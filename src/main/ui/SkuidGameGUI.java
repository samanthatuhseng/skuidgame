package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// represents the gui of skuid game
public class SkuidGameGUI {

    private PreviousPlayers previousPlayersList;
    private Player player;

    private static final Integer MINIMUM_YEAR = 2015;
    private static final Integer CURRENT_YEAR = 2021;

    private static final String JSON_STORE = "./data/players.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 600;

    private JFrame frame;

    private StartMenuPanel startMenuPanel;
    private PlayerFormPanel playerFormPanel;
    private CoinTossPanel coinTossPanel;
    private RockPaperScissorsPanel rockPaperScissorsPanel;
    private LoseGamePanel loseGamePanel;
    private WinGamePanel winGamePanel;

    private boolean startMenuListenersAdded;
    private boolean playerFormListenersAdded;
    private boolean coinTossListenersAdded;
    private boolean rockPaperScissorsListenersAdded;
    private boolean winGameListenersAdded;
    private boolean loseGameListenersAdded;

    //constructs graphic user interface for skuid game
    public SkuidGameGUI() {
        startMenuListenersAdded = false;
        playerFormListenersAdded = false;
        coinTossListenersAdded = false;
        rockPaperScissorsListenersAdded = false;
        winGameListenersAdded = false;
        loseGameListenersAdded = false;

        previousPlayersList = new PreviousPlayers();
        this.jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        createAndShowGUI();
    }

    // MODIFIES: this
    // EFFECTS: Create the GUI for skuid game and show it.
    public void createAndShowGUI() {
        frame = new JFrame("Skuid Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Display the window.
        frame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setLocation(790, 215);
        frame.setVisible(true);

        addPanels();

        openStartMenu();

    }


    // MODIFIES: this
    // EFFECTS: creates new instances of all panels
    public void addPanels() {
        startMenuPanel = new StartMenuPanel();
        playerFormPanel = new PlayerFormPanel();
        coinTossPanel = new CoinTossPanel();
        rockPaperScissorsPanel = new RockPaperScissorsPanel();
        loseGamePanel = new LoseGamePanel();
        winGamePanel = new WinGamePanel();
    }

    // MODIFIES: this
    // EFFECTS: adds start menu panel to main frame
    public void openStartMenu() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(startMenuPanel);
        startMenuPanel.setVisible(true);
        if (!startMenuListenersAdded) {
            startMenuPlayAction();
            startMenuViewAction();
            startMenuLoadAction();
            startMenuQuitAction();
        }
        startMenuListenersAdded = true;
    }

    // MODIFIES: this
    // EFFECTS: sends player to player form panel to enter their information
    public void startMenuPlayAction() {
        startMenuPanel.getPlayButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                startMenuPanel.setVisible(false);
                playerFormPanel.setVisible(true);
                openPlayerForm();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: loads previous players from file
    public void startMenuLoadAction() {
        startMenuPanel.getLoadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    previousPlayersList = jsonReader.read();
                    StringBuilder stringList = new StringBuilder();
                    for (Player p : previousPlayersList.getPlayerList()) {
                        stringList.append("\nLoaded ").append(p.getName()).append(" from ").append(JSON_STORE);
                    }
                    JOptionPane.showMessageDialog(frame, stringList.toString());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(frame, "Unable to read from file: " + JSON_STORE);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: list and shows previous players in JOptionPane
    public void startMenuViewAction() {
        startMenuPanel.getViewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (previousPlayersList.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "There were no past players attempts.");
                } else {
                    StringBuilder stringList = new StringBuilder();
                    for (Player next : previousPlayersList.getPlayerList()) {
                        stringList.append("\n").append(next.toString());
                    }
                    JOptionPane.showMessageDialog(frame, stringList.toString());
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: quits application
    public void startMenuQuitAction() {
        startMenuPanel.getQuitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Event next : EventLog.getInstance()) {
                    System.out.println(next);
                }
                frame.dispose();
            }
        });
    }

    // EFFECTS: adds start menu panel to main frame
    public void openPlayerForm() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(playerFormPanel);

        if (!playerFormListenersAdded) {
            playerFormSubmitAction();
        }

        playerFormListenersAdded = true;
    }

    // REQUIRES: yearText entered must be an integer
    // EFFECTS: sends player to next panel after submitting their information
    public void playerFormSubmitAction() {
        playerFormPanel.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameText = playerFormPanel.getNameTextField();
                int yearText = playerFormPanel.getYearTextField();

                if (yearText < MINIMUM_YEAR || yearText > CURRENT_YEAR) {
                    JOptionPane.showMessageDialog(frame,"Invalid year. The year must be 2015-2021.");
                } else {
                    player = new Player(nameText, 0, true, yearText);
                    playerFormPanel.setVisible(false);
                    openCoinTossPanel();
                }
            }
        });
    }

    // EFFECTS: adds start menu panel to main frame
    public void openCoinTossPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(coinTossPanel);
        coinTossPanel.setVisible(true);
        if (!coinTossListenersAdded) {
            tailsButtonAction();
            headsButtonAction();
        }
        coinTossListenersAdded = true;
    }

    // EFFECTS: sends player to appropriate scene depending on their game status result
    public void tailsButtonAction() {
        coinTossPanel.getTailsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coinTossPanel.tailsButtonAction();
                determineNextPanel();
            }
        });
    }

    // EFFECTS: sends player to appropriate scene depending on their game status result
    public void headsButtonAction() {
        coinTossPanel.getHeadsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coinTossPanel.headsButtonAction();
                determineNextPanel();
            }
        });
    }

    // EFFECTS: sends player to appropriate panel depending on their game status result
    public void determineNextPanel() {
        coinTossPanel.setVisible(false);
        if (coinTossPanel.getGameStatus()) {
            player.addPointToScore();
            openRockPaperScissorsPanel();
        } else {
            openLoseGamePanel();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds win game panel to frame
    public void openWinGamePanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(winGamePanel);
        winGamePanel.setVisible(true);
        if (!winGameListenersAdded) {
            winGamePanelSaveButtonAction();
            winGamePanelNoSaveButtonAction();
        }
        winGameListenersAdded = true;
    }

    // EFFECTS: saves player attempt to file if button pressed
    public void winGamePanelSaveButtonAction() {
        winGamePanel.getYesSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePlayer();
                addPlayerToHistory();
                winGamePanel.setVisible(false);
                openStartMenu();
            }
        });
    }

    // EFFECTS: sends player to start menu
    public void winGamePanelNoSaveButtonAction() {
        winGamePanel.getNoSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winGamePanel.setVisible(false);
                addPlayerToHistory();
                openStartMenu();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds lose game panel to frame
    public void openLoseGamePanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(loseGamePanel);
        player.eliminate();
        loseGamePanel.setVisible(true);
        if (!loseGameListenersAdded) {
            loseGamePanelSaveButtonAction();
            loseGamePanelNoSaveButtonAction();
        }
        loseGameListenersAdded = true;
    }

    // EFFECTS: saves player attempt to file if button pressed
    public void loseGamePanelSaveButtonAction() {
        loseGamePanel.getYesSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayerToHistory();
                savePlayer();
                loseGamePanel.setVisible(false);
                openStartMenu();
            }
        });
    }

    // EFFECTS: sends player to start menu
    public void loseGamePanelNoSaveButtonAction() {
        loseGamePanel.getNoSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loseGamePanel.setVisible(false);
                addPlayerToHistory();
                openStartMenu();
            }
        });
    }

    // EFFECTS: saves the previous players list from file with player to file
    public void savePlayer() {
        try {
            PreviousPlayers toSave = jsonReader.read();
            toSave.addPlayer(player);
            jsonWriter.open();
            jsonWriter.write(toSave);
            jsonWriter.close();
            JOptionPane.showMessageDialog(frame,"Saved " + player.getName() + " to " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame,"Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: previousPlayersList
    // EFFECTS: add player to player history
    private void addPlayerToHistory() {
        previousPlayersList.addPlayer(player);
    }

    // MODIFIES: this
    // EFFECTS: adds rock paper scissors game panel to frame
    public void openRockPaperScissorsPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(rockPaperScissorsPanel);
        rockPaperScissorsPanel.setVisible(true);

        if (!rockPaperScissorsListenersAdded) {
            rpsRockAction();
            rpsScissorsAction();
            rpsPaperAction();
        }

        rockPaperScissorsListenersAdded = true;
    }

    // EFFECTS: sends player to appropriate panel depending on their game status result
    public void rpsRockAction() {
        rockPaperScissorsPanel.getRockButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rockPaperScissorsPanel.rockButtonAction();
                determineNextFromRPS();
            }
        });
    }

    // EFFECTS: sends player to appropriate panel depending on their game status result
    public void rpsScissorsAction() {
        rockPaperScissorsPanel.getScissorsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rockPaperScissorsPanel.scissorsButtonAction();
                determineNextFromRPS();
            }
        });
    }

    // EFFECTS: sends player to appropriate panel depending on their game status result
    public void rpsPaperAction() {
        rockPaperScissorsPanel.getPaperButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rockPaperScissorsPanel.paperButtonAction();
                determineNextFromRPS();
            }
        });
    }

    // EFFECTS: sends player to appropriate panel depending on their game status result
    public void determineNextFromRPS() {
        rockPaperScissorsPanel.setVisible(false);
        if (rockPaperScissorsPanel.getGameStatus()) {
            player.addPointToScore();
            openWinGamePanel();
        } else {
            openLoseGamePanel();
        }
    }
}
