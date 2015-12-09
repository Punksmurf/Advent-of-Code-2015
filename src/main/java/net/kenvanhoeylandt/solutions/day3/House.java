package net.kenvanhoeylandt.solutions.day3;

import java.util.ArrayList;
import java.util.List;

public class House
{
	private static List<House> sHouses = new ArrayList<>();

	private int mX;
	private int mY;

	private int mPresents = 0;

	private House(int x, int y)
	{
		mX = x;
		mY = y;
		sHouses.add(this);
	}

	public int getX()
	{
		return mX;
	}

	public int getY()
	{
		return mY;
	}

	/**
	 * Not required, but fun to keep track of :)
	 */
	public void addPresent()
	{
		mPresents++;
	}

	public int getNumPresents()
	{
		return mPresents;
	}


	public static House getHouseFor(int x, int y)
	{
		for (House house : sHouses)
		{
			if (house.getX() == x && house.getY() == y)
			{
				return house;
			}
		}

		return new House(x, y);
	}

	public static int getTotalHouses()
	{
		return sHouses.size();
	}

	public static int getTotalPresents()
	{
		int presents = 0;
		for (House house : sHouses)
		{
			presents += house.getNumPresents();
		}
		return presents;
	}

	public static void reset()
	{
		sHouses.clear();
	}

}
