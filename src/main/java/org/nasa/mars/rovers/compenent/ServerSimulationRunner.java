package org.nasa.mars.rovers.compenent;

import lombok.RequiredArgsConstructor;
import org.nasa.mars.rovers.service.Simulation;
import org.nasa.mars.rovers.utils.CommonUtil;
import org.nasa.mars.rovers.service.SimulationParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

/**
 * ServerSimulationRunner class is component to create a command line server application
 * By running a socket server in the port 80 you can interact with it and create a simulation
 */
@Component
@Profile("server")
@RequiredArgsConstructor
public class ServerSimulationRunner implements CommandLineRunner {

    @Value("${simulation.server.port}")
    private int port;

    private final Simulation simulation;

    @Override
    public void run(String... args) throws IOException {
        System.out.print("Your simulation is starting at " + port);

        try (var serverSocket = new ServerSocket(port)) {
            var clientSocket = serverSocket.accept();
            var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            var out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("Welcome to Mars movables!");

            var parser = new SimulationParser(getConfig(in, out)::lines);
            var workers = simulation.create(parser);
            simulation.run(workers).forEach(movable -> out.println(movable.printInfo()));

            System.exit(0);
        }
    }

    /**
     * Interact with a user and create a suite of objects (rover, instruction) and return a set of workers
     *
     * @param in  input
     * @param out output
     * @return String or throw a RuntimeException
     */
    private String getConfig(BufferedReader in, PrintWriter out) {
        var config = "";
        try {
            var infos = in.readLine();
            while (!CommonUtil.quitter.equals(infos)) {
                config = config.length() > 0 ? config + "\n" + infos : infos;
                infos = in.readLine();
            }
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
        return config;
    }
}
