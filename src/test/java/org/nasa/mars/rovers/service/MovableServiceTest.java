package org.nasa.mars.rovers.service;

import org.junit.jupiter.api.Test;
import org.nasa.mars.rovers.exception.UnknownDirectionException;
import org.nasa.mars.rovers.model.Plateau;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.nasa.mars.rovers.model.Direction.NORTH;

class MovableServiceTest {

    private final MovableService roverService = new MovableService();

    @Test
    void createRover() {
        var plateau = Plateau.of(5, 5);
        var rover = roverService.createRover("1 2 N");
        rover.drop(plateau);

        assertThat(rover.getCoordinate().x()).isEqualTo(1);
        assertThat(rover.getCoordinate().y()).isEqualTo(2);
        assertThat(rover.getDirection()).isEqualTo(NORTH);
        assertThat(rover).isIn(plateau.movables());
    }

    @Test
    void createRoverNotWorking() {
        assertThatExceptionOfType(UnknownDirectionException.class)
                .isThrownBy(() -> roverService.createRover("1 2 F"));
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> roverService.createRover("S 2 F"));
    }
}