package org.nasa.mars.rovers.exception;

public class UnknownHeadingException extends RuntimeException {

	public UnknownHeadingException(char heading) {
		super("Unknown heading '" + heading + "'!");
	}
}
