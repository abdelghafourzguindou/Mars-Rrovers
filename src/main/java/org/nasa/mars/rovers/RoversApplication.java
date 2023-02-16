package org.nasa.mars.rovers;

import org.nasa.mars.rovers.model.Heading;
import org.nasa.mars.rovers.model.Instruction;
import org.nasa.mars.rovers.model.Plateau;
import org.nasa.mars.rovers.model.Position;
import org.nasa.mars.rovers.model.Rover;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

@SpringBootApplication
public class RoversApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoversApplication.class, args);

        Scanner readData = new Scanner(System.in);

        String[] dimOfPlateau = readData.nextLine().split(" ");
        var dimX = Integer.parseInt(dimOfPlateau[0]);
        var dimY = Integer.parseInt(dimOfPlateau[1]);
        var plateau = new Plateau(dimX, dimY, new ArrayList<>());

        System.out.println("Type send to start processing");

        while(!readData.hasNext("send")) {
            String[] roverInfos = readData.nextLine().toUpperCase().split(" ");
            int x = Integer.parseInt(roverInfos[0]);
            int y = Integer.parseInt(roverInfos[1]);
            Heading heading = Heading.translate(roverInfos[2].charAt(0));
            Rover rover = new Rover(new Position(x, y), heading, plateau);
            rover.drop();
            String instructions = readData.nextLine().toUpperCase();
            rover.processInstructions(Instruction.translate(instructions));

            System.out.printf(rover.printInfo());
        }
    }

}
