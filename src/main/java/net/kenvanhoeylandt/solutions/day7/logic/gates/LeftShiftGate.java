package net.kenvanhoeylandt.solutions.day7.logic.gates;

import net.kenvanhoeylandt.solutions.day7.logic.GateManager;

public class LeftShiftGate extends SingleInputGate
{
	private final int mShift;

	public LeftShiftGate(GateManager gateManager, String outputName, String inputName, int shift)
	{
		super(gateManager, outputName, inputName);

		mShift = shift;
	}

	@Override
	public long getValue()
	{
		return getInputValue() << mShift;
	}
}
