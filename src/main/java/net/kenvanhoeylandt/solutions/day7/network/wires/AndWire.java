package net.kenvanhoeylandt.solutions.day7.network.wires;

import bolts.Task;
import net.kenvanhoeylandt.solutions.day7.network.wires.Wire;

import java.util.Arrays;

public class AndWire extends Wire
{

	@Override
	public Task<Integer> solveInteral()
	{
		Task<Integer> inputA = getInputA();
		Task<Integer> inputB = getInputB();

		return Task.whenAll(Arrays.asList(inputA, inputB))
				.onSuccess(task -> (inputA.getResult() & inputB.getResult()) & 0xffff);
	}
}
