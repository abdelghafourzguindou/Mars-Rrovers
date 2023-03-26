package org.nasa.mars.rovers.service;

import org.nasa.mars.rovers.model.Direction;
import org.nasa.mars.rovers.model.Rover;
import org.springframework.stereotype.Service;

import static org.nasa.mars.rovers.utils.CommonUtil.delimiter;

@Service
public record MovableService() {

    public Rover createRover(String infos) {
        var roverInfos = infos.toUpperCase().split(delimiter);

        var x = Integer.parseInt(roverInfos[0]);
        var y = Integer.parseInt(roverInfos[1]);
        var direction = Direction.map(roverInfos[2]);

        return Rover.createAt(x, y, direction);
    }
}
