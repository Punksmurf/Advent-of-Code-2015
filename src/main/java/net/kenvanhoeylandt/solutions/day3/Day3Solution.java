package net.kenvanhoeylandt.solutions.day3;

import bolts.Task;
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
	protected Task<String> solve(String input) throws Exception
	{
		Direction[] directions = DirectionFactory.create(input);
		Grid grid = new Grid();
		GridDriver driver = new GridDriver(grid, directions);

		driver.drive();

		int count = grid.getVisitCount();

		return Task.forResult(Integer.toString(count));
	}
}
