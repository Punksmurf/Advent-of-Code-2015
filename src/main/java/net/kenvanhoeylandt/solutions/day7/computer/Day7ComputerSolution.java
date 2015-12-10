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
		Computer computer = new Computer();
		computer.load(input.split("\n"));

		computer.run();

		String result = String.format("Computer says: a = %d", computer.getResult());

		return Task.forResult(result);
	}

}
