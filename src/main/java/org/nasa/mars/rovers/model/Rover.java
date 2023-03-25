package org.nasa.mars.rovers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nasa.mars.rovers.exception.RoverNotDroppedException;

import java.util.List;

import static org.nasa.mars.rovers.model.Direction.EAST;
import static org.nasa.mars.rovers.model.Direction.NORTH;
import static org.nasa.mars.rovers.model.Direction.SOUTH;
import static org.nasa.mars.rovers.model.Direction.WEST;

@AllArgsConstructor
@Getter
public class Rover {

	private Coordinate coordinate;
	private Direction direction;
	private Plateau plateau;

	public void drop() {
		drop(this.plateau);
	}

	public String printInfo() {
		return coordinate.toString() + " " + direction.getCode();
	}

	public boolean hasPosition(Coordinate coordinate) {
		return this.coordinate.equals(coordinate);
	}
	
	public void processInstructions(List<Instruction> instructions) {
		if (coordinate == null || direction == null) {
			throw new RoverNotDroppedException();
		}
		instructions.forEach(this::processInstruction);
	}

	private void drop(Plateau plateau) {
		plateau.checkCoordinate(this.coordinate);
		plateau.drop(this);
	}

	private void processInstruction(Instruction instruction) {
		switch (instruction) {
			case LEFT -> direction = turnLeft();
			case RIGHT -> direction = turnRight();
			case MOVE -> coordinate = moveForward();
		}
	}

	private Direction turnLeft() {
		return switch (direction) {
			case EAST -> NORTH;
			case NORTH -> WEST;
			case WEST -> SOUTH;
			case SOUTH -> EAST;
		};
	}

	private Direction turnRight() {
		return switch (direction) {
			case EAST -> SOUTH;
			case NORTH -> EAST;
			case WEST -> NORTH;
			case SOUTH -> WEST;
		};
	}

	private Coordinate moveForward() {
		Coordinate newCoordinate = switch (direction) {
			case EAST -> new Coordinate(coordinate.x() + 1, coordinate.y());
			case NORTH -> new Coordinate(coordinate.x(), coordinate.y() + 1);
			case WEST -> new Coordinate(coordinate.x() - 1, coordinate.y());
			case SOUTH -> new Coordinate(coordinate.x(), coordinate.y() - 1);
		};
		plateau.checkCoordinate(newCoordinate);
		return newCoordinate;
	}

}
