package net.kenvanhoeylandt.solutions.day9;

import org.apache.commons.collections4.iterators.PermutationIterator;

import java.util.List;

public class TomTom
{
	private final RouteManager mRouteManager;

	private int mShortestDistance = Integer.MAX_VALUE;
	private Route mShortestRoute = null;

	private int mLongestDistance = 0;
	private Route mLongestRoute = null;

	public TomTom(RouteManager routeManager)
	{
		mRouteManager = routeManager;
	}

	public void findLongestAndShortestRoutes()
	{
		PermutationIterator<String> cities_iterator = new PermutationIterator<>(mRouteManager.getCities());
		while (cities_iterator.hasNext())
		{
			testRoute(cities_iterator.next());
		}
	}

	public Route getLongestRoute()
	{
		return mLongestRoute;
	}

	public Route getShortestRoute()
	{
		return mShortestRoute;
	}

	private void testRoute(List<String> cities)
	{
		Route route = mRouteManager.createRoute(cities);
		int length = route.getLength();
		if (length < mShortestDistance)
		{
			mShortestDistance = length;
			mShortestRoute = route;
		}
		if (length > mLongestDistance)
		{
			mLongestDistance = length;
			mLongestRoute = route;
		}
	}
}
