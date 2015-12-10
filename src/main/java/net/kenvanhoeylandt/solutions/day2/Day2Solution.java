package net.kenvanhoeylandt.solutions.day2;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;

public class Day2Solution extends Solution
{
	public Day2Solution()
	{
		super(2);
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{
		String[] packages = input.split("\n");

		int area = 0;
		int bow_length = 0;

		for (String package_description : packages)
		{
			Box box = Box.createFromString(package_description);
			area += box.measureWrappingPaper();
			bow_length += box.measureBowLength();
		}

		return Task.forResult(String.format("The elves need %d sq feet of wrapping paper and %d feet of ribbon.", area, bow_length));
	}

}
