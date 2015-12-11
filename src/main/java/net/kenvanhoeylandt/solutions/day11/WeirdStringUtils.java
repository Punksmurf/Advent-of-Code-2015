package net.kenvanhoeylandt.solutions.day11;

/**
 * These are not regular String utilities...
 */
public class WeirdStringUtils
{
	/**
	 * Increase the String as if its characters were a base 26 number in the range of [a-z].
	 * @param input the String to increase
	 * @return the increased String
	 */
	public static String increase(String input)
	{
		char[] output = new char[input.length() + 1]; // assure we have a sufficient amount of characters
		output[0] = 0;

		int i;
		boolean carry = true; // carry the letter to the next

		// Go through all the input, and 1 character further (in case we need to carry to the next character)
		for (i = input.length() - 1; i >= -1; --i)
		{
			if (i == -1)
			{
				if (carry)
				{
					output[0] = 'a';
				}

				break;
			}

			char character = input.charAt(i);

			if (carry)
			{
				if (character == 'z')
				{
					output[i + 1] = 'a';
				}
				else
				{
					char new_char = (char)(character + 1);

					output[i + 1] = new_char;

					carry = false;
				}
			}
			else
			{
				output[i + 1] = character;
			}
		}

		if (output[0] != 0)
		{
			return new String(output);
		}
		else // didn't utilize the new character that was allocated
		{
			return new String(output).substring(1);
		}
	}
}
