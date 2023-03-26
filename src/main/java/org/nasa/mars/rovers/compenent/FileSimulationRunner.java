package org.nasa.mars.rovers.compenent;

import org.nasa.mars.rovers.model.Simulation;
import org.nasa.mars.rovers.utils.filetools.SimulationFileParser;
import org.nasa.mars.rovers.utils.filetools.SimulationFileReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("file")
public class FileSimulationRunner implements Simulation, CommandLineRunner {

    @Value("${simulation.file.name}")
    private String file;

    @Override
    public void run(String... args) {
        var parser = new SimulationFileParser(new SimulationFileReader(file));
        var plateau = parser.parsePlateau();
        var movables = parser.parseMovables(plateau);
        var instructions = parser.parseInstructions();
        var workers = parser.makeWorkers(movables, instructions);

        this.run(workers).forEach(movable -> System.out.println(movable.printInfo()));
    }
}
