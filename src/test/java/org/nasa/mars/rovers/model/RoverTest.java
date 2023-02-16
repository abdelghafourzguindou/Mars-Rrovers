package org.nasa.mars.rovers.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.nasa.mars.rovers.model.Instruction.LEFT;
import static org.nasa.mars.rovers.model.Instruction.MOVE;

class RoverTest {

    @Test
    void drop() {
        var position = new Position(1, 2);
        var plateau = new Plateau(5, 5, new ArrayList<>());
        var rover = new Rover(position, Heading.NORTH, plateau);

        rover.drop();

        assertThat(rover).isIn(plateau.rovers());
    }

    @Test
    void hasPosition() {
        var position = new Position(1, 2);
        var plateau = new Plateau(5, 5, new ArrayList<>());
        var rover = new Rover(position, Heading.NORTH, plateau);

        assertThat(rover.hasPosition(position)).isTrue();
    }

    @Test
    void processInstructions() {
        var position = new Position(1, 2);
        var plateau = new Plateau(5, 5, new ArrayList<>());
        var rover = new Rover(position, Heading.NORTH, plateau);

        rover.drop();
        rover.processInstructions(List.of(LEFT, MOVE, LEFT, MOVE, LEFT, MOVE, LEFT, MOVE, MOVE));

        assertThat(rover.getPosition().x()).isEqualTo(1);
        assertThat(rover.getPosition().y()).isEqualTo(3);
        assertThat(rover.getHeading()).isEqualTo(Heading.NORTH);
    }
}