package org.nasa.mars.rovers.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nasa.mars.rovers.exception.UnknownHeadingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class HeadingTest {

    @Test
    void translateGood() {
        assertThat(Heading.translate('E')).isEqualTo(Heading.EAST);
    }

    @Test
    void translateNotGood() {
        assertThatExceptionOfType(UnknownHeadingException.class).isThrownBy(() -> Heading.translate('Y'));
    }
}