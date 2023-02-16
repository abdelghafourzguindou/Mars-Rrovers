package org.nasa.mars.rovers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nasa.mars.rovers.exception.RoverNotDroppedException;
import org.nasa.mars.rovers.exception.PositionNotOnPlateauException;
import org.nasa.mars.rovers.exception.PositionOccupiedException;

import java.util.List;

import static org.nasa.mars.rovers.model.Heading.EAST;
import static org.nasa.mars.rovers.model.Heading.NORTH;
import static org.nasa.mars.rovers.model.Heading.SOUTH;
import static org.nasa.mars.rovers.model.Heading.WEST;

@AllArgsConstructor
@Getter
public class Rover {

	private Position position;
	private Heading heading;
	private Plateau plateau;

	private void drop(Plateau plateau) {
		checkPosition();
		plateau.addRover(this);
	}

	public void drop() {
		drop(this.plateau);
	}

	public String printInfo() {
		return position.toString() + " " + heading.getLetter();
	}

	public boolean hasPosition(Position position) {
		return this.position.isEqual(position);
	}
	
	public void processInstructions(List<Instruction> instructions) {
		if (position == null || heading == null) {
			throw new RoverNotDroppedException();
		}
		instructions.forEach(this::processInstruction);
	}

	private void processInstruction(Instruction instruction) {
		switch (instruction) {
			case LEFT -> heading = turnLeft();
			case RIGHT -> heading = turnRight();
			case MOVE -> position = moveForward();
		}
	}

	private Heading turnLeft() {
		return switch (heading) {
			case EAST -> NORTH;
			case NORTH -> WEST;
			case WEST -> SOUTH;
			case SOUTH -> EAST;
		};
	}

	private Heading turnRight() {
		return switch (heading) {
			case EAST -> SOUTH;
			case NORTH -> EAST;
			case WEST -> NORTH;
			case SOUTH -> WEST;
		};
	}

	private Position moveForward() {
		Position newPosition = switch (heading) {
			case EAST -> new Position(position.x() + 1, position.y());
			case NORTH -> new Position(position.x(), position.y() + 1);
			case WEST -> new Position(position.x()- 1, position.y());
			case SOUTH -> new Position(position.x(), position.y() - 1);
		};
		checkPosition();
		return newPosition;
	}

	private void checkPosition() {
		if (!position.isOnPlateau(plateau)) {
			throw new PositionNotOnPlateauException();
		}
		if (plateau.isOccupied(this)) {
			throw new PositionOccupiedException();
		}
	}
}
