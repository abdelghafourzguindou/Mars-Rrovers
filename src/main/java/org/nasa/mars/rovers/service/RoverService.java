package org.nasa.mars.rovers.service;

import org.nasa.mars.rovers.model.Direction;
import org.nasa.mars.rovers.model.Plateau;
import org.nasa.mars.rovers.model.Coordinate;
import org.nasa.mars.rovers.model.Rover;
import org.springframework.stereotype.Service;

import static org.nasa.mars.rovers.utils.Utils.delimiter;

@Service
public record RoverService() {

    public Rover createRover(String infos, Plateau plateau) {
        var roverInfos = infos.toUpperCase().split(delimiter);

        var x = Integer.parseInt(roverInfos[0]);
        var y = Integer.parseInt(roverInfos[1]);
        var direction = Direction.map(roverInfos[2]);

        var rover = new Rover(new Coordinate(x, y), direction, plateau);
        rover.drop();

        return rover;
    }
}
