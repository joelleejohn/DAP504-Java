package com.tabletennissimulator;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a match played between two players
 */
public class Match implements Playable {

    private int gamesPlayed;

    /**
     * Creates a new instance of the a Match object
     * @param upperBracketPlayer the player higher in the bracket
     * @param lowerBracketPlayer the player lower in the bracket
     */
    public Match(TableTennisPlayer upperBracketPlayer, TableTennisPlayer lowerBracketPlayer){
        upperPlayerState = new TableTennisPlayerState(upperBracketPlayer);
        lowerPlayerState = new TableTennisPlayerState(lowerBracketPlayer);
        Platform.runLater(() ->upperPlayerInfo.setValue(upperBracketPlayer.getPlayerInformation()));
        Platform.runLater(() ->lowerPlayerInfo.setValue(lowerBracketPlayer.getPlayerInformation()));
    }

    /**
     * The upper bracket player state
     */
    public TableTennisPlayerState upperPlayerState;

    /**
     * The lower bracket player state
     */
    public TableTennisPlayerState lowerPlayerState;

    /**
     * The player information for the upper bracket player
     */
    public StringProperty upperPlayerInfo = new SimpleStringProperty();

    /**
     * The player information for the upper bracket player
     */
    public StringProperty lowerPlayerInfo = new SimpleStringProperty();

    /**
     * Plays the games for this match
     */
    public TableTennisPlayer play() throws InterruptedException {
        boolean playable = !upperPlayerState.getIsWinner() && !lowerPlayerState.getIsWinner();

        while (playable){
            rally(upperPlayerState, lowerPlayerState);

            playable = !upperPlayerState.getIsWinner() && !lowerPlayerState.getIsWinner();
        }

        // return whichever player won
        if (upperPlayerState.getIsWinner())
            return upperPlayerState.getPlayer();

        return lowerPlayerState.getPlayer();
    }

    /**
     * Performs a rally
     * @param first the player to serve
     * @param second the player to return
     * @return true if the games are over
     */
    private void rally(TableTennisPlayerState first, TableTennisPlayerState second) throws InterruptedException {
        // Weight the shot of the primary player against the second's shot quality
        // Use a random double to check if the shot is good (and therefore scores a point)
        double randDouble = ThreadLocalRandom.current().nextDouble();
        double firstHitQuality = first.getHitQuality(gamesPlayed);
        double secondHitQuality = second.getHitQuality(gamesPlayed);
        boolean goodShot = firstHitQuality / (firstHitQuality + secondHitQuality) > randDouble;

        Thread.sleep(5);

        // If this is a good shot, count it as a scored point, else play the shot for the opponent
        if (goodShot){
            System.out.println(first.getPlayer().getPlayerInformation() + " won point vs. " + second.getPlayer().getPlayerInformation() + " " + first.getGamesWon() + " - " + second.getGamesWon() + "("+ first.getScore() + " - " + second.getScore() + ")");

            first.resetHits();
            second.resetHits();
            first.incrementScore();
            first.updatePlayerInfo();

            // Check if the victory conditions have been met
            // If they have, increase the win tally. If the isWinner flag is true, return to the play method
            if (first.getScore() >= 11 && first.getScore() - second.getScore() > 2){
                first.incrementGamesWon();
                gamesPlayed++;

                System.out.println(first.getPlayer().getPlayerInformation() + " wins game vs. " + second.getPlayer().getPlayerInformation() + " " + first.getGamesWon() + " - " + second.getGamesWon() + "("+ first.getScore() + " - " + second.getScore() + ")");

                if (first.getIsWinner()) {
                    System.out.println(first.getPlayer().getPlayerInformation() + " wins match vs. " + second.getPlayer().getPlayerInformation() + " " + first.getGamesWon() + " - " + second.getGamesWon() + "("+ first.getScore() + " - " + second.getScore() + ")");
                    first.updatePlayerInfo();
                    return;
                } else {
                    first.resetScore();
                    second.resetScore();

                    // else carry on the rally.
                    rally(second, first);
                }
            }

        } else {
            System.out.println(first.getPlayer().getPlayerInformation() + " made shot vs. " + second.getPlayer().getPlayerInformation() + " " + first.getGamesWon() + " - " + second.getGamesWon() + "("+ first.getScore() + " - " + second.getScore() + ")");

            // shot didn't cause a point to be scored; continue the rally.
            first.incrementHits();
            rally(second, first);
        }
    }
}
