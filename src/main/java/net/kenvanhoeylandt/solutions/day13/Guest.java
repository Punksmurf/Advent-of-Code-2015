package net.kenvanhoeylandt.solutions.day13;

import java.util.HashMap;
import java.util.Map;

public class Guest
{
	private final String mName;
	private Map<String, Integer> mRelations = new HashMap<>();

	public Guest(String name)
	{
		mName = name;
	}

	public String getName()
	{
		return mName;
	}

	public int getRelationHappiness(String guest)
	{
		return mRelations.containsKey(guest) ? mRelations.get(guest) : 0;
	}

	public int getRelationHappiness(String guestLeft, String guestRight)
	{
		return getRelationHappiness(guestLeft) + getRelationHappiness(guestRight);
	}

	public void setRelationHappiness(String guest, int happiness)
	{
		mRelations.put(guest, happiness);
	}
}
