package org.nasa.mars.rovers.exception;

public class RoverNotDroppedException extends RuntimeException {

	public RoverNotDroppedException() {
		super("Rover was not dropped on the plateau!");
	}
}
