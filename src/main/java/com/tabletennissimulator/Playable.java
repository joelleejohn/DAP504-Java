package com.tabletennissimulator;

/**
 * An interface that provides a contract for playable objects
 */
public interface Playable {

    /**
     * Method that plays a sequence
     * @return An object that may be used by the caller
     * @throws InterruptedException When running a task in the method's implementation
     */
    Object play() throws InterruptedException;
}
