package org.nasa.mars.rovers.exception;

public class UnknownInstructionException extends SimulationException {

	public UnknownInstructionException(String unknownInstruction) {
		super("Unknown instruction '" + unknownInstruction + "'!");
	}
}
