package net.kenvanhoeylandt.solutions.day7.computer;

import java.util.HashMap;
import java.util.Map;

public class Variables
{
	private Map<String, Integer> mValues = new HashMap<>();

	public Integer getValue(String name)
	{
		return mValues.get(name);
	}

	public Integer getValue(Source source)
	{
		return source.getValue(mValues);
	}

	public void setValue(String name, int value)
	{
		if (!mValues.containsKey(name))
		{
			mValues.put(name, value & 0xffff);
		}
	}
}
