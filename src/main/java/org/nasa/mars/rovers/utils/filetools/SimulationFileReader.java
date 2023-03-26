package org.nasa.mars.rovers.utils.filetools;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;
import java.util.stream.Stream;

public record SimulationFileReader(String fileName) {

    public Supplier<Stream<String>> getLines() {
        return this::readToStream;
    }

    private Stream<String> readToStream() {
        try {
            return Files.lines(Path.of(ClassLoader.getSystemResource(fileName).toURI()));
        } catch (IOException | URISyntaxException e) {
            return Stream.empty();
        }
    }
}
