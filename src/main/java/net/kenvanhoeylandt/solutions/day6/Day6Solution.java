package net.kenvanhoeylandt.solutions.day6;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6Solution extends Solution
{
	private boolean[][] mBinaryLights = new boolean[1000][1000];
	private int[][] mDimmingLights = new int[1000][1000];

	public Day6Solution()
	{
		super(6);
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{
		String[] instructions = input.split("\n");

		for (String instruction : instructions)
		{
			execute(instruction);
		}

		String result = String.format("There are %d lights on in the binary grid; the strength of the dimming grid is %d!", countBinaryLights(), countDimmingStrength());

		return Task.forResult(result);
	}

	private void execute(String instruction)
	{
		Action action = getAction(instruction);
		Bounds bounds = getBounds(instruction);

		int x_start = bounds.getTopLeft().getX();
		int x_end = bounds.getBottomRight().getX();

		int y_start = bounds.getTopLeft().getY();
		int y_end = bounds.getBottomRight().getY();

		for (int x = x_start; x <= x_end; x++)
		{
			for (int y = y_start; y <= y_end; y++)
			{
				switch (action)
				{
					case TURN_ON:
						mBinaryLights[x][y] = true;
						mDimmingLights[x][y]++;
						break;
					case TURN_OFF:
						mBinaryLights[x][y] = false;
						mDimmingLights[x][y] = Math.max(0, mDimmingLights[x][y] - 1);
						break;
					case TOGGLE:
						mBinaryLights[x][y] = !mBinaryLights[x][y];
						mDimmingLights[x][y] += 2;
						break;
				}
			}
		}
	}


	private int countBinaryLights()
	{
		int count = 0;
		for (int x = 0; x < mBinaryLights.length; x++)
		{
			for (int y = 0; y < mBinaryLights[x].length; y++)
			{
				if (mBinaryLights[x][y])
				{
					count++;
				}
			}
		}
		return count;
	}

	private int countDimmingStrength()
	{
		int strength = 0;
		for (int x = 0; x < mDimmingLights.length; x++)
		{
			for (int y = 0; y < mDimmingLights[x].length; y++)
			{
				strength += mDimmingLights[x][y];
			}
		}
		return strength;

	}

	private Action getAction(String instruction)
	{
		if (instruction.startsWith("turn on"))
		{
			return Action.TURN_ON;
		}
		else if (instruction.startsWith("turn off"))
		{
			return Action.TURN_OFF;
		}
		else if (instruction.startsWith("toggle"))
		{
			return Action.TOGGLE;
		}
		else
		{
			throw new RuntimeException("Invalid input");
		}
	}

	private Bounds getBounds(String instruction)
	{
		// (\d+),(\d+).*?(\d+),(\d+)

		String regex = "(?<x1>\\d+),(?<y1>\\d+).*?(?<x2>\\d+),(?<y2>\\d+)";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(instruction);
		matcher.find();

		return new Bounds(
				Integer.parseInt(matcher.group("x1")),
				Integer.parseInt(matcher.group("y1")),
				Integer.parseInt(matcher.group("x2")),
				Integer.parseInt(matcher.group("y2"))
		);
	}

}
