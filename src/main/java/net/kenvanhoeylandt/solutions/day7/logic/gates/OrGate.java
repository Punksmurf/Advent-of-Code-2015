package net.kenvanhoeylandt.solutions.day7.logic.gates;

import net.kenvanhoeylandt.solutions.day7.logic.GateManager;

public class OrGate extends DoubleInputGate
{
	public OrGate(GateManager gateManager, String outputName, String firstInputName, String secondInputName)
	{
		super(gateManager, outputName, firstInputName, secondInputName);
	}

	@Override
	public int getValue()
	{
		return getFirstInputValue() | getSecondInputValue();
	}
}
