package org.nasa.mars.rovers.service;

import org.junit.jupiter.api.Test;
import org.nasa.mars.rovers.exception.PositionNotOnPlateauException;
import org.nasa.mars.rovers.exception.UnknownHeadingException;
import org.nasa.mars.rovers.exception.UnknownInstructionException;
import org.nasa.mars.rovers.model.Plateau;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.nasa.mars.rovers.model.Heading.NORTH;

class RoverServiceTest {

    private final RoverService roverService = new RoverService();

    @Test
    void createRover() {
        var plateau = new Plateau(5, 5, new ArrayList<>());
        var rover = roverService.createRover("1 2 N", plateau);

        assertThat(rover.getPosition().x()).isEqualTo(1);
        assertThat(rover.getPosition().y()).isEqualTo(2);
        assertThat(rover.getHeading()).isEqualTo(NORTH);
        assertThat(rover).isIn(plateau.rovers());
    }

    @Test
    void createRoverNotWorking() {
        var plateau = new Plateau(5, 5, new ArrayList<>());
        assertThatExceptionOfType(UnknownHeadingException.class)
                .isThrownBy(() -> roverService.createRover("1 2 F", plateau));
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> roverService.createRover("S 2 F", plateau));
    }

    @Test
    void processInstruction() {
        var plateau = new Plateau(5, 5, new ArrayList<>());
        var rover = roverService.createRover("1 2 N", plateau);

        roverService.processInstruction(rover, "LMLMLMLMM");

        assertThat(rover.getPosition().x()).isEqualTo(1);
        assertThat(rover.getPosition().y()).isEqualTo(3);
        assertThat(rover.getHeading()).isEqualTo(NORTH);
    }

    @Test
    void processInstructionNotWorking() {
        var plateau = new Plateau(5, 5, new ArrayList<>());
        var rover = roverService.createRover("1 2 N", plateau);

        assertThatExceptionOfType(UnknownInstructionException.class)
                .isThrownBy(() -> roverService.processInstruction(rover, "LMJYK"));
        assertThatExceptionOfType(PositionNotOnPlateauException.class)
                .isThrownBy(() -> roverService.processInstruction(rover, "LMMMMMMMMM"));
    }
}