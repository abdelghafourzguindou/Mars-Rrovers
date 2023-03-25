package org.nasa.mars.rovers.exception;

public class UnknownDirectionException extends RuntimeException {

	public UnknownDirectionException(String unknownDirection) {
		super("Unknown heading '" + unknownDirection + "'!");
	}
}
