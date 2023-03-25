package org.nasa.mars.rovers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nasa.mars.rovers.exception.RoverNotDroppedException;

import java.util.List;

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

	public Rover process(List<Instruction> instructions) {
		if (coordinate == null || direction == null) {
			throw new RoverNotDroppedException();
		}
		instructions.forEach(this::execute);
		return this;
	}

	private void drop(Plateau plateau) {
		plateau.checkCoordinate(this.coordinate);
		plateau.drop(this);
	}

	private void execute(Instruction instruction) {
		switch (instruction) {
			case LEFT -> direction = turnLeft();
			case RIGHT -> direction = turnRight();
			case MOVE -> coordinate = moveForward();
		}
	}

	private Direction turnLeft() {
		return this.direction.left();
	}

	private Direction turnRight() {
		return this.direction.right();
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
