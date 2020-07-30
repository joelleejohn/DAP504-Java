package com.tabletennissimulator;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class representing the Player's state throughout a match-up
 */
public class TableTennisPlayerState {

    /**
     * Creates a new player state instance
     * @param statePlayer the player who this state belongs to
     */
    public TableTennisPlayerState(TableTennisPlayer statePlayer){
        this.player = statePlayer;
        Platform.runLater(()->playerInfo.setValue(player.getPlayerInformation()));
    }

    /**
     * Indicates that this player has won this match
     */
    private Boolean isWinner = false;

    /**
     * The player that this state belongs to
     */
    private TableTennisPlayer player;

    /**
     * The number of games the player has won in a match
     */
    private int gamesWon = 0;

    /**
     * The number of hits a player has made within a rally
     */
    private int hits = 0;

    /**
     * The player's score through the match
     */
    private int score = 0;

    /**
     * String to bind to the label displaying information
     */
    StringProperty gameInfo = new SimpleStringProperty();

    /**
     * String to bind to the label displaying information
     */
    StringProperty playerInfo = new SimpleStringProperty();

    /**
     * Updates the player information for the GUI
     */
    public void updatePlayerInfo(){
        Platform.runLater(() -> gameInfo.setValue(gamesWon + " (" + score + ")"));
    }

    /**
     * Gets the player that this state object is associated with
     * @return The associated player
     */
    public TableTennisPlayer getPlayer() {
        return player;
    }

    /**
     * Gets the amount of games that this player has won
     * @return The amount of games that the player has won
     */
    public int getGamesWon(){
        return gamesWon;
    }

    /**
     * Gets the flag indicating the player has won the match-up
     * @return A flag indicating that the player has won
     */
    public boolean getIsWinner(){ return isWinner; }

    /**
     * Increments the tally of the games that the player has won in a matchup.
     */
    public void incrementGamesWon() {

        gamesWon++;

        // set isWinner if this player has won two games
        if (gamesWon == 2) {
            isWinner = true;
        }

        updatePlayerInfo();
    }

    /**
     * Increments the tally of hits a player has made during a game
     */
    public void incrementHits() { hits++; updatePlayerInfo();}

    /**
     * Increments the tally of this player's score through a game
     */
    public void incrementScore() { score++; updatePlayerInfo(); }

    /**
     * Get the player's score for a game
     * @return The player's score for the game
     */
    public int getScore() { return score; }

    /**
     * Resets the number of hits a player has made in a game
     */
    public void resetHits() { hits = 0; }

    /**
     * Resets the player's score tally for a game
     */
    public void resetScore() {
        score = 0;
        updatePlayerInfo();
    }

    /**
     * Get the player's hit quality for a shot
     * @param gamesPlayed total games played thus far
     * @return The hit quality
     */
    public double getHitQuality(int gamesPlayed){
        return player.getHitQuality(3, gamesPlayed, hits);
    }
}
