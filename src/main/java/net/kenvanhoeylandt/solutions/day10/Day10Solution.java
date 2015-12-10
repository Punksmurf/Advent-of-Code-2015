package net.kenvanhoeylandt.solutions.day10;

import net.kenvanhoeylandt.solutions.Solution;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10Solution extends Solution
{
	public Day10Solution()
	{
		super(10);
	}

	@Override
	protected Object solve(String data) throws Exception
	{
		int part_one = solve(data, 40);
		int part_two = solve(data, 50);

		return String.format("part one: %d, part two: %d", part_one, part_two);
	}

	private int solve(String data, int iterations)
	{
		for (int i = 0; i < iterations; ++i)
		{
			data = convert(data);
		}

		return data.length();
	}

	private String convert(String input)
	{
		Pattern pattern = Pattern.compile("(\\d)\\1*");
		Matcher matcher = pattern.matcher(input);

		StringBuilder output = new StringBuilder();

		// Find all matches
		while (matcher.find())
		{
			String token = input.substring(matcher.start(), matcher.end());

			int count = token.length();
			char character = token.charAt(0);

			output.append(Integer.toString(count));
			output.append(character);
		}

		return output.toString();
	}
}
