package net.kenvanhoeylandt.solutions.day7.logic.valueproviders;

import net.kenvanhoeylandt.solutions.day7.logic.Gate;
import net.kenvanhoeylandt.solutions.day7.logic.GateManager;
import net.kenvanhoeylandt.solutions.day7.logic.ValueProvider;

public class GateValueProvider implements ValueProvider
{
	private final GateManager mGateManager;

	private final String mGateName;

	public GateValueProvider(GateManager gateManager, String gateName)
	{
		mGateManager = gateManager;
		mGateName = gateName;
	}

	@Override
	public long getValue()
	{
		Gate gate = mGateManager.getGate(mGateName);

		if (gate == null)
		{
			throw new RuntimeException("gate not found for name " + mGateName);
		}

		return gate.getValue();
	}
}
