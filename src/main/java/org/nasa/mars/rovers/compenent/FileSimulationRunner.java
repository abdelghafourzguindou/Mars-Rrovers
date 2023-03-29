package org.nasa.mars.rovers.compenent;

import lombok.RequiredArgsConstructor;
import org.nasa.mars.rovers.service.Simulation;
import org.nasa.mars.rovers.service.SimulationFileParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("file")
public class FileSimulationRunner implements CommandLineRunner {

    private final SimulationFileParser simulationFileParser;
    private final Simulation simulation;

    @Override
    public void run(String... args) {
        var workers = simulation.create(simulationFileParser);
        simulation.run(workers).forEach(movable -> System.out.println(movable.printInfo()));
    }
}
