package net.kenvanhoeylandt.solutions.day11;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecurityRequirements
{
	/**
	 * "Passwords must include one increasing straight of at least three letters,
	 * like abc, bcd, cde, and so on, up to xyz.
	 * They cannot skip letters; abd doesn't count."
	 */
	public static boolean isStraightMatch(String input)
	{
		for (int i = 0; i + 3 < input.length(); ++i)
		{
			char character = input.charAt(i);

			if ( input.charAt(i + 1) == (character + 1)
					&& input.charAt(i + 2) == (character + 2) )
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * "Passwords may not contain the letters i, o, or l, as these letters can be mistaken for other characters and are therefore confusing."
	 */
	public static boolean containsForbiddenLetters(String input)
	{
		return input.matches("[iol]");
	}

	/**
	 * "Passwords must contain at least two different, non-overlapping pairs of letters, like aa, bb, or zz."
	 */
	public static boolean containsTwoDifferentNonOverlappingPairs(String password)
	{
		// Lambda-safe value containers
		Character found_character = null;

		Pattern pattern = Pattern.compile("([a-z])\\1+");
		Matcher matcher = pattern.matcher(password);

		// Find all matches
		while (matcher.find())
		{
			char match_character = password.charAt(matcher.start());

			if (found_character == null)
			{
				found_character = match_character;
			}
			else
			{
				if (found_character != match_character)
				{
					return true;
				}
			}
		}

		return false;
	}
}
