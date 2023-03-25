package org.nasa.mars.rovers.model;

import org.junit.jupiter.api.Test;
import org.nasa.mars.rovers.exception.UnknownInstructionException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.nasa.mars.rovers.model.Instruction.LEFT;
import static org.nasa.mars.rovers.model.Instruction.MOVE;
import static org.nasa.mars.rovers.model.Instruction.RIGHT;

class InstructionTest {

    @Test
    void should_map_successfully() {
        assertThat(Instruction.toList("MLR")).isEqualTo(List.of(MOVE, LEFT, RIGHT));
    }

    @Test
    void should_throw_UnknownInstructionException() {
        assertThatExceptionOfType(UnknownInstructionException.class).isThrownBy(() -> Instruction.toList("KPO"));
    }
}