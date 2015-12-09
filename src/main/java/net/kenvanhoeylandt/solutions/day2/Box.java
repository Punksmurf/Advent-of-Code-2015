package net.kenvanhoeylandt.solutions.day2;

import java.util.Arrays;

public class Box
{
	private final int[] mDimensions;
	private final int[] mSides;

	public Box(int[] dimensions)
	{
		if (dimensions.length != 3)
		{
			throw new RuntimeException("Invalid box");
		}

		mDimensions = dimensions;
		mSides = calculateSides(mDimensions);

		Arrays.sort(mDimensions);
		Arrays.sort(mSides);
	}

	public int measureWrappingPaper()
	{
		int area = 0;
		for (int side : mSides)
		{
			area += side * 2;
		}

		area += mSides[0];

		return area;
	}

	public int measureBowLength()
	{
		return mDimensions[0] + mDimensions[0] + mDimensions[1] + mDimensions[1] + calculateVolume(mDimensions);
	}

	public static Box createFromString(String description)
	{
		String[] lengths = description.split("x");
		return new Box(new int[]{
				Integer.parseInt(lengths[0]),
				Integer.parseInt(lengths[1]),
				Integer.parseInt(lengths[2])}
		);
	}

	public static int[] calculateSides(int[] dimensions)
	{
		return new int[]{
				dimensions[0] * dimensions[1],
				dimensions[0] * dimensions[2],
				dimensions[1] * dimensions[2],
		};
	}

	public static int calculateVolume(int[] dimensions)
	{
		return dimensions[0] * dimensions[1] * dimensions[2];
	}


}
