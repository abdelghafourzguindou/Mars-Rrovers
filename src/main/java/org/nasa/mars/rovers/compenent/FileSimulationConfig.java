package org.nasa.mars.rovers.compenent;

import org.nasa.mars.rovers.service.SimulationFileParser;
import org.nasa.mars.rovers.service.SimulationFileReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FileSimulationConfig {

    @Value("${simulation.file.name}")
    private String file;

    @Bean
    public SimulationFileReader simulationFileReader() {
        return new SimulationFileReader(file);
    }

    @Bean
    public SimulationFileParser simulationFileParser(SimulationFileReader SimulationFileReader) {
        return new SimulationFileParser(SimulationFileReader);
    }
}
