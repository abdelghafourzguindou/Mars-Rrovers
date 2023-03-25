package org.nasa.mars.rovers.exception;

public class UnknownInstructionException extends RuntimeException {

	public UnknownInstructionException(String unknownInstruction) {
		super("Unknown instruction '" + unknownInstruction + "'!");
	}
}
