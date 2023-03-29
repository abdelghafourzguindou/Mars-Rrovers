package org.nasa.mars.rovers.exception;

public class RoverNotDroppedException extends SimulationException {

	public RoverNotDroppedException() {
		super("Rover was not dropped on the plateau!");
	}
}
