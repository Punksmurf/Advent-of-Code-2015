package net.kenvanhoeylandt.solutions;

import bolts.Task;
import net.kenvanhoeylandt.exceptions.InputParsingException;
import net.kenvanhoeylandt.services.ChallengeDataService;

public abstract class Solution implements Runnable
{
	private final int mDay;

	public Solution(int day)
	{
		mDay = day;
	}

	@Override
	public final void run()
	{
		// Request data -> Solve with data -> Output result/error

		System.out.print("Retrieving assignment data...");

		ChallengeDataService.shared().request(mDay)
			.onSuccessTask(task ->
			{
				System.out.println("done");
				System.out.print("Solving assignment...");

				return solve(task.getResult());
			})
			.continueWith(task ->
			{
				if (task.isFaulted())
				{
					System.out.println("error");

					Exception exception = task.getError();
					String message = exception.getMessage();

					if (message != null)
					{
						System.err.println("Solution failed: " + message);
						exception.printStackTrace();
					}
					else
					{
						exception.printStackTrace();
					}

					if (InputParsingException.class.isAssignableFrom(exception.getClass()))
					{
						System.err.println("Make sure that you provide a valid token and that Advent of Code 2016 is still available online.");
					}
				}
				else
				{
					System.out.println("done");
					System.out.println(String.format("Solution for day %d: %s", mDay, task.getResult()));
				}

				return null;
			});
	}

	public abstract Task<Object> solve(String input) throws Exception;
}
