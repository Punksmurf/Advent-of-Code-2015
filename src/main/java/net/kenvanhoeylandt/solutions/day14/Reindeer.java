package net.kenvanhoeylandt.solutions.day14;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reindeer implements Comparable<Reindeer>
{
	// (?<name>\w+).*?(?<speed>\d+).*?(?<flyTime>\d+).*?(?<restTime>\d+).*?\.
	private static final Pattern sPattern = Pattern.compile("(?<name>\\w+).*?(?<speed>\\d+).*?(?<flyTime>\\d+).*?(?<restTime>\\d+).*?\\.");

	private final String mName;
	private final int mSpeed;
	private final int mFlyTime;
	private final int mRestTime;

	private int mPoints = 0;

	public Reindeer(String description)
	{
		Matcher matcher = sPattern.matcher(description);
		if (matcher.find())
		{
			mName = matcher.group("name");
			mSpeed = Integer.valueOf(matcher.group("speed"));
			mFlyTime = Integer.valueOf(matcher.group("flyTime"));
			mRestTime = Integer.valueOf(matcher.group("restTime"));
		}
		else
		{
			throw new RuntimeException("Cannot create reindeer");
		}
	}

	public String getName()
	{
		return mName;
	}

	public void awardPoint()
	{
		mPoints++;
	}

	public int getPoints()
	{
		return mPoints;
	}

	public int getSpeedForTime(int currentTime)
	{
		int loop_time = mFlyTime + mRestTime;
		currentTime %= loop_time;
		return  currentTime % loop_time < mFlyTime ? mSpeed : 0;
	}

	public int getDistanceForTime(int time)
	{
		int loop_time = mFlyTime + mRestTime;
		int loops = time / loop_time;
		int rest = Math.min(time % loop_time, mFlyTime);

		return loops * mFlyTime * mSpeed + rest * mSpeed;
	}

	@Override
	public String toString()
	{
		return String.format("%s (%d points)", getName(), getPoints());
	}

	// region implements Comparable<Reindeer>

	@Override
	public int compareTo(Reindeer o)
	{
		return o.getPoints() - getPoints();
	}

	// endregion
}
