package net.kenvanhoeylandt.solutions.day8;

import net.kenvanhoeylandt.solutions.Solution;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8Solution extends Solution
{
	public Day8Solution()
	{
		super(8);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		String[] inputs = input.split("\n");

		// Character counts
		int total_original = 0;
		int total_decoded = 0;
		int total_encoded = 0;

		// Iterate each input
		for (String value : inputs)
		{
			total_original += value.length();
			total_decoded += decode(value).length();
			total_encoded += encode(value).length();
		}

		// Calculate results
		int first_part = total_original - total_decoded;
		int second_part = total_encoded - total_original;

		return String.format("first part: %d, second part: %d", first_part, second_part);
	}

	private String decode(String input)
	{
		// Remove start and ending quote
		String no_quotes_input = input.substring(1, input.length() - 1);
		// Convert \" to "
		String cleaned = no_quotes_input.replaceAll("\\\\\"", "\"");
		// Convert \\ to \
		cleaned = cleaned.replaceAll("\\\\{2}", "\\\\");
		// convert hex \x?? to character
		return decodeHexCharacters(cleaned);
	}

	private String decodeHexCharacters(String input)
	{
		Pattern pattern = Pattern.compile("\\\\x[0-9abcdef]{2}");
		Matcher matcher = pattern.matcher(input);

		StringBuilder output = new StringBuilder();

		int last_match = 0;

		// Find all matches
		while (matcher.find())
		{
			// If we skipped any non-matching characters, just copy them to the output
			if (matcher.start() > last_match)
			{
				String skipped = input.substring(last_match, matcher.start());
				output.append(skipped);
			}

			// Get the character from the input
			String hex_part = input.substring(matcher.start(), matcher.end());
			String hex_string = hex_part.substring(2);
			int ascii_char_byte = Integer.parseInt(hex_string, 16);

			String character_string = Character.toString((char)ascii_char_byte);

			// Append the converted character to the output
			output.append(character_string);

			// Store the matching end position for later use
			last_match = matcher.end();
		}

		// If the last match is not at the end of the string,
		// copy the remainder of the string to the outpu
		if (last_match < input.length())
		{
			String remainder = input.substring(last_match);
			output.append(remainder);
		}

		return output.toString();
	}

	private String encode(String input)
	{
		StringBuilder result = new StringBuilder();

		result.append('\"');

		// Replace characters
		input.chars()
			.mapToObj(i -> (char)i) // chars() returns an IntStream (which is illogical) so we have to map it to characters
			.forEach(c ->
			{
				switch (c)
				{
					case '"':
						result.append("\\\"");
						break;

					case '\\':
						result.append("\\\\");
						break;

					default:
						result.append(c);
				}
			});

		result.append('\"');

		return result.toString();
	}
}
