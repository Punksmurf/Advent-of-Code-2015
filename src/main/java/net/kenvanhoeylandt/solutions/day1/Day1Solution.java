package net.kenvanhoeylandt.solutions.day1;

import bolts.Task;

public class Day1Solution extends net.kenvanhoeylandt.solutions.Solution
{
	public Day1Solution()
	{
		super(1);
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{
		int floor_index = 0;
        int basement_position = -1;

		for (int i = 0; i < input.length(); ++i)
		{
			char character = input.charAt(i);
            if (character == '(')
            {
                floor_index++;
            }
            else
            {
                floor_index--;
            }

            if (floor_index < 0 && basement_position == -1)
            {
                basement_position = i + 1;
            }
		}

		String result = String.format("Floors: %d, Basement counter: %d", floor_index, basement_position);

		return Task.forResult(result);
	}
}
