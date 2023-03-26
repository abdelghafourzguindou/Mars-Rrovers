package org.nasa.mars.rovers.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * A NASA rover is a robotic vehicle designed to explore the surface of other planets, moons, or asteroids.
 * NASA has sent several rovers to Mars, including the Sojourner, Spirit, Opportunity, and Curiosity rovers,
 * which have all made significant discoveries on the Red Planet.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Rover implements Movable {

	private Coordinate coordinate;
	private Direction direction;

	@Setter
	private Plateau plateau;

	public static Rover createAt(int x, int y, Direction direction) {
		return new Rover(new Coordinate(x, y), direction, null);
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
		this.coordinate = this.moveForward();
	}

	@Override
	public String printInfo() {
		return this.coordinate.toString() + " " + this.direction.getCode();
	}

	/**
	 * Check if a rover has the given coordinate
	 * @param coordinate to check
	 * @return true, else false
	 */
	public boolean hasPosition(Coordinate coordinate) {
		return this.coordinate.equals(coordinate);
	}

	/**
	 * Move forward one grid point, and maintain the same heading. Assume that the square directly North from (x, y) is (x, y+1).
	 * @return Coordinate or throw CoordinateNotOnPlateauException or CoordinateOccupiedException
	 */
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
