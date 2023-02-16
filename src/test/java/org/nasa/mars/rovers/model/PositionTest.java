package org.nasa.mars.rovers.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    void isEqual() {
        var position1 = new Position(1, 2);
        var position2 = new Position(1, 2);
        var position3 = new Position(8, 1);

        assertThat(position1.isEqual(position2)).isTrue();
        assertThat(position1.isEqual(position3)).isFalse();
    }

    @Test
    void isOnPlateau() {
        var position1 = new Position(1, 2);
        var position2 = new Position(10, 2);
        var plateau = new Plateau(5, 5, new ArrayList<>());

        assertThat(position1.isOnPlateau(plateau)).isTrue();
        assertThat(position2.isOnPlateau(plateau)).isFalse();
    }
}