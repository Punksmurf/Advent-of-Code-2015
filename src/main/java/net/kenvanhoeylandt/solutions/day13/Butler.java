package net.kenvanhoeylandt.solutions.day13;

import org.apache.commons.collections4.iterators.PermutationIterator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Butler
{
	// (?<guestName>\w+).*?(?<sign>gain|lose).*?(?<happiness>\d+).*?(?<relationName>\w+)\.
	private static final Pattern sPattern = Pattern.compile("(?<guestName>\\w+).*?(?<sign>gain|lose).*?(?<happiness>\\d+).*?(?<relationName>\\w+)\\.");
	private Map<String, Guest> mGuests = new HashMap<>();

	private Guest getGuest(String name)
	{
		if (!mGuests.containsKey(name))
		{
			mGuests.put(name, new Guest(name));
		}

		return mGuests.get(name);
	}

	public void theseAreOurGuests(String[] guestList)
	{
		for (String guest_info : guestList)
		{
			readGuestInfo(guest_info);
		}
	}

	public void pleaseAddMeToTheListAsWell()
	{
		Guest punksmurf = new Guest("Punksmurf");
		mGuests.put(punksmurf.getName(), punksmurf);
	}

	public List<String> whereShallWePlaceEveryone()
	{
		int best_happiness = 0;
		List<String> best_seating_plan = null;

		PermutationIterator<String> seating_plans = new PermutationIterator<>(mGuests.keySet());
		while (seating_plans.hasNext())
		{
			List<String> seating_plan = seating_plans.next();
			int happiness = howHappyWillOurGuestsBe(seating_plan);
			if (happiness > best_happiness)
			{
				best_happiness = happiness;
				best_seating_plan = seating_plan;
			}
		}

		return best_seating_plan;
	}

	private void readGuestInfo(String guestInfo)
	{
		// Bob would gain 19 happiness units by sitting next to Carol.
		// (\w+).*?(gain|lose).*?(\d+).*?(\w+)\.
		Matcher matcher = sPattern.matcher(guestInfo);
		if (matcher.find())
		{
			String name = matcher.group("guestName");
			String relation_name = matcher.group("relationName");
			int happiness = Integer.valueOf(matcher.group("happiness"));
			if ("lose".equals(matcher.group("sign")))
			{
				happiness *= -1;
			}
			addHappinessRelation(name, relation_name, happiness);
		}

	}

	private void addHappinessRelation(String guestName, String relationName, int happiness)
	{
		Guest guest = getGuest(guestName);
		guest.setRelationHappiness(relationName, happiness);
	}

	public int howHappyWillOurGuestsBe(List<String> seatingPlan)
	{
		int happiness = 0;

		for (int i = 0; i < seatingPlan.size(); i++)
		{
			Guest guest = getGuest(seatingPlan.get(i));
			String left = seatingPlan.get(wrap(i + 1, seatingPlan.size()));
			String right = seatingPlan.get(wrap(i - 1, seatingPlan.size()));
			happiness += guest.getRelationHappiness(left, right);
		}

		return happiness;
	}

	private int wrap(int value, int max)
	{
		value %= max;
		if (value < 0)
		{
			value += max;
		}
		return value;
	}
}
