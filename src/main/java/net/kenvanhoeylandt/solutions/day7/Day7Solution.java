package net.kenvanhoeylandt.solutions.day7;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;
import net.kenvanhoeylandt.solutions.day7.computer.Day7ComputerSolution;
import net.kenvanhoeylandt.solutions.day7.network.Day7NetworkSolution;

import java.util.Arrays;
import java.util.Objects;

public class Day7Solution extends Solution

{
	public Day7Solution()
	{
		super(7);
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{

		Task<Object> computerTask = new Day7ComputerSolution().solve(input);
		Task<Object> networkTask = new Day7NetworkSolution().solve(input);

		return Task.whenAll(Arrays.asList(
				computerTask,
				networkTask
		))
				.onSuccess(task -> String.format("%s\n%s", computerTask.getResult(), networkTask.getResult()));
	}
}
