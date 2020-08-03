package com.tabletennissimulator;

/**
 * An interface that provides a contract for playable objects
 */
interface Playable {

    /**
     * Method that plays a sequence
     * @return An object that may be used by the caller
     * @throws InterruptedException When running a task in the method's implementation
     */
    public Object play() throws InterruptedException;
}
