package net.kenvanhoeylandt.solutions.day9;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Road
{
	// (\w*)\s*to\s*(\w*)\s*=\s*(\d*)
	private static final Pattern sPattern = Pattern.compile("(?<from>\\w*)\\s*to\\s*(?<to>\\w*)\\s*=\\s*(?<distance>\\d*)");

	private final String mCityA;
	private final String mCityB;
	private final int mDistance;

	public static Road create(String route)
	{
		Matcher matcher = sPattern.matcher(route);
		if (matcher.find())
		{
			return new Road(matcher.group("from"), matcher.group("to"), Integer.valueOf(matcher.group("distance")));
		}
		else
		{
			throw new RuntimeException("Invalid route");
		}
	}

	public Road(String from, String to, int distance)
	{
		mCityA = from;
		mCityB = to;
		mDistance = distance;
	}

	public boolean isRoadBetween(String cityA, String cityB)
	{
		return (
			mCityA.equals(cityA) &&
			mCityB.equals(cityB)
		) || (
			mCityA.equals(cityB) &&
			mCityB.equals(cityA)
		);
	}

	public String getCityA()
	{
		return mCityA;
	}

	public String getCityB()
	{
		return mCityB;
	}

	public int getDistance()
	{
		return mDistance;
	}
}
