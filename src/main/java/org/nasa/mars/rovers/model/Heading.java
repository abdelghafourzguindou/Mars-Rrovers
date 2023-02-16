package org.nasa.mars.rovers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nasa.mars.rovers.exception.UnknownHeadingException;

import java.util.EnumSet;

@AllArgsConstructor
@Getter
public enum Heading {
	NORTH('N'), EAST('E'), SOUTH('S'), WEST('W');

	private final char letter;

	public static Heading translate(char letter) {
		return EnumSet.allOf(Heading.class)
				.stream()
				.filter(instruction -> instruction.getLetter() == letter)
				.findFirst()
				.orElseThrow(() -> new UnknownHeadingException(letter));
	}
}
