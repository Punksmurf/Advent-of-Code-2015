package net.kenvanhoeylandt.solutions.day2;

import bolts.Task;
import net.kenvanhoeylandt.solutions.day2.data.Box;
import net.kenvanhoeylandt.solutions.day2.data.BoxFactory;
import net.kenvanhoeylandt.solutions.day2.data.BoxMath;

import java.util.List;

public class Solution extends net.kenvanhoeylandt.solutions.Solution
{
	public Solution()
	{
		super(2);
	}

	@Override
	protected Task<String> solve(String input) throws Exception
	{
		String[] lines = input.split("\n");

		List<Box> box_list = BoxFactory.create(lines);

		int paper_needed = BoxMath.calculatePaperNeeded(box_list);

		return Task.forResult(Integer.toString(paper_needed));
	}
}
