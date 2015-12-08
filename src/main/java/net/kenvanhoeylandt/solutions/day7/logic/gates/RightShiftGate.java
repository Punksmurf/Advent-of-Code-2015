package net.kenvanhoeylandt.solutions.day7.logic.gates;

import net.kenvanhoeylandt.solutions.day7.logic.GateManager;

public class RightShiftGate extends SingleInputGate
{
	private final int mShift;

	public RightShiftGate(GateManager gateManager, String outputName, String inputName, int shift)
	{
		super(gateManager, outputName, inputName);

		mShift = shift;
	}

	@Override
	public int getValue()
	{
		return getInputValue() >> mShift;
	}
}
