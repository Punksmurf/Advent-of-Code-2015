package net.kenvanhoeylandt.solutions.day8;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;

public class Day8Solution extends Solution
{
	public Day8Solution()
	{
		super(8);
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{
		String[] lines = input.split("\n");

		int original_size = 0;

		int code_size = 0;
		int memory_size = 0;

		for (String line : lines)
		{
			if (!line.startsWith("\"") || !line.endsWith("\""))
			{
				throw new RuntimeException("Improper string");
			}

			original_size += line.length();
			memory_size += toMemoryString(line).length();
			code_size += toCodeString(line).length();
		}

		String result = String.format("Original size: %d, memory size: %d, result 1: %d, result 2: %d", original_size, memory_size, original_size - memory_size, code_size - original_size);

		return Task.forResult(result);
	}

	private String toMemoryString(String line)
	{
		// work on a copy, trim the enclosing quote marks
		String copy = line.substring(1, line.length() - 1);
		// replace all used escape sequences with a one character string ( \x00, \", \\; in regex: \\x\d{2} | \\\" | \\\\ )

		// replace \" (regex \\\") with "
		//copy = copy.replaceAll("\\\\\\\"", "\"");

		// replace \\ (regex \\\\) with '
		//copy = copy.replaceAll("\\\\\\\\", "\\");

		// replace \x00 (where 00 is a hex digit) (regex \\x[0-9a-fA-F]{2}) with ? (no need to actually translate to the real char)
		//copy = copy.replaceAll("\\\\x[0-9a-fA-F]{2}", "?");

		// or just do it all at once, because why not
		copy = copy.replaceAll("\\\\x[0-9a-fA-F]{2}|\\\\\\\"|\\\\\\\\", "?");

		return copy;
	}

	private String toCodeString(String line)
	{
		// treat every " and \ as strings which need to be prefixed by a \"
		// then wrap the whole thing in "

		String copy = line.replaceAll("(\\\"|\\\\)", "\\\\$1");
		copy = "\"" + copy + "\"";
		return copy;
	}
}
