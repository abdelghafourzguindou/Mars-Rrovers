package org.nasa.mars.rovers.model;

import org.junit.jupiter.api.Test;
import org.nasa.mars.rovers.exception.CoordinateOccupiedException;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PlateauTest {

    @Test
    void addRover() {
        var coordinate = new Coordinate(1, 2);
        var plateau = new Plateau(5, 5, new ArrayList<>());
        var rover1 = new Rover(coordinate, Direction.NORTH, plateau);
        var rover2 = new Rover(coordinate, Direction.NORTH, plateau);

        plateau.drop(rover1).drop(rover2);

        assertThat(rover1).isIn(plateau.rovers());
        assertThat(rover2).isIn(plateau.rovers());
    }

    @Test
    void isOccupied() {
        var coordinate = new Coordinate(1, 2);
        var plateau = new Plateau(5, 5, new ArrayList<>());
        var rover1 = new Rover(coordinate, Direction.NORTH, plateau);
        var rover2 = new Rover(coordinate, Direction.NORTH, plateau);

        assertThatCode(() -> plateau.checkCoordinate(rover1.getCoordinate())).doesNotThrowAnyException();
        rover1.drop();
        assertThatExceptionOfType(CoordinateOccupiedException.class).isThrownBy(() -> plateau.checkCoordinate(rover2.getCoordinate()));
    }
}