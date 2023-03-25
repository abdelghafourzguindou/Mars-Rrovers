package org.nasa.mars.rovers.model;

public record Coordinate(int x, int y) {

	@Override
	public String toString() {
		return x + " " + y;
	}
}
