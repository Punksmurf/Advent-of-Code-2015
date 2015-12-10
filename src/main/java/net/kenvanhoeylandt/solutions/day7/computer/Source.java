package net.kenvanhoeylandt.solutions.day7.computer;

import java.util.Map;

public abstract class Source
{
	abstract public Integer getValue(Map<String, Integer> values);
	public static Source create(String description)
	{
		if (description == null)
		{
			return new StaticSource(null);
		}

		try
		{
			return new StaticSource(Integer.valueOf(description));
		}
		catch (NumberFormatException e)
		{
			return new VariableSource(description);
		}
	}
}
