package org.nasa.mars.rovers.service;

import org.nasa.mars.rovers.model.Worker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record SimulationRunner() implements Simulation {

    @Override
    public List<Worker> create(SimulationParser parser) {
        var plateau = parser.parsePlateau();
        var movables = parser.parseMovables();
        var instructions = parser.parseInstructions();
        drop(movables, plateau);

        return parser.createWorkers(movables, instructions);
    }
}
