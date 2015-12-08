package net.kenvanhoeylandt.solutions.day3;

import net.kenvanhoeylandt.exceptions.InputParsingException;
import net.kenvanhoeylandt.solutions.Solution;
import net.kenvanhoeylandt.solutions.day3.data.Direction;
import net.kenvanhoeylandt.solutions.day3.data.DirectionFactory;
import net.kenvanhoeylandt.solutions.day3.data.Grid;
import net.kenvanhoeylandt.solutions.day3.logic.GridDriver;

public class Day3Solution extends Solution
{
	public Day3Solution()
	{
		super(3);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		int part_one_result = solvePartOne(input);
		int part_two_result = solvePartTwo(input);

		return String.format("part one: %d, part two: %d", part_one_result, part_two_result);
	}

	private int solvePartOne(String input) throws InputParsingException
	{
		Direction[] directions = DirectionFactory.create(input);
		Grid grid = new Grid();
		GridDriver driver = new GridDriver(grid, directions);

		driver.drive();

		return grid.getVisitCount();
	}

	private int solvePartTwo(String input) throws InputParsingException
	{
		Direction[] directions = DirectionFactory.create(input);

		Direction[] santa_directions = new Direction[directions.length / 2];
		Direction[] robosanta_directions = new Direction[directions.length / 2];

		for (int i = 0; i < directions.length; i += 2)
		{
			santa_directions[i/2] = directions[i];
		}

		for (int i = 1; i < directions.length; i += 2)
		{
			robosanta_directions[i/2] = directions[i];
		}

		Grid grid = new Grid();

		GridDriver santa_driver = new GridDriver(grid, santa_directions);
		GridDriver robosanta_driver = new GridDriver(grid, robosanta_directions);

		santa_driver.drive();
		robosanta_driver.drive();

		return grid.getVisitCount();
	}
}
