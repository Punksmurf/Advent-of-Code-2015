package net.kenvanhoeylandt.solutions.day10;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;

import java.util.stream.IntStream;

public class Day10Solution extends Solution
{
	//Source: https://oeis.org/A014715/constant
	private static final double sConwaysConstant = 1.30357726903429639125709911215255189073070250465940487575486139062855088785246155712681576686442522555;

	public Day10Solution()
	{
		super(10);
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{
		input = input.trim();

		int length_initial = input.length();
		int length_40 = 0;

		for (int i = 0; i < 50; i++)
		{
			input = lookAndSay(input);
			if (i == 39)
			{
				length_40 = input.length();
			}
		}

		String result = String.format(
				"I looked and said, therefore I know 40 iterations has length: %d; Conway was smarter and calculated: %d (but he probably got it wrong, though).\n" +
				"I looked and said, therefore I know 50 iterations has length: %d; Conway was smarter and calculated: %d (but he probably got it wrong, though).\n" +
				"When he calculated based on the length of the 40 iterations result he still got it wrong: %d :(",
				length_40, (int)(length_initial * Math.pow(sConwaysConstant, 40)),
				input.length(), (int)(length_initial * Math.pow(sConwaysConstant, 50)), (int)(length_40 * Math.pow(sConwaysConstant, 10)));

		return Task.forResult(result);
	}

	private String lookAndSay(String input)
	{
		char[] input_chars = input.toCharArray();
		StringBuilder output = new StringBuilder();

		char lastChar = input_chars[0];
		int lastCharCnt = 0;
		for (char thisChar : input_chars)
		{
			if (thisChar == lastChar)
			{
				lastCharCnt++;
			}
			else
			{
				output.append(lastCharCnt).append(lastChar);
				lastCharCnt = 1;
				lastChar = thisChar;
			}
		}
		output.append(lastCharCnt).append(lastChar);

		return output.toString();
	}


}
