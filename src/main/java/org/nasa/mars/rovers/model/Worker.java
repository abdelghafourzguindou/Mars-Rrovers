package org.nasa.mars.rovers.model;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public record Worker(Movable movable, List<Instruction> instructions) {
    public CompletableFuture<Movable> start() {
        return CompletableFuture.supplyAsync(() -> movable.process(instructions), Executors.newSingleThreadExecutor());
    }
}
