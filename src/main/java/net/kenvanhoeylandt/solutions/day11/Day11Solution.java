package net.kenvanhoeylandt.solutions.day11;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;

public class Day11Solution extends Solution
{

	private static final char[] sCharacters = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	private static final int sAlphabetStart = 97;
	private static final int sAlphabetLength = 26;

	public Day11Solution()
	{
		super(11);
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{
		input = input.trim();

		int tries = 0;
		while (!isValid(input))
		{
			tries++;
			input = getNextPassword(input);
		}

		String result = String.format("Santa's next valid password is: %s (but don't tell anyone).\nAs an aside, it took the elves %d tries to find it.", input, tries);


		return Task.forResult(result);
	}

	public String getNextPassword(String password)
	{
		char[] password_chars = password.toCharArray();

		for (int i = password.length() - 1; i >= 0; i--)
		{
			password_chars[i]++;
			if (password_chars[i] >= sAlphabetStart + sAlphabetLength)
			{
				password_chars[i] = sAlphabetStart;
			}
			else
			{
				break;
			}
		}

		String new_password = new String(password_chars);

		//return isValid(new_password) ? new_password : getNextPassword(new_password);
		return new_password;
	}

	public boolean isValid(String password)
	{
		return
			hasIncreasingStraight(password) &&
			!hasConfusingCharacters(password) &&
			hasTwoDifferentPairs(password);
	}

	public boolean hasIncreasingStraight(String password)
	{
		// Passwords must include one increasing straight of at least three letters, like abc, bcd, cde, and so on, up
		// to xyz. They cannot skip letters; abd doesn't count.

		boolean has_straight = false;
		int straight_length = 0;
		char last_char = 0;

		for (int i = 0; i < password.length(); i++)
		{
			char c = password.charAt(i);
			if (c == last_char + 1)
			{
				straight_length++;

				if (straight_length >= 2)
				{
					has_straight = true;
					break;
				}
			}
			else
			{
				straight_length = 0;
			}
			last_char = c;
		}

		return has_straight;
	}

	public boolean hasConfusingCharacters(String password)
	{
		// Passwords may not contain the letters i, o, or l, as these letters can be mistaken for other characters and
		// are therefore confusing.
		return password.contains("i") || password.contains("o") || password.contains("l");
	}

	public boolean hasTwoDifferentPairs(String password)
	{
		// Passwords must contain at least two different, non-overlapping pairs of letters, like aa, bb, or zz.
		return true;
	}
}
