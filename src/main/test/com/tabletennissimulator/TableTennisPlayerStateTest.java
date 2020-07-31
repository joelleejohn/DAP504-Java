package com.tabletennissimulator;

import java.io.FileNotFoundException;

/**
 * Tests the TableTennisPlayerState methods
 */
public class TableTennisPlayerStateTest {

    private TableTennisPlayerState player = null;

    /**
     * Allows the player state to be assigned without a Try/Catch
     * @throws FileNotFoundException
     */
    public TableTennisPlayerStateTest() throws FileNotFoundException {
        player = new TableTennisPlayerState(App.getPlayers().get(83));
    }
}
