package com.tabletennissimulator;

import javafx.scene.control.ChoiceBox;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

/**
 * Tests AppController methods
 */
public class AppControllerTest {
    private AppController appController = new AppController();

    /**
     * Test that IsBaseTwo function works correctly
     */
    @Test
    public void testIsBaseTwo(){
        // A number that isn't base two returns false
        Assert.assertEquals((boolean)appController.isBaseTwo(10), false);

        // A number that is base two returns true
        Assert.assertEquals((boolean)appController.isBaseTwo(128), true);
    }
}
