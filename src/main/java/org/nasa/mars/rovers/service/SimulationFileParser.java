package org.nasa.mars.rovers.service;

public class SimulationFileParser extends SimulationParser {

    public SimulationFileParser(SimulationFileReader reader) {
        super(reader.getLines());
    }
}
