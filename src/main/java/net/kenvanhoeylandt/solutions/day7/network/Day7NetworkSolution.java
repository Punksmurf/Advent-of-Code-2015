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

		Task<Integer> first_task = Wire.getWire("a").solve();

		return first_task
				.onSuccessTask(task -> {
					Wire.resetAll();
					Wire.getWire("b").setInputA(String.valueOf(task.getResult()));
					return Wire.getWire("a").solve();
				})
				.onSuccess(second_task ->
					String.format("Network says: a (first) = %d\nNetwork says: a (second) = %d",
							first_task.getResult(),
							second_task.getResult())
				);

	}
}
