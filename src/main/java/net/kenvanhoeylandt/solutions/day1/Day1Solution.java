package net.kenvanhoeylandt.solutions.day1;

import bolts.Task;
import net.kenvanhoeylandt.exceptions.InputParsingException;

public class Day1Solution extends net.kenvanhoeylandt.solutions.Solution
{
	public Day1Solution()
	{
		super(1);
	}

	@Override
	protected Task<String> solve(String input) throws Exception
	{
		int count = 0;

		for (int i = 0; i < input.length(); ++i)
		{
			char character = input.charAt(i);

			count += getFloorDifference(character);
		}

		String result = Integer.toString(count);

		return Task.forResult(result);
	}

	/**
	 * @param character '(' means the floor goes up and ')' means the floor goes down. Anything else throws an exception
	 * @return 1 when the floor goes up and -1 when it goes down
	 * @throws InputParsingException on invalid input
	 */
	private int getFloorDifference(char character) throws InputParsingException
	{
		switch (character)
		{
			case '(':
				return 1;

			case ')':
				return -1;

			default:
				throw new InputParsingException("Invalid input: \'" + character + "\'");
		}
	}
}
