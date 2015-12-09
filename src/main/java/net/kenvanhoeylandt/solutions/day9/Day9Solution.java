package net.kenvanhoeylandt.solutions.day9;

import net.kenvanhoeylandt.solutions.Solution;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day9Solution extends Solution
{
	public Day9Solution()
	{
		super(9);
	}

	public int distance(Connection[] connections, String from, String to)
	{
		for (Connection connection : connections)
		{
			boolean matches = ( connection.getFrom().equals(from) && connection.getTo().equals(to) )
				|| ( connection.getFrom().equals(to) && connection.getTo().equals(from) );

			if (matches)
			{
				return connection.getDistance();
			}
		}

		throw new RuntimeException("match not found between " + from + " and " + to);
	}

	private int permutatePartOne(@Nullable String fromLocation, List<String> locationsToVisit, Set<String> locationsVisited, Connection[] connections)
	{
		if (locationsToVisit.isEmpty())
		{
			return 0;
		}

		int smallest_distance = Integer.MAX_VALUE;

		for (int i = 0; i < locationsToVisit.size(); ++i)
		{
			String visit_location  = locationsToVisit.get(i);

			List<String> to_visit = new ArrayList<>(locationsToVisit);
			to_visit.remove(i);

			int distance;

			// Starting locations
			if (fromLocation == null)
			{
				String from_location = visit_location;
				HashSet<String> locations_visited = new HashSet<>(locationsVisited);
				locations_visited.add(from_location);

				distance = permutatePartOne(from_location, to_visit, locations_visited, connections);
			}
			else // recursive locations
			{
				int current_distance = distance(connections, fromLocation, visit_location);

				Set<String> locations_visited = new HashSet<>(locationsVisited);
				locations_visited.add(visit_location);

				distance = current_distance + permutatePartOne(visit_location, to_visit, locations_visited, connections);
			}

			if (distance < smallest_distance)
			{
				smallest_distance = distance;
			}
		}

		return smallest_distance;
	}

	private int permutatePartTwo(@Nullable String fromLocation, List<String> locationsToVisit, Set<String> locationsVisited, Connection[] connections)
	{
		if (locationsToVisit.isEmpty())
		{
			return 0;
		}

		int smallest_distance = Integer.MIN_VALUE;

		for (int i = 0; i < locationsToVisit.size(); ++i)
		{
			String visit_location  = locationsToVisit.get(i);

			List<String> to_visit = new ArrayList<>(locationsToVisit);
			to_visit.remove(i);

			int distance;

			// Starting locations
			if (fromLocation == null)
			{
				String from_location = visit_location;
				HashSet<String> locations_visited = new HashSet<>(locationsVisited);
				locations_visited.add(from_location);

				distance = permutatePartTwo(from_location, to_visit, locations_visited, connections);
			}
			else // recursive locations
			{
				int current_distance = distance(connections, fromLocation, visit_location);

				Set<String> locations_visited = new HashSet<>(locationsVisited);
				locations_visited.add(visit_location);

				distance = current_distance + permutatePartTwo(visit_location, to_visit, locations_visited, connections);
			}

			if (distance > smallest_distance)
			{
				smallest_distance = distance;
			}
		}

		return smallest_distance;
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		String[] inputs = input.split("\n");

		Connection[] connections = ConnectionFactory.create(inputs);

		HashSet<String> cities = new HashSet<>();
		List<String> locations = new ArrayList<>();

		for (Connection connection : connections)
		{
			if (cities.add(connection.getFrom()))
			{
				locations.add(connection.getFrom());
			}

			if (cities.add(connection.getTo()))
			{
				locations.add(connection.getTo());
			}
		}

		int smallest = permutatePartOne(null, locations, new HashSet<>(), connections);
		int largest = permutatePartTwo(null, locations, new HashSet<>(), connections);

		return String.format("smallest: %d, largest: %d", smallest, largest);
	}
}
