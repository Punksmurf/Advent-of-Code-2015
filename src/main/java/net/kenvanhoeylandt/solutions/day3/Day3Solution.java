package net.kenvanhoeylandt.solutions.day3;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;

public class Day3Solution extends Solution
{
	public Day3Solution()
	{
		super(3);
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{

		// initialize cities and their (robo-)Santas.
		GridCity barcelona = new GridCity();
		Santa spanish_santa = new Santa(barcelona);

		GridCity copenhagen = new GridCity();
		Santa danish_santa = new Santa(copenhagen);
		Santa robo_santa = new Santa(copenhagen);

		// add the first presents
		barcelona.getHouseFor(0, 0).addPresent();
		copenhagen.getHouseFor(0, 0).addPresent();
		copenhagen.getHouseFor(0, 0).addPresent();

		for (int i = 0; i < input.length(); i++)
		{
			char direction = input.charAt(i);
			spanish_santa.move(direction);

			if (i % 2 == 0)
			{
				danish_santa.move(direction);
			}
			else
			{
				robo_santa.move(direction);
			}
		}

		String result = String.format(
				"Spanish Santa visited %d houses in Barcelona for a total of %d gifts.\n"+
				"Danish Santa and RoboSanta visited %d houses in Copenhagen for a total of %d gifts",
				barcelona.getTotalHouses(), barcelona.getTotalPresents(),
				copenhagen.getTotalHouses(), copenhagen.getTotalPresents()
		);

		return Task.forResult(result);
	}


}
