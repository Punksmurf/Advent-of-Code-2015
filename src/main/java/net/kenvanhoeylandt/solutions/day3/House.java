package net.kenvanhoeylandt.solutions.day3;

import java.util.ArrayList;
import java.util.List;

public class House
{
	private static List<House> sHouses = new ArrayList<>();

	private int mX;
	private int mY;

	private int mPresents = 0;

	public House(int x, int y)
	{
		mX = x;
		mY = y;
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


}
