package org.nasa.mars.rovers;

import org.nasa.mars.rovers.service.PlateauService;
import org.nasa.mars.rovers.service.RoverService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

@Component
public record RoversCommandLineRunner(PlateauService plateauService, RoverService roverService) implements CommandLineRunner {
    private static final String quitter = "q";

    @Override
    public void run(String... args) throws IOException {
        System.out.print("Starting at 80");

        try (var serverSocket = new ServerSocket(80)) {
            var clientSocket = serverSocket.accept();
            var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            var out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("Welcome to Mars rovers!");

            roversProcessor(in, out);
        }
    }

    private void roversProcessor(BufferedReader in, PrintWriter out) {
        try {
            out.println("Enter the plateau dimension!");
            var plateauInfos = in.readLine();
            var plateau = plateauService.createPlateau(plateauInfos);

            out.println("Enter the instructions and Press q to quite!");

            var infos = in.readLine() ;

            while (!quitter.equals(infos)) {
                var rover = roverService.createRover(infos, plateau);
                infos = in.readLine();
                roverService.processInstruction(rover, infos);
                infos = in.readLine();
            }

            out.println("The new locations are!");
            plateauService.printInfos(out, plateau);
        } catch (Exception e) {
            out.println("Error -> " + e.getMessage());
        }
    }
}
