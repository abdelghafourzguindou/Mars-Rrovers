package org.nasa.mars.rovers.model;

import java.util.List;

/**
 * The "Simulation" Interface simulates the execution of instructions given by NASA to control a movable on Mars.
 * It provides methods for initializing the rover's position and direction, as well as executing a series of commands
 * to move the rover and return its final position and direction.
 */
public interface Simulation {

    /**
     * Run a list of worker to get the final state of rovers
     * @param workers list of movable, instructions entry
     * @return future state of the movable
     */
    default List<Movable> run(List<Worker> workers) {
        return workers.stream().map(Worker::start).toList();
    }
}
