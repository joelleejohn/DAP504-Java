package com.tabletennissimulator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class representing a Table Tennis Player
 */
public final class TableTennisPlayer extends Player {

    @Expose
    @SerializedName("serve_power")
    private int servePower;

    @Expose
    @SerializedName("serve_skill")
    private int serveSkill;

    @Expose
    private int spin;

    @Expose
    @SerializedName("forehand_power")
    private int forehandPower;

    @Expose
    @SerializedName("backhand_power")
    private int backhandPower;

    @Expose
    private int fitness;

    @Expose
    private int age;

    /**
     * Gets the ID of this player
     * @return The player's ID
     */
    public int getId(){ return id; }

    /**
     * Gets the player's server power
     * @return
     */
    public int getServePower() {
        return servePower;
    }

    /**
     * Gets the player's server skill
     * @return
     */
    public int getServeSkill() {
        return serveSkill;
    }

    /**
     * Gets the player's ability to add spin to a shot
     * @return
     */
    public int getSpin() {
        return spin;
    }

    /**
     * Gets the player's forehand power
     * @return
     */
    public int getForehandPower() {
        return forehandPower;
    }

    /**
     * Gets the player's backhand power
     * @return
     */
    public int getBackhandPower() {
        return backhandPower;
    }

    /**
     * Gets the player's fitness level, affects ability to stay effective throughout longer games
     * @return The player's fitness level
     */
    public int getFitness() {
        return fitness;
    }

    /**
     * Gets the player's age
     * @return The player's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the player's overall rating
     */
    public double getOverall() { return ((double)(fitness + spin + backhandPower + forehandPower) /  40d) * 100d; }


    /**
     * A method providing formatted information about the player.
     * Used to display information to the front-end
     * @return formatted information about the player
     */
    @Override
    public String getPlayerInformation() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        // Round the overall to 2nd decimal place
        return String.format("%s %s (OVR: %s)", firstName, lastName, df.format(getOverall()));
    }

    /**
     * Get the quality of a shot
     * @return The quality of a shot
     */
    public double getHitQuality(int bestOf, int gameNumber, int hits){
        double hitRating = getHitRating();
        double fitnessPenalty = getFitnessPenalty(3, 1, 2);
        return hitRating - (hitRating * fitnessPenalty);
    }

    /**
     * Gets the player's hit rating for a shot
     * @return The player's hit rating
     */
    private double getHitRating(){

        int handBasedPower;

        // Use a random double to compare to 60%. If the random double is greater than 60%, use the weaker
        // shot type; else use the better shot value.
        double randDouble = ThreadLocalRandom.current().nextDouble();
        boolean dominantHandShot = randDouble > (double)(3 / 5);

        // Get the value for the shot type the player is going to attempt.
        if (dominantHandShot){
            handBasedPower = forehandPower > backhandPower ? forehandPower : backhandPower;
        }
        else {
            handBasedPower = forehandPower > backhandPower ? backhandPower : forehandPower;
        }

        return ((double)(handBasedPower + spin) / 20);
    }

    /**
     * Gets the rating for a player's serve
     * @return the player's serve rating
     */
    private double getServeRating(){ return (double)(servePower + serveSkill + spin) / 30; }

    /**
     * Gets the fitness penalty for a hit to be made
     * @param bestOf Best of 'n' games for the tournament
     * @param gameNumber The number of games played for the round between two players
     * @param numOfHits Total number of hits the player has made during the rally
     * @return The fitness penalty to apply to a hit
     */
    private double getFitnessPenalty(int bestOf, int gameNumber, int numOfHits){
        double gamePenalty      = (bestOf * 5) - (gameNumber * 5);
        double numOfHitsPenalty = numOfHits > 10 ? 0 : 10 - numOfHits;
        double basePenalty      = 10;
        double totalPenalty     = gamePenalty + numOfHitsPenalty + basePenalty;

        return ((10 - fitness) / totalPenalty);
    }
}
