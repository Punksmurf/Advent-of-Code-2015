package net.kenvanhoeylandt.solutions.day14;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;

import java.util.List;

public class Day14Solution extends Solution
{
	private static final int sRunTime = 2503;

	public Day14Solution()
	{
		super(14);
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{

		RidiculousMatch match = new RidiculousMatch(input.split("\n"));
		List<Reindeer> fastest_reindeer = match.go(sRunTime);

		String result = String.format("The race was won by %s, they flew %d km in %d seconds!\n" +
				"The highest score was %d.\n" +
				"All scores are: %s.",
				fastest_reindeer, fastest_reindeer.get(0).getDistanceForTime(sRunTime), sRunTime,
				match.getHighScore(),
				match.getContestants()
		);

		return Task.forResult(result);
	}

}
