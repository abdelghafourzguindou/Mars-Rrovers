package org.nasa.mars.rovers.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimulationFileReaderTest {

    @Test
    void should_read_file_as_stream() {
        var reader = new SimulationFileReader("file.txt");

        assertThat(reader.getLines()).isNotNull();
    }
}