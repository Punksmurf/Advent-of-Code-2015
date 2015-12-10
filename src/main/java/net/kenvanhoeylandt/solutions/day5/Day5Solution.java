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
	public Task<Object> solve(String input) throws Exception
	{
		String[] words = input.split("\n");

		int nice = 0;
		int really_nice = 0;

		for (String word : words)
		{
			if (isNice(word))
			{
				nice++;
			}

			if (isReallyNice(word))
			{
				really_nice++;
			}
		}

		String result = String.format("The list contains %d nice words and %d really nice words.", nice, really_nice);

		return Task.forResult(result);
	}

	private boolean isNice(String word)
	{
		return hasThreeVowels(word) && hasDoubleCharacters(word) && !hasEvilString(word);
	}

	private boolean hasThreeVowels(String word)
	{
		int vowels = 0;
		for (char currentChar : word.toCharArray())
		{
			switch (currentChar)
			{
				case 'a':
				case 'e':
				case 'i':
				case 'o':
				case 'u':
					vowels++;
					break;
			}
		}
		return vowels >= 3;
	}

	private boolean hasDoubleCharacters(String word)
	{
		//assume 0x00 isn't a valid word character, so no need to skip the first char
		char lastChar = 0x00;
		for (char currentChar : word.toCharArray())
		{
			if (lastChar == currentChar)
			{
				return true;
			}
			lastChar = currentChar;
		}
		return false;
	}

	private boolean hasEvilString(String word)
	{
		return word.contains("ab") || word.contains("cd") || word.contains("pq") || word.contains("xy");
	}




	private boolean isReallyNice(String word)
	{
		return hasDoublePair(word) && hasRepeatWithOneBetween(word);
	}

	private boolean hasDoublePair(String word)
	{
		// matches pattern (\w\w)\w*\1; padded with \w* because it needs to match the whole word
		return word.matches("\\w*(\\w\\w)\\w*\\1\\w*");
	}

	private boolean hasRepeatWithOneBetween(String word)
	{
		// matches pattern (\w)\w\1
		return word.matches("\\w*(\\w)\\w\\1\\w*");
	}

}
