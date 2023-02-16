package org.nasa.mars.rovers.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

class PlateauServiceTest {

    private final PlateauService plateauService = new PlateauService();

    @Test
    void createPlateau() {
        var plateau = plateauService.createPlateau("5 5");
        assertThat(plateau.dimX()).isEqualTo(5);
        assertThat(plateau.dimY()).isEqualTo(5);
    }

    @Test
    void createPlateauNotWorking() {
        assertThatException().isThrownBy(() -> plateauService.createPlateau("Q 5"));
    }
}