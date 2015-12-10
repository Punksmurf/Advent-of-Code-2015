package net.kenvanhoeylandt.solutions.day7.computer;

import java.util.Map;

public class StaticSource extends Source
{
	private final Integer mValue;

	public StaticSource(Integer value)
	{
		mValue = value;
	}

	@Override
	public Integer getValue(Map<String, Integer> values)
	{
		return mValue;
	}
}
