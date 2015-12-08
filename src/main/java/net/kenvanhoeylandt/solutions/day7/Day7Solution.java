package net.kenvanhoeylandt.solutions.day7;

import net.kenvanhoeylandt.solutions.Solution;
import net.kenvanhoeylandt.solutions.day7.logic.GateFactory;
import net.kenvanhoeylandt.solutions.day7.logic.GateManager;

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

		GateFactory factory = new GateFactory();
		GateManager manager = new GateManager();

		// Create all the gates (they auto-register to the GateManager)
		factory.create(manager, inputs);

		return manager.getGate("a").getValue();
	}
}
