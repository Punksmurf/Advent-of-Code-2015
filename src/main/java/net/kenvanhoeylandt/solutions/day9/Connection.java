package net.kenvanhoeylandt.solutions.day9;

public class Connection
{
	private final String mFrom;

	private final String mTo;

	private final int mDistance;

	public Connection(String from, String to, int distance)
	{
		mFrom = from;
		mTo = to;
		mDistance = distance;
	}

	public String getFrom()
	{
		return mFrom;
	}

	public String getTo()
	{
		return mTo;
	}

	public int getDistance()
	{
		return mDistance;
	}
}
