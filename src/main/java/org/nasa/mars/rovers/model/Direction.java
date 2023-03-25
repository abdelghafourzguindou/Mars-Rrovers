package org.nasa.mars.rovers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nasa.mars.rovers.exception.UnknownDirectionException;

import java.util.EnumSet;

@AllArgsConstructor
@Getter
public enum Direction {
	NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

	private final String code;

	public static Direction map(String code) {
		return EnumSet.allOf(Direction.class)
				.stream()
				.filter(instruction -> instruction.getCode().equals(code))
				.findFirst()
				.orElseThrow(() -> new UnknownDirectionException(code));
	}
}
