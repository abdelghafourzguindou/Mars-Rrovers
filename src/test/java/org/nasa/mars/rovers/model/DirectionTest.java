package org.nasa.mars.rovers.model;

import org.junit.jupiter.api.Test;
import org.nasa.mars.rovers.exception.UnknownDirectionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class DirectionTest {

    @Test
    void should_map_successfully() {
        assertThat(Direction.map("E")).isEqualTo(Direction.EAST);
    }

    @Test
    void should_throw_UnknownDirectionException() {
        assertThatExceptionOfType(UnknownDirectionException.class).isThrownBy(() -> Direction.map("YQ"));
    }
}