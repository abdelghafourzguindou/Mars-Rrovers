package org.nasa.mars.rovers.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class PlateauTest {

    @Test
    void addRover() {
        var position = new Position(1, 2);
        var plateau = new Plateau(5, 5, new ArrayList<>());
        var rover1 = new Rover(position, Heading.NORTH, plateau);
        var rover2 = new Rover(position, Heading.NORTH, plateau);

        plateau.addRover(rover1).addRover(rover2);

        assertThat(rover1).isIn(plateau.rovers());
        assertThat(rover2).isIn(plateau.rovers());
    }

    @Test
    void isOccupied() {
        var position = new Position(1, 2);
        var plateau = new Plateau(5, 5, new ArrayList<>());
        var rover1 = new Rover(position, Heading.NORTH, plateau);
        var rover2 = new Rover(position, Heading.NORTH, plateau);

        assertThat(plateau.isOccupied(rover1)).isFalse();
        rover1.drop();
        assertThat(plateau.isOccupied(rover2)).isTrue();
    }
}