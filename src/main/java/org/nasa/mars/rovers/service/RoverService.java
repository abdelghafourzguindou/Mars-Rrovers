package org.nasa.mars.rovers.service;

import lombok.RequiredArgsConstructor;
import org.nasa.mars.rovers.model.Direction;
import org.nasa.mars.rovers.model.Instruction;
import org.nasa.mars.rovers.model.Plateau;
import org.nasa.mars.rovers.model.Coordinate;
import org.nasa.mars.rovers.model.Rover;
import org.springframework.stereotype.Service;

import static org.nasa.mars.rovers.service.Utils.delimiter;

@Service
public record RoverService() {

    public Rover createRover(String infos, Plateau plateau) {
        var roverInfos = infos.toUpperCase().split(delimiter);
        var x = Integer.parseInt(roverInfos[0]);
        var y = Integer.parseInt(roverInfos[1]);
        var heading = Direction.map(roverInfos[2]);
        var rover = new Rover(new Coordinate(x, y), heading, plateau);
        rover.drop();
        return rover;
    }

    public void processInstruction(Rover rover, String infos) {
        var instructions = infos.toUpperCase();
        rover.processInstructions(Instruction.toList(instructions));
    }
}
