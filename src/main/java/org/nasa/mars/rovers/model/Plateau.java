package org.nasa.mars.rovers.model;

import org.nasa.mars.rovers.exception.CoordinateNotOnPlateauException;
import org.nasa.mars.rovers.exception.CoordinateOccupiedException;

import java.util.concurrent.ConcurrentLinkedDeque;

public record Plateau(int width, int height, ConcurrentLinkedDeque<Movable> movables) {

	public Plateau drop(Movable movable) {
		this.movables.add(movable);
		return this;
	}

	public static Plateau of(int width, int height) {
		return new Plateau(width, height, new ConcurrentLinkedDeque<>());
	}

	public void checkCoordinate(Coordinate coordinate) {
		if (!this.coordinateInPlateau(coordinate)) {
			throw new CoordinateNotOnPlateauException();
		}
		if (!this.coordinateNotOccupied(coordinate)) {
			throw new CoordinateOccupiedException();
		}
	}

	private boolean coordinateInPlateau(Coordinate coordinate) {
		return coordinate.x() >= 0 && coordinate.x() <= width && coordinate.y() >= 0 && coordinate.y() <= height;
	}

	private boolean coordinateNotOccupied(Coordinate coordinate) {
		return this.movables.stream().noneMatch(movable -> movable.getCoordinate().equals(coordinate));
	}
}
