package org.nasa.mars.rovers.exception;

public class CoordinateNotOnPlateauException extends SimulationException {

	public CoordinateNotOnPlateauException() {
		super("Coordinate is not on the plateau!");
	}
}
