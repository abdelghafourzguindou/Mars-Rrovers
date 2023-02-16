package org.nasa.mars.rovers.exception;

public class PositionNotOnPlateauException extends RuntimeException {

	public PositionNotOnPlateauException() {
		super("Position is not on the plateau!");
	}
}
