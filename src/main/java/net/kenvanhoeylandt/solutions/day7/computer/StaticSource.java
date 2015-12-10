package net.kenvanhoeylandt.solutions.day7.computer;

import java.util.Map;

public class StaticSource extends Source
{
	private Integer mValue;

	public StaticSource(Integer value)
	{
		mValue = value;
	}

	@Override
	public Integer getValue(Map<String, Integer> values)
	{
		return mValue;
	}

	@Override
	public void setValue(Object value)
	{
		if (value instanceof Integer)
		{
			mValue = (Integer)value;
		}
		else
		{
			throw new RuntimeException("Cannot assign non-integer value");
		}
	}
}
