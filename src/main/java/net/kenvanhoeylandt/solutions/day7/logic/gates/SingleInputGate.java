package net.kenvanhoeylandt.solutions.day7.logic.gates;

import net.kenvanhoeylandt.solutions.day7.logic.Gate;
import net.kenvanhoeylandt.solutions.day7.logic.GateManager;
import net.kenvanhoeylandt.solutions.day7.logic.IntegerUtils;
import net.kenvanhoeylandt.solutions.day7.logic.ValueProvider;
import net.kenvanhoeylandt.solutions.day7.logic.valueproviders.GateValueProvider;
import net.kenvanhoeylandt.solutions.day7.logic.valueproviders.StaticValueProvider;

import javax.annotation.Nullable;

/**
 * A gate that has a single other gate output as input.
 */
abstract public class SingleInputGate extends Gate
{
	private final ValueProvider mValueProvider;

	private @Nullable Integer mValue = null;

	public SingleInputGate(GateManager gateManager, String outputName, String inputName)
	{
		super(gateManager, outputName);

		// The input can be a variable or a constant
		if (IntegerUtils.isInteger(inputName))
		{
			mValueProvider = new StaticValueProvider(Integer.valueOf(inputName));
		}
		else
		{
			mValueProvider = new GateValueProvider(gateManager, inputName);
		}
	}

	protected int getInputValue()
	{
		// Must cache value, or the process will be very slow
		if (mValue == null)
		{
			mValue = mValueProvider.getValue();
		}

		return mValue;
	}
}
