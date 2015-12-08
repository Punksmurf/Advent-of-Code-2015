package net.kenvanhoeylandt.solutions.day6.logic;

import net.kenvanhoeylandt.solutions.day6.data.Area;

import java.util.Arrays;

public class LightGridPartTwo implements LightGrid
{
	private final int[][] mLights;

	private interface LightProcessor
	{
		int process(int input);
	}

	public LightGridPartTwo()
	{
		mLights = new int[1000][1000];

		for (int i = 0; i < 1000; ++i)
		{
			Arrays.fill(mLights[i], 0);
		}
	}

	@Override
	public void turnOn(Area area)
	{
		process(area, input -> input + 1);
	}

	@Override
	public void turnOff(Area area)
	{
		process(area, input -> Math.max(0, input - 1));
	}

	@Override
	public void toggle(Area area)
	{
		process(area, input -> input + 2);
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

	public int getTotalBrightness()
	{
		int brightness = 0;

		for (int x = 0; x < mLights.length; ++x)
		{
			for (int y = 0; y < mLights[x].length; ++y)
			{
				brightness += mLights[x][y];
			}
		}

		return brightness;
	}
}
