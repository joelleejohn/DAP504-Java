package com.tabletennissimulator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MatchController extends Pane implements Playable {

    public Match matchState;

    @FXML
    public GridPane match;

    @FXML
    public Label upperPlayerInfo;

    @FXML
    public Label lowerPlayerInfo;

    @FXML
    public Label upperGamesWon;

    @FXML
    public Label lowerGamesWon;

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


    @Override
    public Object play() throws InterruptedException {
        matchState.play();
        return null;
    }
}
