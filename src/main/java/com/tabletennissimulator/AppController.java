package com.tabletennissimulator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Controller for the entire application, allows control of GUI elements
 */
public class AppController {

    private int numberOfRounds = 0;

    @FXML
    public HBox roundContainer;

    @FXML
    public Button simulate;

    @FXML
    public ChoiceBox<Integer> numPlayers;

    @FXML
    public Label error;

    public ArrayList<TableTennisPlayer> players = new ArrayList<>();


    /**
     * Initialize function that the fxml loader uses to initialize all the fields
     */
    @FXML
    public void initialize() throws FileNotFoundException {

        players = App.getPlayers();

        players.sort(Comparator.comparing(TableTennisPlayer::getOverall).reversed());

        // This tournament requires the number of players to be base 2.
        // Give a warning if this constraint is violated and disable the simulate button.
        if (!isBaseTwo(players.size())){
            error.setText("WARNING! Number of players found is not base 2! Check JSON and restart application");
            error.setVisible(true);
            simulate.setDisable(true);
        }

        numPlayers.setItems(getBaseTwo());

    }

    /**
     * Tests if an integer is base 2
     * @param x The integer to tests
     * @return True if the integer passed is base 2
     */
    private Boolean isBaseTwo(int x) { return (x != 0) && ((x & (x - 1)) == 0); }

    /**
     * Gets the base 2 options for the number of players to use in the simulation
     * @return A list of base 2 options compatible with the number of players
     */
    private ObservableList<Integer> getBaseTwo() {
        int current = 2;
        int iteration = 1;
        ArrayList<Integer> numPlayersOption = new ArrayList<>();

        while (current <= players.size()) {
            numPlayersOption.add(current);
            iteration++;
            current = (int) Math.pow(2, iteration);
        }

        numberOfRounds = iteration;
        return FXCollections.observableArrayList(numPlayersOption);
    }

    public void handleSimulate(){
        Task t = new Task() {
            @Override
            protected Object call() throws Exception {
                simulate.setDisable(true);
                numPlayers.setDisable(true);
                new Tournament(players, numPlayers.getValue(), roundContainer);
                return null;
            }
        };

        new Thread(t).start();


    }
}
