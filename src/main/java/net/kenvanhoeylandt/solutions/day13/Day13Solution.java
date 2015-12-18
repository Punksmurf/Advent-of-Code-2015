package net.kenvanhoeylandt.solutions.day13;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;
import org.apache.commons.collections4.iterators.PermutationIterator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13Solution extends Solution
{
	public Day13Solution()
	{
		super(13);
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{
		Butler carson = new Butler();
		carson.theseAreOurGuests(input.split("\n"));

		List<String> seating_plan = carson.whereShallWePlaceEveryone();

		carson.pleaseAddMeToTheListAsWell();
		List<String> new_seating_plan = carson.whereShallWePlaceEveryone();

		String result = String.format("Carson found the best seating plan to be %s, for a total happiness of %d\n" +
				"After adding me, the order changed to be %s, for a total happiness of %d",
				seating_plan.toString(), carson.howHappyWillOurGuestsBe(seating_plan),
				new_seating_plan.toString(), carson.howHappyWillOurGuestsBe(new_seating_plan));

		return Task.forResult(result);
	}


	public static class Butler
	{
		private static final Pattern sPattern = Pattern.compile("(\\w+).*?(gain|lose).*?(\\d+).*?(\\w+)\\.");
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
				String name = matcher.group(1);
				String relation_name = matcher.group(4);
				int happiness = Integer.valueOf(matcher.group(3));
				if ("lose".equals(matcher.group(2)))
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

	public static class Guest
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
}
