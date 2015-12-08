package net.kenvanhoeylandt.solutions.day2;

import net.kenvanhoeylandt.solutions.day2.data.Box;
import net.kenvanhoeylandt.solutions.day2.data.BoxFactory;
import net.kenvanhoeylandt.solutions.day2.data.BoxMath;

import java.util.List;

public class Day2Solution extends net.kenvanhoeylandt.solutions.Solution
{
	public Day2Solution()
	{
		super(2);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		String[] lines = input.split("\n");

		List<Box> box_list = BoxFactory.create(lines);

		int paper_needed = BoxMath.calculatePaperNeeded(box_list);
		int ribbon_needed = BoxMath.calculateRibbonNeeded(box_list);

		return String.format("%d paper needed and %d ribbon needed", paper_needed, ribbon_needed);
	}
}
