package org.nasa.mars.rovers.service;

import lombok.RequiredArgsConstructor;
import org.nasa.mars.rovers.model.Plateau;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static org.nasa.mars.rovers.service.Utils.delimiter;

@Service
@RequiredArgsConstructor
public class PlateauService {

    public Plateau createPlateau(String infos) {
        var dimOfPlateau = infos.split(delimiter);
        var dimX = Integer.parseInt(dimOfPlateau[0]);
        var dimY = Integer.parseInt(dimOfPlateau[1]);
        return new Plateau(dimX, dimY, new ArrayList<>());
    }

    public void printInfos(Plateau plateau) {
        plateau.rovers().forEach(rover -> System.out.println(rover.printInfo()));
    }
}
