package org.nasa.mars.rovers.exception;

public class UnknownDirectionException extends SimulationException {

	public UnknownDirectionException(String unknownDirection) {
		super("Unknown heading '" + unknownDirection + "'!");
	}
}
