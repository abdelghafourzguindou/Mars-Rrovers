package org.nasa.mars.rovers.model;

public record Position(int x, int y) {

	public boolean isEqual(Position other) {
		return this.x == other.x && this.y == other.y;
	}

	@Override
	public String toString() {
		return x + " " + y;
	}
	
	public boolean isOnPlateau(Plateau plateau) {
		return this.x >= 0 && this.x <= plateau.dimY() && this.y >= 0 && this.y <= plateau.dimY();
	}

}
