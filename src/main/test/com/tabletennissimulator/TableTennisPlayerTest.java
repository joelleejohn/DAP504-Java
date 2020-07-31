package com.tabletennissimulator;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

/**
 * Class for testing TableTennisPlayer methods
 */
public class TableTennisPlayerTest {

    private TableTennisPlayer player = App.getPlayers().get(83);

    /**
     * Allows the player to be assigned without a Try/Catch
     * @throws FileNotFoundException
     */
    public TableTennisPlayerTest() throws FileNotFoundException {}

    /**
     * Tests the getOverall method calculates the correct value
     */
    @Test
    public void testGetOverall(){
        Assert.assertEquals(player.getOverall(), 85d);
    }

    /**
     * Tests the getHitRating method calculates the correct value
     */
    @Test
    public void testHitRating(){
        Assert.assertEquals(player.getHitQuality(3, 1, 1), 0.7913793103448276);
        Assert.assertEquals(player.getHitQuality(3, 1, 10), 0.765);
    }

    /**
     * Tests the getHitRating method calculates the correct value
     */
    @Test
    public void testGetPlayerInformation(){
        Assert.assertEquals(player.getPlayerInformation(), "Arturo Ailsun (OVR: 85)");
    }
}
