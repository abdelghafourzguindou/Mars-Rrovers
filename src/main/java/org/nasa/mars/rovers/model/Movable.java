package org.nasa.mars.rovers.model;

import org.nasa.mars.rovers.exception.RoverNotDroppedException;

import java.util.List;

public interface Movable {
    void drop();
    void turnLeft();
    void turnRight();
    void move();
    Coordinate getCoordinate();
    Direction getDirection();
    String printInfo();

    default Movable process(List<Instruction> instructions) {
        if (this.getCoordinate() == null || this.getDirection() == null) {
            throw new RoverNotDroppedException();
        }
        instructions.forEach(this::execute);
        return this;
    }

    private void execute(Instruction instruction) {
        switch (instruction) {
            case LEFT -> this.turnLeft();
            case RIGHT -> this.turnRight();
            case MOVE -> this.move();
        }
    }
}
