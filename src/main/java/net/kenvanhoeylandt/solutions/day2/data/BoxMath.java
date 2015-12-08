package net.kenvanhoeylandt.solutions.day2.data;

import java.util.Arrays;
import java.util.List;

public class BoxMath
{
	public static int calculatePaperNeeded(Box box)
	{
		int size_a = box.getHeight() * box.getWidth();
		int size_b = box.getHeight() * box.getLength();
		int size_c = box.getWidth() * box.getLength();

		int extra = Math.min(Math.min(size_a, size_b), size_c);

		return (2 * size_a) + (2 * size_b) + (2 * size_c) + extra;
	}

	public static int calculatePaperNeeded(List<Box> boxList)
	{
		int paper_needed = 0;

		for (Box box : boxList)
		{
			paper_needed += calculatePaperNeeded(box);
		}

		return paper_needed;
	}

	public static int calculateRibbonNeeded(List<Box> boxList)
	{
		int ribbon_needed = 0;

		for (Box box : boxList)
		{
			ribbon_needed += calculateRibbonNeeded(box);
		}

		return ribbon_needed;
	}

	public static int calculateRibbonNeeded(Box box)
	{
		int[] dimensions = new int[]
		{
			box.getHeight(),
			box.getWidth(),
			box.getLength()
		};

		Arrays.sort(dimensions);

		return (dimensions[0] * 2)
			+ (dimensions[1] * 2)
			+ (box.getWidth() * box.getHeight() * box.getLength());
	}
}
