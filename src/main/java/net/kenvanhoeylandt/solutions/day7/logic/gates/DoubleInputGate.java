package net.kenvanhoeylandt.solutions.day7.logic.gates;

import net.kenvanhoeylandt.solutions.day7.logic.Gate;
import net.kenvanhoeylandt.solutions.day7.logic.GateManager;
import net.kenvanhoeylandt.solutions.day7.logic.IntegerUtils;
import net.kenvanhoeylandt.solutions.day7.logic.ValueProvider;
import net.kenvanhoeylandt.solutions.day7.logic.valueproviders.GateValueProvider;
import net.kenvanhoeylandt.solutions.day7.logic.valueproviders.StaticValueProvider;

import javax.annotation.Nullable;

/**
 * A gate that has a two other gate outputs as input.
 */
abstract public class DoubleInputGate extends Gate
{
	private final ValueProvider mFirstValueProvider;

	private final ValueProvider mSecondValueProvider;

	private @Nullable Long mFirstValue;

	private @Nullable Long mSecondValue;

	public DoubleInputGate(GateManager gateManager, String outputName, String firstInputName, String secondInputName)
	{
		super(gateManager, outputName);

		// The input can be a variable or a constant
		if (IntegerUtils.isInteger(firstInputName))
		{
			mFirstValueProvider = new StaticValueProvider(Integer.valueOf(firstInputName));
		}
		else
		{
			mFirstValueProvider = new GateValueProvider(gateManager, firstInputName);
		}

		// The input can be a variable or a constant
		if (IntegerUtils.isInteger(secondInputName))
		{
			mSecondValueProvider = new StaticValueProvider(Integer.valueOf(secondInputName));
		}
		else
		{
			mSecondValueProvider = new GateValueProvider(gateManager, secondInputName);
		}
	}

	protected long getFirstInputValue()
	{
		// Must cache value, or the process will be very slow
		if (mFirstValue == null)
		{
			mFirstValue = mFirstValueProvider.getValue();
		}

		return mFirstValue;
	}

	protected long getSecondInputValue()
	{
		// Must cache value, or the process will be very slow
		if (mSecondValue == null)
		{
			mSecondValue = mSecondValueProvider.getValue();
		}

		return mSecondValue;
	}
}
