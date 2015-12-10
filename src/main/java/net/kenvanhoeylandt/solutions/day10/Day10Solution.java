package net.kenvanhoeylandt.solutions.day10;

import net.kenvanhoeylandt.regex.MatchFinder;
import net.kenvanhoeylandt.solutions.Solution;

public class Day10Solution extends Solution
{
	public Day10Solution()
	{
		super(10);
	}

	@Override
	protected Object solve(String data) throws Exception
	{
		int part_one = StringIterator.iterate(data, 40, this::convert).length();
		int part_two = StringIterator.iterate(data, 50, this::convert).length();

		return String.format("part one: %d, part two: %d", part_one, part_two);
	}

	private String convert(String input)
	{
		StringBuilder output = new StringBuilder();

		MatchFinder.find("(\\d)\\1*", input, (from, to) ->
		{
			String token = input.substring(from, to);

			int count = token.length();
			char character = token.charAt(0);

			output.append(Integer.toString(count));
			output.append(character);
		});

		return output.toString();
	}
}
