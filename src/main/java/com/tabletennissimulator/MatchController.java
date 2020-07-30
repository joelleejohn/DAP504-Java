package com.tabletennissimulator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * A custom FXML component that displays formatted information about a match
 */
public class MatchController extends Pane {

    /**
     * The match that this component is bound to
     */
    public Match matchState;

    /**
     * The Grid Pane of the MatchController component in which all major components reside
     */
    @FXML
    public GridPane match;

    /**
     * The Label of the MatchController component that provides information about the upper bracket player
     */
    @FXML
    public Label upperPlayerInfo;

    /**
     * The Label of the MatchController component that provides information about the lower bracket player
     */
    @FXML
    public Label lowerPlayerInfo;

    /**
     * The Label of the MatchController component that provides information about the upper bracket player's game score
     */
    @FXML
    public Label upperGamesWon;

    /**
     * The Label of the MatchController component that provides information about the lower bracket player's game score
     */
    @FXML
    public Label lowerGamesWon;

    /**
     * Creates a new instance that can be loaded onto the UI
     * @param newMatch The match that the control will be bound to
     */
    public MatchController(Match newMatch) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/Match.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        matchState = newMatch;

        upperPlayerInfo.textProperty().bind(matchState.upperPlayerInfo);
        lowerPlayerInfo.textProperty().bind(matchState.lowerPlayerInfo);
        upperGamesWon.textProperty().bind(matchState.upperPlayerState.gameInfo);
        lowerGamesWon.textProperty().bind(matchState.lowerPlayerState.gameInfo);
    }
}
