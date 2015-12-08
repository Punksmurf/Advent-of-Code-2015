package net.kenvanhoeylandt.solutions.day7.logic.gates;

import net.kenvanhoeylandt.solutions.day7.logic.GateManager;

public class ValueGate extends SingleInputGate
{
	public ValueGate(GateManager manager, String outputName, String input)
	{
		super(manager, outputName, input);
	}

	@Override
	public int getValue()
	{
		return getInputValue();
	}
}
