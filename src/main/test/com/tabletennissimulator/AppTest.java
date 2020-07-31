package com.tabletennissimulator;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Tests the App class methods
 */
public class AppTest {

    /**
     * Tests the getPlayers method returns the correct amount of players
     */
    @Test
    public void testGetPlayers() {
        int numPlayersFound = 0;
        ArrayList<TableTennisPlayer> players = null;
        try {
            players = App.getPlayers();
            numPlayersFound = players.size();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // We know that there should be 128 players in the JSON file
        Assert.assertEquals(numPlayersFound, 128);

        // Players should be sorted by ID
        Assert.assertEquals(players.get(0).getId(),1);
        Assert.assertEquals(players.get(127).getId(),128);

    }
}