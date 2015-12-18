package net.kenvanhoeylandt.solutions.day13;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


}
