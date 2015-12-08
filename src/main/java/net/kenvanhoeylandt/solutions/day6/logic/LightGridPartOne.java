package net.kenvanhoeylandt.solutions.day6.logic;

import net.kenvanhoeylandt.solutions.day6.data.Area;

import java.util.Arrays;

public class LightGridPartOne implements LightGrid
{
	private final boolean[][] mLights;

	private interface LightProcessor
	{
		boolean process(boolean input);
	}

	public LightGridPartOne()
	{
		mLights = new boolean[1000][1000];

		for (int i = 0; i < 1000; ++i)
		{
			Arrays.fill(mLights[i], false);
		}
	}

	@Override
	public void turnOn(Area area)
	{
		process(area, input -> true);
	}

	@Override
	public void turnOff(Area area)
	{
		process(area, input -> false);
	}

	@Override
	public void toggle(Area area)
	{
		process(area, input -> !input);
	}

	public void process(Area area, LightProcessor processor)
	{
		for (int x = area.getFromX(); x <= area.getToX(); ++x)
		{
			for (int y = area.getFromY(); y <= area.getToY(); ++y)
			{
				mLights[x][y] = processor.process(mLights[x][y]);
			}
		}
	}

	public int getLightsOnCount()
	{
		int count = 0;

		for (int x = 0; x < mLights.length; ++x)
		{
			for (int y = 0; y < mLights[x].length; ++y)
			{
				if (mLights[x][y])
				{
					count++;
				}
			}
		}

		return count;
	}
}
