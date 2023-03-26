package org.nasa.mars.rovers.model;

import java.util.List;

/**
 * Worker class is a representation of a movable processor to execute a suite of instructions
 * @param movable
 * @param instructions
 */
public record Worker(Movable movable, List<Instruction> instructions) {

    /**
     * Execute a suite of instructions into a movable
     * @return Movable or RuntimeException
     */
    public Movable start() {
        return movable.process(instructions);
    }
}
