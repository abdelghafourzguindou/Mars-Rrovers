package org.nasa.mars.rovers;

import lombok.RequiredArgsConstructor;
import org.nasa.mars.rovers.model.Heading;
import org.nasa.mars.rovers.model.Instruction;
import org.nasa.mars.rovers.model.Plateau;
import org.nasa.mars.rovers.model.Position;
import org.nasa.mars.rovers.model.Rover;
import org.nasa.mars.rovers.service.PlateauService;
import org.nasa.mars.rovers.service.RoverService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

@SpringBootApplication
public class RoversApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoversApplication.class, args);
    }

}
