package org.nasa.mars.rovers.utils.filetools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimulationFileParserTest {

    private SimulationFileParser parser;

    @BeforeEach
    void setUp() {
        var reader = new SimulationFileReader("file.txt");
        parser = new SimulationFileParser(reader);
    }

    @Test
    void should_parse_plateau() {
        var plateau = parser.parsePlateau();

        assertThat(plateau.width()).isEqualTo(5);
        assertThat(plateau.height()).isEqualTo(5);
    }

    @Test
    void should_parse_movable() {
        var plateau = parser.parsePlateau();
        var movables = parser.parseMovables();

        assertThat(movables).isNotEmpty();
    }

    @Test
    void should_parse_instructions() {
        var instructions = parser.parseInstructions();

        assertThat(instructions).isNotEmpty();
    }

    @Test
    void should_make_workers() {
        var plateau = parser.parsePlateau();
        var movables = parser.parseMovables();
        var instructions = parser.parseInstructions();
        var workers = parser.makeWorkers(movables, instructions);

        assertThat(workers).isNotEmpty();
    }
}