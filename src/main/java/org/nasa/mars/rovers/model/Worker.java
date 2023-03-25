package org.nasa.mars.rovers.model;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public record Worker(Rover rover, List<Instruction> instructions) {
    public CompletableFuture<Rover> start() {
        return CompletableFuture.supplyAsync(() -> rover.process(instructions), Executors.newSingleThreadExecutor());
    }
}
