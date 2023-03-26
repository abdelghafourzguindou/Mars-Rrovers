package org.nasa.mars.rovers.model;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Simulation {
    default List<Movable> run(List<Worker> workers) {
        return workers.parallelStream()
                .map(Worker::start)
                .map(CompletableFuture::join)
                .toList();
    }
}
