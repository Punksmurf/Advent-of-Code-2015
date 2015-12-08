package net.kenvanhoeylandt.solutions.day7;

import net.kenvanhoeylandt.exceptions.InputParsingException;
import net.kenvanhoeylandt.solutions.Solution;
import net.kenvanhoeylandt.solutions.day7.logic.Gate;
import net.kenvanhoeylandt.solutions.day7.logic.GateFactory;
import net.kenvanhoeylandt.solutions.day7.logic.GateManager;
import net.kenvanhoeylandt.solutions.day7.logic.gates.ValueGate;

public class Day7Solution extends Solution
{
	public Day7Solution()
	{
		super(7);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		String[] inputs = input.split("\n");

		long part_one_value = solvePartOne(inputs);
		long part_two_value = solvePartTwo(inputs, part_one_value);

		return String.format("part one: %d, part two: %d", part_one_value, part_two_value);
	}

	protected long solvePartOne(String[] inputs) throws InputParsingException
	{
		GateFactory factory = new GateFactory();
		GateManager manager = new GateManager();

		// Create all the gates (they auto-register to the GateManager)
		factory.create(manager, inputs);

		return manager.getGate("a").getValue();
	}

	protected long solvePartTwo(String[] inputs, long value) throws InputParsingException
	{
		GateFactory factory = new GateFactory();
		GateManager manager = new GateManager();

		// Create all the gates (they auto-register to the GateManager)
		// We must re-created them for part two, because of the value caching
		factory.create(manager, inputs);

		// override gate "b"
		Gate b_gate = new ValueGate(manager, "b", Long.toString(value));
		manager.register(b_gate);

		return manager.getGate("a").getValue();
	}
}
