package org.nasa.mars.rovers.utils.filetools;

import org.nasa.mars.rovers.model.Direction;
import org.nasa.mars.rovers.model.Instruction;
import org.nasa.mars.rovers.model.Movable;
import org.nasa.mars.rovers.model.Plateau;
import org.nasa.mars.rovers.model.Rover;
import org.nasa.mars.rovers.model.Worker;
import org.nasa.mars.rovers.utils.CommonUtil;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public record SimulationFileParser(SimulationFileReader reader) {

    public Plateau parsePlateau() {
        var data = reader.getLines()
                .get()
                .findFirst()
                .orElseThrow()
                .split(CommonUtil.delimiter);

        return Plateau.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
    }

    public List<Movable> parseMovables() {
        return reader.getLines()
                .get()
                .skip(1)
                .filter(line -> Character.isDigit(line.charAt(0)))
                .map(extractMovable())
                .toList();
    }

    public List<List<Instruction>> parseInstructions() {
        return reader.getLines()
                .get()
                .skip(1)
                .filter(line -> Character.isAlphabetic(line.charAt(0)))
                .map(Instruction::toList)
                .toList();
    }

    public List<Worker> makeWorkers(List<Movable> movables, List<List<Instruction>> instructions) {
        return IntStream.range(0, movables.size())
                .mapToObj(index -> new Worker(movables.get(index), instructions.get(index)))
                .toList();
    }

    private static Function<String, Movable> extractMovable() {
        return line -> {
            var data = line.split(CommonUtil.delimiter);
            var x = Integer.parseInt(data[0]);
            var y = Integer.parseInt(data[1]);
            var direction = Direction.map(data[2]);
            return Rover.createAt(x, y, direction);
        };
    }
}
