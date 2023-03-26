package org.nasa.mars.rovers.compenent;

import org.nasa.mars.rovers.model.Instruction;
import org.nasa.mars.rovers.model.Simulation;
import org.nasa.mars.rovers.service.PlateauService;
import org.nasa.mars.rovers.service.RoverService;
import org.nasa.mars.rovers.model.Worker;
import org.nasa.mars.rovers.utils.Utils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * RoversCommandLineRunner class is component to create a command line server application
 * By running a socket server in the port 80 you can interact with it and create a simulation
 *
 * @param plateauService
 * @param roverService
 */
@Component
public record RoversCommandLineRunner(PlateauService plateauService, RoverService roverService) implements Simulation, CommandLineRunner {

    @Override
    public void run(String... args) throws IOException {
        System.out.print("Your simulation is starting at 80...");
        try (var serverSocket = new ServerSocket(80)) {
            var clientSocket = serverSocket.accept();
            var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            var out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("Welcome to Mars movables!");

            var workers = createWorkers(in, out);

            out.println("Your movables changed position to: ");

            this.run(workers).forEach(movable -> out.println(movable.printInfo()));

            System.exit(0);
        }
    }

    /**
     * Interact with a user and create a suite of objects (rover, instruction) and return a set of workers
     * @param in input
     * @param out output
     * @return List<Worker> or throw a RuntimeException
     */
    private List<Worker> createWorkers(BufferedReader in, PrintWriter out) {
        var workers = new ArrayList<Worker>();
        try {
            out.println("Please enter the plateau dimension");
            var plateauInfos = in.readLine();
            var plateau = plateauService.createPlateau(plateauInfos);

            out.println("Please enter the instructions and Press q to quite");

            var infos = in.readLine();

            while (!Utils.quitter.equals(infos)) {
                var rover = roverService.createRover(infos.toUpperCase(), plateau);
                infos = in.readLine();
                workers.add(new Worker(rover, Instruction.toList(infos.toUpperCase())));
                infos = in.readLine();
            }

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }

        return workers;
    }
}
