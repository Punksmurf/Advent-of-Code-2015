package net.kenvanhoeylandt.solutions;

import com.squareup.okhttp.Request;

import net.kenvanhoeylandt.exceptions.InputParsingException;
import net.kenvanhoeylandt.services.RequestService;

import bolts.Task;

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

		request(mDay)
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
					System.err.println("Solution failed: " + exception.getMessage());

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

	private Task<String> request(int day)
	{
		RequestService request_service = RequestService.shared();

		Request request = request_service.requestBuilder()
			.url(String.format("http://adventofcode.com/day/%d/input", day))
			.build();

		return request_service.executeForString(request);
	}

	abstract protected Task<String> solve(String input) throws Exception;
}
