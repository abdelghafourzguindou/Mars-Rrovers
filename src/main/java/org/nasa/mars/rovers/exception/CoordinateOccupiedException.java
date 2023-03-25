package org.nasa.mars.rovers.exception;

public class CoordinateOccupiedException extends RuntimeException {

    public CoordinateOccupiedException() {
        super("Already occupied by a rover!");
    }
}
