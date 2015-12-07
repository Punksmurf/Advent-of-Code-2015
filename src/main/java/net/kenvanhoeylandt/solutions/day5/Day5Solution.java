package net.kenvanhoeylandt.solutions.day5;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;

public class Day5Solution extends Solution
{
	public Day5Solution()
	{
		super(5);
	}

	@Override
	protected Task<Object> solve(String input) throws Exception
	{
		String[] lines = input.split("\n");

		int count = 0;

		for (String line : lines)
		{
			if (isMatch(line))
			{
				System.out.println("match: " + line);
				count++;
			}
		}

		return Task.forResult(count);
	}

	boolean isMatch(String line)
	{
		return line.matches(".*[aeiou].*[aeiou].*[aeiou].*")
			&& line.matches(".*([a-z])\\1.*")
			&& !line.matches(".*(ab|cd|pq|xy).*");
	}
}
