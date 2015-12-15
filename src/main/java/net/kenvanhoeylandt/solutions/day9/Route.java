package net.kenvanhoeylandt.solutions.day9;

import java.util.List;

public class Route
{
	private final List<String> mCities;
	private final int mLength;

	public Route(List<String> cities, int length)
	{
		mCities = cities;
		mLength = length;
	}

	public int getLength()
	{
		return mLength;
	}

	public List<String> getCities()
	{
		return mCities;
	}

	public int indexOf(String city)
	{
		return mCities.indexOf(city);
	}

	public int indexOf(Route route)
	{
		if (route.mLength > mLength)
		{
			return -1;
		}

		for (int i = 0; i <= mLength - route.mLength; i++)
		{
			boolean contains = true;
			for (int j = 0; j < route.mLength; j++)
			{
				String cityMe = mCities.get(i + j);
				String cityOther = route.mCities.get(j);
				if (!cityMe.equals(cityOther))
				{
					contains = false;
					break;
				}
			}
			if (contains)
			{
				return i;
			}
		}

		return -1;
	}
}
