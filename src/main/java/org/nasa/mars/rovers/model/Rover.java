package org.nasa.mars.rovers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Rover implements Movable {

	private Coordinate coordinate;
	private Direction direction;
	private Plateau plateau;

	@Override
	public void drop() {
		this.drop(this.plateau);
	}

	@Override
	public void turnLeft() {
		this.direction = this.direction.left();
	}

	@Override
	public void turnRight() {
		this.direction = this.direction.right();
	}

	@Override
	public void move() {
		this.coordinate = moveForward();
	}

	@Override
	public String printInfo() {
		return coordinate.toString() + " " + direction.getCode();
	}

	public boolean hasPosition(Coordinate coordinate) {
		return this.coordinate.equals(coordinate);
	}

	private void drop(Plateau plateau) {
		plateau.checkCoordinate(this.coordinate);
		plateau.drop(this);
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
