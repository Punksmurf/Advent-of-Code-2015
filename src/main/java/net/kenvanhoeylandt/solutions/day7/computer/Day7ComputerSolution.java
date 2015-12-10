package net.kenvanhoeylandt.solutions.day7.computer;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;

public class Day7ComputerSolution extends Solution
{
	public Day7ComputerSolution()
	{
		super(7);
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{
		Computer first_computer = new Computer();
		first_computer.load(input.split("\n"));
		first_computer.run();

		Computer second_computer = new Computer();
		second_computer.load(input.split("\n"));
		second_computer.getInstructionForDestination("b").getSourceA().setValue(first_computer.getResult());
		second_computer.run();

		String result = String.format("Computer says: a (first) = %d\nComputer says: a (second) = %d",
				first_computer.getResult(),
				second_computer.getResult());

		return Task.forResult(result);
	}

}
