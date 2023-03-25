package org.nasa.mars.rovers.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import static org.assertj.core.api.Assertions.*;
import static org.nasa.mars.rovers.model.Instruction.LEFT;
import static org.nasa.mars.rovers.model.Instruction.MOVE;

class RoverTest {

    @Test
    void drop() {
        var coordinate = new Coordinate(1, 2);
        var plateau = new Plateau(5, 5, new ConcurrentLinkedDeque<>());
        var rover = new Rover(coordinate, Direction.NORTH, plateau);

        rover.drop();

        assertThat(rover).isIn(plateau.rovers());
    }

    @Test
    void hasPosition() {
        var coordinate = new Coordinate(1, 2);
        var plateau = new Plateau(5, 5, new ConcurrentLinkedDeque<>());
        var rover = new Rover(coordinate, Direction.NORTH, plateau);

        assertThat(rover.hasPosition(coordinate)).isTrue();
    }

    @Test
    void processInstructions() {
        var coordinate = new Coordinate(1, 2);
        var plateau = new Plateau(5, 5, new ConcurrentLinkedDeque<>());
        var rover = new Rover(coordinate, Direction.NORTH, plateau);

        rover.drop();
        rover.process(List.of(LEFT, MOVE, LEFT, MOVE, LEFT, MOVE, LEFT, MOVE, MOVE));

        assertThat(rover.getCoordinate().x()).isEqualTo(1);
        assertThat(rover.getCoordinate().y()).isEqualTo(3);
        assertThat(rover.getDirection()).isEqualTo(Direction.NORTH);
    }
}