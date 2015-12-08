package net.kenvanhoeylandt.solutions.day6.data;

public class Area
{
	private final int mFromX;

	private final int mFromY;

	private final int mToX;

	private final int mToY;

	public Area(int fromX, int fromY, int toX, int toY)
	{
		mFromX = fromX;
		mFromY = fromY;
		mToX = toX;
		mToY = toY;
	}

	public int getFromX()
	{
		return mFromX;
	}

	public int getFromY()
	{
		return mFromY;
	}

	public int getToX()
	{
		return mToX;
	}

	public int getToY()
	{
		return mToY;
	}
}
