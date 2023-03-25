package org.nasa.mars.rovers.model;

import org.nasa.mars.rovers.exception.CoordinateNotOnPlateauException;
import org.nasa.mars.rovers.exception.CoordinateOccupiedException;

import java.util.concurrent.ConcurrentLinkedDeque;

public record Plateau(int width, int height, ConcurrentLinkedDeque<Rover> rovers) {

	public Plateau drop(Rover rover) {
		this.rovers.add(rover);
		return this;
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
		return this.rovers
				.stream()
				.noneMatch(droppedRover -> droppedRover.getCoordinate().equals(coordinate));
	}
}
