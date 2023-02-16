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
	LEFT('L'), RIGHT('R'), MOVE('M');

	private final char letter;

	public static List<Instruction> translate(String command) {
		return command.chars()
				.mapToObj(letter -> (char)letter)
				.map(Instruction::mapInstruction)
				.collect(Collectors.toList());
	}

	private static Instruction mapInstruction(char letter) {
		return EnumSet.allOf(Instruction.class)
				.stream()
				.filter(instruction -> instruction.getLetter() == letter)
				.findFirst()
				.orElseThrow(() -> new UnknownInstructionException(letter));
	}
}
