package net.kenvanhoeylandt.solutions.day2.data;

public class Box
{
	private final int mLength;
	private final int mWidth;
	private final int mHeight;

	public Box(int length, int width, int height)
	{
		mLength = length;
		mWidth = width;
		mHeight = height;
	}

	public int getLength()
	{
		return mLength;
	}

	public int getWidth()
	{
		return mWidth;
	}

	public int getHeight()
	{
		return mHeight;
	}
}

