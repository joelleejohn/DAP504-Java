package com.tabletennissimulator;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class providing Round interaction.
 */
public class Round implements Playable {

    private HBox controller;

    /**
     * Tracks which round this is
     */
    public int roundNumber;

    /**
     * Matches in this round with a game ID so the next round is ordered correctly.
     */
    private HashMap<Integer, Match> matches = new HashMap<>();

    /**
     * Creates a round instance and assigns the matches to a custom MatchController Component and updates the UI accordingly
     * @param playerPairs
     * @param container
     */
    public Round(HashMap<Integer, ArrayList<TableTennisPlayer>> playerPairs, HBox container){

        for (int i = 1; i <= playerPairs.size() ; i++) {
            ArrayList<TableTennisPlayer> pair = playerPairs.get(i);
            matches.put(i, new Match(pair.get(0), pair.get(1)));
        }
        Platform.runLater(() ->{

            ScrollPane scrollPane = new ScrollPane();
            // add a VBox for storing matches
            VBox box = new VBox();
            box.setSpacing(10);

            // ensure we're ordering the matches in the right order
            ArrayList<Integer> roundIds = new ArrayList<>(matches.keySet());
            ArrayList<MatchController> mc = new ArrayList<>();
            for (Integer key: roundIds) {
                Match matchToAdd = matches.get(key);

                // for each match, add a custom MatchController item.
                mc.add(new MatchController(matchToAdd));
            }
            box.setPrefWidth(265);
            box.setAlignment(Pos.CENTER);
            box.getChildren().addAll(mc);
            scrollPane.setContent(box);
            container.getChildren().add(scrollPane);
        });
    }

    /**
     * plays the round
     * @return A hashset of matched players
     */
    @Override
    public HashMap<Integer, ArrayList<TableTennisPlayer>> play() {

        HashMap<Integer, ArrayList<TableTennisPlayer>> pairs = new HashMap<>();

        int newIndex = 1;
        for (int iterator = 1; iterator <= matches.size(); iterator++) {
            var match = matches.get(iterator);
            try {
                TableTennisPlayer winner = match.play();

                // If the number is even, add the winner to the previous pair
                // Else this is the first player in the pair and we need a new array
                if (iterator % 2 == 0){
                    System.out.println("Added winner at " + (iterator - 1));
                    pairs.get(newIndex).add(winner);
                    newIndex++;
                } else {
                    ArrayList<TableTennisPlayer> pair = new ArrayList<>();
                    System.out.println("Added winner at " + iterator);
                    pair.add(winner);
                    pairs.put(newIndex, pair);
                }

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        return pairs;
    }
}
