package org.nasa.mars.rovers.controller;

import org.nasa.mars.rovers.exception.SimulationException;
import org.nasa.mars.rovers.model.Movable;
import org.nasa.mars.rovers.service.Simulation;
import org.nasa.mars.rovers.service.SimulationParser;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@Profile("web")
public record SimulationController(Simulation simulation) {

    @PostMapping("/simulation")
    String simulate(@RequestBody String config) {
        var workers = simulation.create(new SimulationParser(config::lines));
        return simulation.run(workers)
                .stream()
                .map(Movable::printInfo)
                .collect(Collectors.joining("\n"));
    }

    @ExceptionHandler(SimulationException.class)
    public ResponseEntity<String> handleSimulationException(SimulationException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
