package com.tabletennissimulator;

/**
 * An interface that provides a contract for playable objects
 */
public interface Playable {

    /**
     * Method that plays a sequence
     */
    Object play() throws InterruptedException;
}
