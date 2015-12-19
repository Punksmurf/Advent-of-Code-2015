package net.kenvanhoeylandt.solutions.day12;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Day12Solution extends Solution
{
	public Day12Solution()
	{
		super(12);
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{
		int count = 0;
		try
		{
			JSONObject object = new JSONObject(input);
			count = count(object);
		} catch (JSONException e)
		{
			try
			{
				JSONArray array = new JSONArray(input);
				count = count(array);
			} catch (JSONException e2)
			{
				throw e2;
			}

		}

		String result = String.format("All numbers in the elves' book add up to %d.", count);

		return Task.forResult(result);

	}

	public int count(Object object)
	{
		if (JSONObject.class.isAssignableFrom(object.getClass()))
		{
			return count((JSONObject) object);
		}
		else if (JSONArray.class.isAssignableFrom(object.getClass()))
		{
			return count((JSONArray) object);
		}
		else if (Integer.class.isAssignableFrom(object.getClass()))
		{
			return (Integer) object;
		}
		return 0;
	}

	public int count(JSONObject json_object)
	{
		int count = 0;
		for (String key : json_object.keySet())
		{
			count += count(json_object.get(key));
		}
		return count;
	}

	public int count(JSONArray json_array)
	{
		int count = 0;
		for (int i = 0; i < json_array.length(); i++)
		{
			count += count(json_array.get(i));
		}
		return count;
	}
}
