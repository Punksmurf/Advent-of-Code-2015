package net.kenvanhoeylandt.solutions.day5;

import net.kenvanhoeylandt.solutions.Solution;

public class Day5Solution extends Solution
{
	public Day5Solution()
	{
		super(5);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		String[] lines = input.split("\n");

		int part_one = solve(lines, line ->
			line.matches(".*[aeiou].*[aeiou].*[aeiou].*")
			&& line.matches(".*([a-z])\\1.*")
			&& !line.matches(".*(ab|cd|pq|xy).*")
		);

		int part_two = solve(lines, line ->
			line.matches(".*([a-z][a-z]).*\\1.*")
			&& line.matches(".*([a-z])[a-z]\\1.*")
		);

		return String.format("part one: %d, part two: %d", part_one, part_two);
	}

	private int solve(String[] lines, Matcher matcher)
	{
		int count = 0;

		for (String line : lines)
		{
			if (matcher.isMatch(line))
			{
				count++;
			}
		}

		return count;
	}
}
