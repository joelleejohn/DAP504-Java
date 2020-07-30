package com.tabletennissimulator;

import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tournament {

    final private HashMap<Integer, Round> roundHashMap = new HashMap<>();

    private int currentRoundIndex;

    private int numberOfPlayers;

    private HBox roundContainer;

    private ArrayList<TableTennisPlayer> players;

    private ArrayList<Match> current
    /**
     * Starts the tournament by creating the first round and playing it
     * @param players all the possible players in the tournament
     * @param numberOfPlayers the number of players for the tournament
     * @param roundContainer the container that displays round information
     */
    public Tournament(ArrayList<TableTennisPlayer> players, int numberOfPlayers, HBox roundContainer){

        // Initialize the things we're going to use repeatedly
        this.numberOfPlayers = numberOfPlayers;
        this.roundContainer = roundContainer;
        this.players = players;

        HashMap<Integer, ArrayList<TableTennisPlayer>> playerPairs = new HashMap<>();

        for (int currentIndex = 0; currentIndex < (numberOfPlayers / 2) - 1; currentIndex++) {
            int offset = players.size() - numberOfPlayers;
            int matchupIndex = (players.size() - 1) - currentIndex;

            ArrayList<TableTennisPlayer> playerPair = new ArrayList<>();
            playerPair.add(0, players.get(currentIndex));
            playerPair.add(1, players.get(matchupIndex - offset));
            playerPairs.put(currentIndex + 1, playerPair);

        }

        currentRoundIndex = 1;

        roundHashMap.put(1, new Round(playerPairs, roundContainer));
    }

    private void play(){

        // Get the current round
        Round currentRound = roundHashMap.get(currentRoundIndex);

        // Play the round
        HashMap<Integer, ArrayList<TableTennisPlayer>> playersLeft = currentRound.play();
    }

    /**
     * Add a r
     */
    private void addRound(HashMap<Integer, ArrayList<TableTennisPlayer>> playersLeft) {

        HashMap<Integer, ArrayList<TableTennisPlayer>> playerPairs = new HashMap<>();

        playersLeft.forEach((index, pair) -> {
            ArrayList<TableTennisPlayer> playerPair = new ArrayList<>();
            playerPair.add(0, pair.get(0));
            playerPair.add(1, players.get(matchupIndex - offset));
            playerPairs.put(currentIndex + 1, playerPair);
        });

        currentRoundIndex++;

        roundHashMap.put(currentRoundIndex, new Round(matches, roundContainer));
    }
}
