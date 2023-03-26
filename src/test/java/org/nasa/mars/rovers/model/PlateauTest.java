package org.nasa.mars.rovers.model;

import org.junit.jupiter.api.Test;
import org.nasa.mars.rovers.exception.CoordinateNotOnPlateauException;
import org.nasa.mars.rovers.exception.CoordinateOccupiedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PlateauTest {

    @Test
    void should_create_plateau_with_width_and_height() {
        var dim = 5;
        var plateau = Plateau.of(dim, dim);

        assertThat(plateau.width()).isEqualTo(dim);
        assertThat(plateau.height()).isEqualTo(dim);
        assertThat(plateau.movables()).isEmpty();
    }

    @Test
    void should_drop_movable() {
        var coordinate = new Coordinate(1, 2);
        var plateau = Plateau.of(5, 5);
        var rover1 = new Rover(coordinate, Direction.NORTH, plateau);
        var rover2 = new Rover(coordinate, Direction.NORTH, plateau);

        plateau.drop(rover1).drop(rover2);

        assertThat(rover1).isIn(plateau.movables());
        assertThat(rover2).isIn(plateau.movables());
    }

    @Test
    void checkCoordinate_should_throw_CoordinateOccupiedException() {
        var coordinate = new Coordinate(1, 2);
        var plateau = Plateau.of(5, 5);

        new Rover(coordinate, Direction.NORTH, plateau).drop();

        assertThatExceptionOfType(CoordinateOccupiedException.class).isThrownBy(() -> plateau.checkCoordinate(coordinate));
    }

    @Test
    void checkCoordinate_should_throw_CoordinateNotOnPlateauException() {
        var coordinate = new Coordinate(6, 6);
        var plateau = Plateau.of(5, 5);

        assertThatExceptionOfType(CoordinateNotOnPlateauException.class).isThrownBy(() -> plateau.checkCoordinate(coordinate));
    }
}