package org.nasa.mars.rovers.model;

import org.junit.jupiter.api.Test;
import org.nasa.mars.rovers.exception.RoverNotDroppedException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.nasa.mars.rovers.model.Direction.NORTH;
import static org.nasa.mars.rovers.model.Instruction.LEFT;
import static org.nasa.mars.rovers.model.Instruction.MOVE;

class RoverTest {

    @Test
    void should_drop_rover_successfully() {
        var plateau = Plateau.of(5, 5);
        var rover = Rover.createAt(1, 2, NORTH);

        rover.drop(plateau);

        assertThat(rover).isIn(plateau.movables());
    }

    @Test
    void should_turn_left() {
        var plateau = Plateau.of(5, 5);
        var rover = Rover.createAt(1, 2, NORTH);

        rover.drop(plateau);

        rover.turnLeft();

        assertThat(rover.getDirection()).isEqualTo(Direction.WEST);
    }

    @Test
    void should_turn_right() {
        var plateau = Plateau.of(5, 5);
        var rover = Rover.createAt(1, 2, NORTH);

        rover.drop(plateau);

        rover.turnRight();

        assertThat(rover.getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    void should_check_if_rover_has_position() {
        var rover = Rover.createAt(1, 2, NORTH);

        assertThat(rover.hasPosition(rover.getCoordinate())).isTrue();
    }

    @Test
    void should_process_instructions() {
        var plateau = Plateau.of(5, 5);
        var rover = Rover.createAt(1, 2, NORTH);
        var instructions = List.of(LEFT, MOVE, LEFT, MOVE, LEFT, MOVE, LEFT, MOVE, MOVE);
        rover.drop(plateau);
        rover.process(instructions);

        assertThat(rover.getCoordinate().x()).isEqualTo(1);
        assertThat(rover.getCoordinate().y()).isEqualTo(3);
        assertThat(rover.getDirection()).isEqualTo(NORTH);
    }

    @Test
    void should_throw_RoverNotDroppedException() {
        var rover = Rover.createAt(1, 2, NORTH);
        var instructions = List.of(MOVE);

        assertThatExceptionOfType(RoverNotDroppedException.class).isThrownBy(() -> rover.process(instructions));
    }
}