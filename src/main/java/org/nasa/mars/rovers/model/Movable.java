package org.nasa.mars.rovers.model;

import org.nasa.mars.rovers.exception.RoverNotDroppedException;

import java.util.List;

/**
 * To be implemented by any movable inside a plateau
 */
public interface Movable {
    /**
      * Drop operation to add a movable into the plateau
     */
    void drop();
    void turnLeft();
    void turnRight();
    void move();
    Coordinate getCoordinate();
    Direction getDirection();
    String printInfo();

    /**
     * Process a suite of instructions
     * @param instructions
     * @return Movable in the new state
     */
    default Movable process(List<Instruction> instructions) {
        if (this.getCoordinate() == null || this.getDirection() == null) {
            throw new RoverNotDroppedException();
        }
        instructions.forEach(this::execute);
        return this;
    }

    /**
     * Execute an instruction
     * @param instruction
     */
    private void execute(Instruction instruction) {
        switch (instruction) {
            case LEFT -> this.turnLeft();
            case RIGHT -> this.turnRight();
            case MOVE -> this.move();
        }
    }
}
