package net.kenvanhoeylandt.solutions.day3;

import java.util.ArrayList;
import java.util.List;

public class GridCity
{
	private List<House> mHouses = new ArrayList<>();

	public House getHouseFor(int x, int y)
	{
		for (House house : mHouses)
		{
			if (house.getX() == x && house.getY() == y)
			{
				return house;
			}
		}

		House house = new House(x, y);
		mHouses.add(house);

		return house;
	}

	public int getTotalHouses()
	{
		return mHouses.size();
	}

	public int getTotalPresents()
	{
		return mHouses.stream().mapToInt(House::getNumPresents).sum();
	}
}
