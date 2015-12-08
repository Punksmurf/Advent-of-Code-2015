package net.kenvanhoeylandt.solutions.day7.logic.gates;

import net.kenvanhoeylandt.solutions.day7.logic.GateManager;

public class NotGate extends SingleInputGate
{
	public NotGate(GateManager gateManager, String outputName, String inputName)
	{
		super(gateManager, outputName, inputName);
	}

	@Override
	public long getValue()
	{
		return ~ getInputValue();
	}
}
