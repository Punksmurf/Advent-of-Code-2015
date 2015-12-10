package net.kenvanhoeylandt.solutions.day3;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;

public class Day3Solution extends Solution
{
	public Day3Solution()
	{
		super(3);
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{
		String result = solveFirstAssignment(input) + '\n' + solveSecondAssignment(input);

		return Task.forResult(result);
	}


	private String solveFirstAssignment(String input)
	{
		House.reset();

		Santa santa = new Santa();

		House.getHouseFor(0, 0).addPresent();

		for (int i = 0; i < input.length(); i++)
		{
			char direction = input.charAt(i);
			santa.move(direction);
		}

		return String.format("Santa visited %d houses for a total of %d gifts", House.getTotalHouses(), House.getTotalPresents());
	}

	private String solveSecondAssignment(String input)
	{
		House.reset();

		Santa santa = new Santa();
		Santa robot = new Santa();

		House.getHouseFor(0, 0).addPresent();
		House.getHouseFor(0, 0).addPresent();

		for (int i = 0; i < input.length(); i += 2)
		{
			char direction = input.charAt(i);
			santa.move(direction);

			if (i < input.length() - 1)
			{
				direction = input.charAt(i+1);
				robot.move(direction);
			}
		}

		return String.format("Santa and RoboSanta visited %d houses for a total of %d gifts", House.getTotalHouses(), House.getTotalPresents());
	}

}
