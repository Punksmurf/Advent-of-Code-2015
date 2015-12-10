package net.kenvanhoeylandt.solutions.day10;

public interface StringIterator
{
	String iterate(String input);

	/**
	 * Input gets converted to output by the provided Interface.
	 * That output is then used as input again for the given amount of times.
	 *
	 * @param input input value
	 * @param count iteration count
	 * @param iterator processing logic
	 * @return final output after iterations
	 */
	static String iterate(String input, int count, StringIterator iterator)
	{
		for (int i = 0; i < count; ++i)
		{
			input = iterator.iterate(input);
		}

		return input;
	}
}