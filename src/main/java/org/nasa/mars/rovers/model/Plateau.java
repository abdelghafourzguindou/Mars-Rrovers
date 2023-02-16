package org.nasa.mars.rovers.model;

import java.util.*;

public record Plateau(int dimX, int dimY, List<Rover> rovers) {

	public void addRover(Rover rover) {
		this.rovers.add(rover);
	}
	
	public boolean isOccupied(Rover rover) {
		return this.rovers.stream()
				.filter(otherRover -> !otherRover.equals(rover))
				.anyMatch(otherRover -> otherRover.hasPosition(rover.getPosition()));
	}
}
