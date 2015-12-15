package net.kenvanhoeylandt.solutions.day9;

import java.util.ArrayList;
import java.util.List;

public class RouteManager
{
	private List<String> mCities = new ArrayList<>();
	private List<Road> mRoads = new ArrayList<>();
	//private List<Route> mRoutes = new ArrayList<>();

	public void initialize(String[] roads)
	{
		for (String road_description : roads)
		{
			Road road = Road.create(road_description);
			mRoads.add(road);

			if (!mCities.contains(road.getCityA()))
			{
				mCities.add(road.getCityA());
			}

			if (!mCities.contains(road.getCityB()))
			{
				mCities.add(road.getCityB());
			}
		}
	}

	public Route createRoute(List<String> cities)
	{
		if (cities.size() < 2)
		{
			throw new RuntimeException("Insufficient cities to be a route");
		}

		int length = 0;
		for (int i = 0; i < cities.size() - 1; i++)
		{
			length += findDistanceBetweenCities(cities.get(i), cities.get(i + 1));
		}

		return new Route(cities, length);
	}

	public int findDistanceBetweenCities(String cityA, String cityB)
	{
		for (Road road : mRoads)
		{
			if (road.isRoadBetween(cityA, cityB))
			{
				return road.getDistance();
			}
		}

		throw new RuntimeException(String.format("Unable to find route between cities %s and %s", cityA, cityB));
	}

	public List<String> getCities()
	{
		return mCities;
	}
}
