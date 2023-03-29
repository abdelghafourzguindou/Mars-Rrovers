package org.nasa.mars.rovers.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.nasa.mars.rovers.model.Direction;
import org.nasa.mars.rovers.model.Instruction;
import org.nasa.mars.rovers.model.Movable;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.nasa.mars.rovers.model.Direction.NORTH;
import static org.nasa.mars.rovers.model.Instruction.LEFT;
import static org.nasa.mars.rovers.model.Instruction.MOVE;

class SimulationRunnerTest {

    @Test
    void should_create_simulation() {
        SimulationRunner simulationRunner = new SimulationRunner();
        var workers = simulationRunner.create(new SimulationParser("5 5\n1 2 N\nLM"::lines));

        var movable = Objects.requireNonNull(workers).get(0).movable();
        var instructions = Objects.requireNonNull(workers).get(0).instructions();

        assertAll(
                () -> assertThat(workers).isNotEmpty(),
                () -> assertThat(movable.getCoordinate().x()).isEqualTo(1),
                () -> assertThat(movable.getCoordinate().y()).isEqualTo(2),
                () -> assertThat(movable.getDirection()).isEqualTo(NORTH),
                () -> assertThat(movable.getPlateau().height()).isEqualTo(5),
                () -> assertThat(movable.getPlateau().width()).isEqualTo(5),
                () -> assertThat(instructions).hasSameElementsAs(List.of(LEFT, MOVE))
        );
    }

    @Test
    void should_run_simulation() {
        SimulationRunner simulationRunner = new SimulationRunner();
        var workers = simulationRunner.create(new SimulationParser("5 5\n1 2 N\nLM"::lines));
        var movables = simulationRunner.run(workers);

        assertThat(movables).isNotEmpty();
    }

}