package net.kenvanhoeylandt.solutions.day7.logic.valueproviders;

import net.kenvanhoeylandt.solutions.day7.logic.ValueProvider;

public class StaticValueProvider implements ValueProvider
{
	private final long mValue;

	public StaticValueProvider(long value)
	{
		mValue = value;
	}

	@Override
	public long getValue()
	{
		return mValue;
	}
}
