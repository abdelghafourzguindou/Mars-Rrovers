package org.nasa.mars.rovers;

import lombok.RequiredArgsConstructor;
import org.nasa.mars.rovers.model.Rover;
import org.nasa.mars.rovers.service.PlateauService;
import org.nasa.mars.rovers.service.RoverService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class RoversCommandLineRunner implements CommandLineRunner {
    private final PlateauService plateauService;
    private final RoverService roverService;

    private static final String quitter = "q";

    @Override
    public void run(String... args) {
        try {
            var reader = new Scanner(System.in);

            var plateauInfos = reader.nextLine();
            var plateau = plateauService.createPlateau(plateauInfos);

            System.out.println("Press q to quite!");

            while (!reader.hasNext(quitter)) {
                String roverInfo = reader.nextLine();
                Rover rover = roverService.createRover(roverInfo, plateau);
                String instructions = reader.nextLine();
                roverService.processInstruction(rover, instructions);
            }

            plateauService.printInfos(plateau);
        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage());
        }
    }
}
