package net.kenvanhoeylandt.solutions.day14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RidiculousMatch
{
	private final List<Reindeer> mReindeer = new ArrayList<>();

	public RidiculousMatch(String[] reindeer_descriptions)
	{
		for (String reinder_description : reindeer_descriptions)
		{
			mReindeer.add(new Reindeer(reinder_description));
		}
	}

	public List<Reindeer> go(int runTime)
	{
		List<Reindeer> fastest_reindeer = new ArrayList<>();

		for (int time = 1; time <= runTime; time++)
		{
			int longest_distance = 0;

			for (Reindeer reindeer : mReindeer)
			{
				int distance = reindeer.getDistanceForTime(time);
				if (distance > longest_distance)
				{
					longest_distance = distance;
					fastest_reindeer.clear();
				}

				if (distance >= longest_distance)
				{
					fastest_reindeer.add(reindeer);
				}
			}

			fastest_reindeer.forEach(Reindeer::awardPoint);
		}

		return fastest_reindeer;
	}

	public List<Reindeer> getContestants()
	{
		return mReindeer;
	}

	public int getHighScore()
	{
		Collections.sort(mReindeer);
		return mReindeer.get(0).getPoints();
	}
}
