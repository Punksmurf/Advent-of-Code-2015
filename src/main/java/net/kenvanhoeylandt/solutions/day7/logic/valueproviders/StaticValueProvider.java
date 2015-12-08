package net.kenvanhoeylandt.solutions.day7.logic.valueproviders;

import net.kenvanhoeylandt.solutions.day7.logic.ValueProvider;

public class StaticValueProvider implements ValueProvider
{
	private final int mValue;

	public StaticValueProvider(int value)
	{
		mValue = value;
	}

	@Override
	public int getValue()
	{
		return mValue;
	}
}
