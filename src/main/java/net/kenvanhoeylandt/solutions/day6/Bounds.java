package net.kenvanhoeylandt.solutions.day6;

public class Bounds
{
	private final Coords mTopLeft;
	private final Coords mBottomRight;

	public Bounds(int x1, int y1, int x2, int y2)
	{
		mTopLeft = new Coords(x1, y1);
		mBottomRight = new Coords(x2, y2);
	}

	public Bounds(Coords topLeft, Coords bottomRight)
	{
		mTopLeft = topLeft;
		mBottomRight = bottomRight;
	}

	public Coords getTopLeft()
	{
		return mTopLeft;
	}

	public Coords getBottomRight()
	{
		return mBottomRight;
	}
}
