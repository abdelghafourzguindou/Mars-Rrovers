package org.nasa.mars.rovers.service;

import org.junit.jupiter.api.Test;
import org.nasa.mars.rovers.exception.UnknownDirectionException;
import org.nasa.mars.rovers.model.Plateau;

import java.util.concurrent.ConcurrentLinkedDeque;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.nasa.mars.rovers.model.Direction.NORTH;

class RoverServiceTest {

    private final RoverService roverService = new RoverService();

    @Test
    void createRover() {
        var plateau = new Plateau(5, 5, new ConcurrentLinkedDeque<>());
        var rover = roverService.createRover("1 2 N", plateau);

        assertThat(rover.getCoordinate().x()).isEqualTo(1);
        assertThat(rover.getCoordinate().y()).isEqualTo(2);
        assertThat(rover.getDirection()).isEqualTo(NORTH);
        assertThat(rover).isIn(plateau.movables());
    }

    @Test
    void createRoverNotWorking() {
        var plateau = new Plateau(5, 5, new ConcurrentLinkedDeque<>());
        assertThatExceptionOfType(UnknownDirectionException.class)
                .isThrownBy(() -> roverService.createRover("1 2 F", plateau));
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> roverService.createRover("S 2 F", plateau));
    }
}