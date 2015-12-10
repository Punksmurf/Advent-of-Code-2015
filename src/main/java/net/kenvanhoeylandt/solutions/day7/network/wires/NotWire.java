package net.kenvanhoeylandt.solutions.day7.network.wires;

import bolts.Task;
import net.kenvanhoeylandt.solutions.day7.network.wires.Wire;

public class NotWire extends Wire
{

	@Override
	public Task<Integer> solveInteral()
	{
		return getInputA()
				.onSuccess(task -> (~task.getResult()) & 0xffff);
	}
}
