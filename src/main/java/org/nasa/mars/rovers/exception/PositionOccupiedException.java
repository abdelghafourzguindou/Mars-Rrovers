package org.nasa.mars.rovers.exception;

public class PositionOccupiedException extends RuntimeException {

    public PositionOccupiedException() {
        super("Already occupied by a rover!");
    }
}
