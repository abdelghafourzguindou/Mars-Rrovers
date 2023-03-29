package org.nasa.mars.rovers.exception;

public class CoordinateOccupiedException extends SimulationException {

    public CoordinateOccupiedException() {
        super("Already occupied by a rover!");
    }
}
