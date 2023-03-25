package org.nasa.mars.rovers.compenent;

import org.nasa.mars.rovers.model.Instruction;
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
import java.util.concurrent.CompletableFuture;

@Component
public record RoversCommandLineRunner(PlateauService plateauService, RoverService roverService) implements CommandLineRunner {

    @Override
    public void run(String... args) throws IOException {
        System.out.print("Starting at 80");
        try (var serverSocket = new ServerSocket(80)) {
            var clientSocket = serverSocket.accept();
            var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            var out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("Welcome to Mars rovers!");

            var workers = createWorkers(in, out);

            out.println("The new positions are!");

            workers.parallelStream()
                    .map(Worker::start)
                    .map(CompletableFuture::join)
                    .forEach(rover -> out.println(rover.printInfo()));
        }
    }

    private List<Worker> createWorkers(BufferedReader in, PrintWriter out) {
        var workers = new ArrayList<Worker>();
        try {
            out.println("Enter the plateau dimension!");
            var plateauInfos = in.readLine();
            var plateau = plateauService.createPlateau(plateauInfos);

            out.println("Enter the instructions and Press q to quite!");

            var infos = in.readLine();

            while (!Utils.quitter.equals(infos)) {
                var rover = roverService.createRover(infos, plateau);
                infos = in.readLine();
                workers.add(new Worker(rover, Instruction.toList(infos)));
                infos = in.readLine();
            }

        } catch (Exception e) {
            out.println("Error -> " + e.getMessage());
        }

        return workers;
    }
}
