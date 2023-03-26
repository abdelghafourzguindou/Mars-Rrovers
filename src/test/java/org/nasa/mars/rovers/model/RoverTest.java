package org.nasa.mars.rovers.model;

import org.junit.jupiter.api.Test;
import org.nasa.mars.rovers.exception.RoverNotDroppedException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.nasa.mars.rovers.model.Instruction.LEFT;
import static org.nasa.mars.rovers.model.Instruction.MOVE;

class RoverTest {

    @Test
    void should_drop_rover_successfully() {
        var coordinate = new Coordinate(1, 2);
        var plateau = Plateau.of(5, 5);
        var rover = new Rover(coordinate, Direction.NORTH, plateau);

        rover.drop();

        assertThat(rover).isIn(plateau.movables());
    }

    @Test
    void should_turn_left() {
        var coordinate = new Coordinate(1, 2);
        var plateau = Plateau.of(5, 5);
        var rover = new Rover(coordinate, Direction.NORTH, plateau);
        rover.drop();

        rover.turnLeft();

        assertThat(rover.getDirection()).isEqualTo(Direction.WEST);
    }

    @Test
    void should_turn_right() {
        var coordinate = new Coordinate(1, 2);
        var plateau = Plateau.of(5, 5);
        var rover = new Rover(coordinate, Direction.NORTH, plateau);
        rover.drop();

        rover.turnRight();

        assertThat(rover.getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    void should_check_if_rover_has_position() {
        var coordinate = new Coordinate(1, 2);
        var plateau = Plateau.of(5, 5);
        var rover = new Rover(coordinate, Direction.NORTH, plateau);

        assertThat(rover.hasPosition(coordinate)).isTrue();
    }

    @Test
    void should_process_instructions() {
        var coordinate = new Coordinate(1, 2);
        var plateau = Plateau.of(5, 5);
        var rover = new Rover(coordinate, Direction.NORTH, plateau);
        var instructions = List.of(LEFT, MOVE, LEFT, MOVE, LEFT, MOVE, LEFT, MOVE, MOVE);
        rover.drop();
        rover.process(instructions);

        assertThat(rover.getCoordinate().x()).isEqualTo(1);
        assertThat(rover.getCoordinate().y()).isEqualTo(3);
        assertThat(rover.getDirection()).isEqualTo(Direction.NORTH);
    }

    @Test
    void should_throw_RoverNotDroppedException() {
        var plateau = Plateau.of(5, 5);
        var rover = new Rover(null, Direction.NORTH, plateau);
        var instructions = List.of(MOVE);

        assertThatExceptionOfType(RoverNotDroppedException.class).isThrownBy(() -> rover.process(instructions));
    }
}