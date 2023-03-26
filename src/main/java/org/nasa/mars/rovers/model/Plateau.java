package org.nasa.mars.rovers.model;

import org.nasa.mars.rovers.exception.CoordinateNotOnPlateauException;
import org.nasa.mars.rovers.exception.CoordinateOccupiedException;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * A plateau representation with the maximum dimension width, height
 * @param width
 * @param height
 * @param movables
 */
public record Plateau(int width, int height, ConcurrentLinkedDeque<Movable> movables) {

	/**
	 * Drop a movable into the plateau
	 * @param movable like Rover
	 * @return the new state of the plateau
	 */
	public Plateau drop(Movable movable) {
		this.movables.add(movable);
		return this;
	}

	/**
	 * Create a plateau with the maximum dimension and init the list of movables
	 * @param width max X
	 * @param height max Y
	 * @return Plateau
	 */
	public static Plateau of(int width, int height) {
		return new Plateau(width, height, new ConcurrentLinkedDeque<>());
	}

	/**
	 * Check the validity of a given coordinate in the plateau
	 * @param coordinate to check
	 */
	public void checkCoordinate(Coordinate coordinate) {
		if (!this.coordinateInPlateau(coordinate)) {
			throw new CoordinateNotOnPlateauException();
		}
		if (!this.coordinateNotOccupied(coordinate)) {
			throw new CoordinateOccupiedException();
		}
	}

	/**
	 * Check if a coordinate is in the plateau
	 * @param coordinate to check
	 * @return true if the coordinate in the plateau else return false
	 */
	private boolean coordinateInPlateau(Coordinate coordinate) {
		return coordinate.x() >= 0 && coordinate.x() <= width && coordinate.y() >= 0 && coordinate.y() <= height;
	}

	/**
	 * Check if given coordinate is already occupied by another movable
	 * @param coordinate to check
	 * @return true if there is no movable occupied the coordinate, else true
	 */
	private boolean coordinateNotOccupied(Coordinate coordinate) {
		return this.movables.stream().noneMatch(movable -> movable.getCoordinate().equals(coordinate));
	}
}
