package org.nasa.mars.rovers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nasa.mars.rovers.exception.UnknownDirectionException;

import java.util.EnumSet;

/**
 * The Direction class represents the four cardinal directions: North, East, South, and West.
 */
@AllArgsConstructor
@Getter
public enum Direction {

	NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

	private final String code;

	/**
	 * Map a given string to a heading
	 * @param code a character
	 * @return Direction else throw an UnknownDirectionException
	 */
	public static Direction map(String code) {
		return EnumSet.allOf(Direction.class)
				.stream()
				.filter(instruction -> instruction.getCode().equals(code))
				.findFirst()
				.orElseThrow(() -> new UnknownDirectionException(code));
	}

	/**
	 * Turn left operation
	 * @return Direction
	 */
	public Direction left() {
		return switch (this) {
			case EAST -> NORTH;
			case NORTH -> WEST;
			case WEST -> SOUTH;
			case SOUTH -> EAST;
		};
	}

	/**
	 * Turn right operation
	 * @return Direction
	 */
	public Direction right() {
		return switch (this) {
			case EAST -> SOUTH;
			case NORTH -> EAST;
			case WEST -> NORTH;
			case SOUTH -> WEST;
		};
	}
}
