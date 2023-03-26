package org.nasa.mars.rovers.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nasa.mars.rovers.model.Direction.NORTH;
import static org.nasa.mars.rovers.model.Instruction.LEFT;
import static org.nasa.mars.rovers.model.Instruction.MOVE;

class WorkerTest {

    @Test
    void should_start_worker_and_process() {
        var plateau = Plateau.of(5, 5);
        var rover = Rover.createAt(1, 2, NORTH);
        var instructions = List.of(LEFT, MOVE, LEFT, MOVE, LEFT, MOVE, LEFT, MOVE, MOVE);
        var worker = new Worker(rover, instructions);

        rover.drop(plateau);
        worker.start();

        assertThat(rover.getCoordinate().x()).isEqualTo(1);
        assertThat(rover.getCoordinate().x()).isEqualTo(1);
        assertThat(rover.getDirection()).isEqualTo(NORTH);
    }
}