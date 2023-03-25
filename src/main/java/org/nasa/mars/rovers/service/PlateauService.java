package org.nasa.mars.rovers.service;

import org.nasa.mars.rovers.model.Plateau;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.ArrayList;

import static org.nasa.mars.rovers.service.Utils.delimiter;

@Service
public record PlateauService() {

    public Plateau createPlateau(String infos) {
        var dimOfPlateau = infos.split(delimiter);
        var dimX = Integer.parseInt(dimOfPlateau[0]);
        var dimY = Integer.parseInt(dimOfPlateau[1]);
        return new Plateau(dimX, dimY, new ArrayList<>());
    }

    public void printInfos(PrintWriter out, Plateau plateau) {
        plateau.rovers().forEach(rover -> out.println(rover.printInfo()));
    }
}
