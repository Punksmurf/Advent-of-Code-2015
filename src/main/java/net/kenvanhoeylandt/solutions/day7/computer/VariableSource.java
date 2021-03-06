package net.kenvanhoeylandt.solutions.day7.computer;

import java.util.Map;

public class VariableSource extends Source
{
	private String mName;

	public VariableSource(String name)
	{
		mName = name;
	}

	@Override
	public Integer getValue(Map<String, Integer> values)
	{
		if (values.containsKey(mName))
		{
			return values.get(mName);
		}

		return null;
	}

	@Override
	public void setValue(Object value)
	{
		if (value instanceof String)
		{
			mName = (String)value;
		}
		else
		{
			throw new RuntimeException("Cannot assign non-string value");
		}
	}
}
