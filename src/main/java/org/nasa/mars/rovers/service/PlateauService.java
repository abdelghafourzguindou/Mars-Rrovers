package org.nasa.mars.rovers.service;

import org.nasa.mars.rovers.model.Plateau;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedDeque;

import static org.nasa.mars.rovers.utils.Utils.delimiter;

@Service
public record PlateauService() {

    public Plateau createPlateau(String infos) {
        var dimOfPlateau = infos.split(delimiter);

        var width = Integer.parseInt(dimOfPlateau[0]);
        var height = Integer.parseInt(dimOfPlateau[1]);

        return new Plateau(width, height, new ConcurrentLinkedDeque<>());
    }
}
