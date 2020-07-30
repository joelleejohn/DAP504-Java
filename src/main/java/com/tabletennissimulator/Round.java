package com.tabletennissimulator;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ScrollPane;
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

    public Round(HashMap<Integer, ArrayList<TableTennisPlayer>> playerPairs, HBox container){

        for (int i = 1; i < playerPairs.size() ; i++) {
            ArrayList<TableTennisPlayer> pair = playerPairs.get(i);
            matches.put(i, new Match(pair.get(0), pair.get(1)));
        }

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

        box.getChildren().addAll(mc);
        scrollPane.setContent(box);
        container.getChildren().add(scrollPane);
    }

    /**
     * plays the round
     * @return A hashset of matched players
     */
    @Override
    public HashMap<Integer, ArrayList<TableTennisPlayer>> play() {

        // Prepare
        HashMap<Integer, ArrayList<TableTennisPlayer>> pairs = new HashMap<>();


        for (int iterator = 1; iterator < matches.size(); iterator++) {
            var match = matches.get(iterator);
            try {
                TableTennisPlayer winner = match.play();

                // If the number is even, add the winner to the previous pair
                // Else this is the first player in the pair and we need a new array
                if (iterator % 2 != 1){
                    pairs.get(iterator - 1).add(winner);
                } else {
                    ArrayList<TableTennisPlayer> pair = new ArrayList<>();
                    pair.add(winner);
                    pairs.put(iterator, pair);
                }

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        return pairs;
    }
}
