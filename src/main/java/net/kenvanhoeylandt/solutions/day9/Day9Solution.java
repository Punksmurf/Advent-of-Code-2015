package net.kenvanhoeylandt.solutions.day9;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;

public class Day9Solution extends Solution
{
	public Day9Solution()
	{
		super(9);
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{
		String[] roads = input.split("\n");

		RouteManager route_manager = new RouteManager();
		route_manager.initialize(roads);

		TomTom tom_tom = new TomTom(route_manager);
		tom_tom.findLongestAndShortestRoutes();

		Route shortest_route = tom_tom.getShortestRoute();
		Route longest_route = tom_tom.getLongestRoute();

		String result = String.format("The shortest route has a length of %s and is %s\nThe longest route has a length of %s and is %s",
				shortest_route.getLength(), shortest_route.getCities().toString(),
				longest_route.getLength(), longest_route.getCities().toString()
		);

		return Task.forResult(result);
	}


}
