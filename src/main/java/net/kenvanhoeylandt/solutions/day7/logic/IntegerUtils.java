package net.kenvanhoeylandt.solutions.day7.logic;

public class IntegerUtils
{
	// Based on: http://stackoverflow.com/a/5439547/3848666
	public static boolean isInteger(String s)
	{
		return isInteger(s, 10);
	}

	// Based on: http://stackoverflow.com/a/5439547/3848666
	public static boolean isInteger(String s, int radix)
	{
		if (s.isEmpty())
		{
			return false;
		}

		for (int i = 0; i < s.length(); i++)
		{
			if (i == 0 && s.charAt(i) == '-')
			{
				if (s.length() == 1)
				{
					return false;
				}
				else
				{
					continue;
				}
			}

			if (Character.digit(s.charAt(i), radix) < 0)
			{
				return false;
			}
		}

		return true;
	}
}
