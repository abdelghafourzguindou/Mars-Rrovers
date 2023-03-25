package org.nasa.mars.rovers.exception;

public class CoordinateNotOnPlateauException extends RuntimeException {

	public CoordinateNotOnPlateauException() {
		super("Coordinate is not on the plateau!");
	}
}
