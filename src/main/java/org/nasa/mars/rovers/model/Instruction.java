package org.nasa.mars.rovers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nasa.mars.rovers.exception.UnknownInstructionException;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Instruction {
	LEFT("L"), RIGHT("R"), MOVE("M");

	private final String code;

	public static List<Instruction> toList(String command) {
		return command.chars()
				.mapToObj(letter -> String.valueOf((char)letter))
				.map(Instruction::map)
				.collect(Collectors.toList());
	}

	private static Instruction map(String code) {
		return EnumSet.allOf(Instruction.class)
				.stream()
				.filter(instruction -> instruction.getCode().equals(code))
				.findFirst()
				.orElseThrow(() -> new UnknownInstructionException(code));
	}
}
