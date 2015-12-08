package net.kenvanhoeylandt.solutions.day7.logic.gates;

import net.kenvanhoeylandt.solutions.day7.logic.GateManager;

public class AndGate extends DoubleInputGate
{
	public AndGate(GateManager gateManager, String outputName, String firstInputName, String secondInputName)
	{
		super(gateManager, outputName, firstInputName, secondInputName);
	}

	@Override
	public long getValue()
	{
		return getFirstInputValue() & getSecondInputValue();
	}
}
