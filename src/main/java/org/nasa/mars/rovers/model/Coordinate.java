package org.nasa.mars.rovers.model;

/**
 * The Coordinate class represents a point in Cartesian coordinates, with an x-coordinate and a y-coordinate.
 * @param x
 * @param y
 */
public record Coordinate(int x, int y) {

	@Override
	public String toString() {
		return x + " " + y;
	}
}
