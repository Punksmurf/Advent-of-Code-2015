package net.kenvanhoeylandt.solutions.day7.network;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;
import net.kenvanhoeylandt.solutions.day7.network.wires.Wire;

import java.util.Arrays;

public class Day7NetworkSolution extends Solution
{
	public Day7NetworkSolution()
	{
		super(7);
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{
		for (String wire_description : input.split("\n"))
		{
			Wire.create(wire_description);
		}

		return Wire.getWire("a").solve()
				.onSuccess(task -> String.format("Network says: a = %d", task.getResult()));
	}

}
