package org.nasa.mars.rovers.model;

import org.junit.jupiter.api.Test;
import org.nasa.mars.rovers.exception.CoordinateNotOnPlateauException;
import org.nasa.mars.rovers.exception.CoordinateOccupiedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.nasa.mars.rovers.model.Direction.EAST;
import static org.nasa.mars.rovers.model.Direction.NORTH;

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
        var plateau = Plateau.of(5, 5);
        var rover1 = Rover.createAt(1, 2, NORTH);
        var rover2 = Rover.createAt(1, 5, EAST);

        rover1.drop(plateau);
        rover2.drop(plateau);

        assertThat(rover1).isIn(plateau.movables());
        assertThat(rover2).isIn(plateau.movables());
    }

    @Test
    void checkCoordinate_should_throw_CoordinateOccupiedException() {
        var plateau = Plateau.of(5, 5);

        var rover = Rover.createAt(1, 2, NORTH);
        rover.drop(plateau);

        assertThatExceptionOfType(CoordinateOccupiedException.class).isThrownBy(() -> plateau.checkCoordinate(rover.getCoordinate()));
    }

    @Test
    void checkCoordinate_should_throw_CoordinateNotOnPlateauException() {
        var coordinate = new Coordinate(6, 6);
        var plateau = Plateau.of(5, 5);

        assertThatExceptionOfType(CoordinateNotOnPlateauException.class).isThrownBy(() -> plateau.checkCoordinate(coordinate));
    }
}