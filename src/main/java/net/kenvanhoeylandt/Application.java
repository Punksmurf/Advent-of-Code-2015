package net.kenvanhoeylandt;

import net.kenvanhoeylandt.services.SessionService;
import net.kenvanhoeylandt.solutions.day1.Day1Solution;
import net.kenvanhoeylandt.solutions.day2.Day2Solution;

public class Application
{
	private static final Class[] mSolutionClasses = new Class[]
	{
		Day1Solution.class,
		Day2Solution.class
	};

	public static void main(String[] args)
	{
		if (args.length != 2)
		{
			System.err.println("Expected 2 arguments");
			printUsage();
			return;
		}

		String session_token = args[0];
		String day_string = args[1];
		int day = Integer.valueOf(day_string);

		if (day < 1 || day > mSolutionClasses.length)
		{
			System.err.println("No solution found for day '" + day_string + "'");
			printUsage();
			return;
		}

		SessionService.shared().setSessionToken(session_token);

		System.out.println("Advent of Code solutions by Ken Van Hoeylandt");

		try
		{
			net.kenvanhoeylandt.solutions.Solution solution = getSolution(day - 1);

			solution.run();
		}
		catch (Exception e)
		{
			System.err.println("Failed to get solution: " + e.getMessage());
		}
	}

	private static void printUsage()
	{
		System.out.println("Usage:");
		System.out.println("\tjava -jar AdventOfCode2016.jar [sessionToken] [day]");
	}

	private static net.kenvanhoeylandt.solutions.Solution getSolution(int index) throws Exception
	{
		if (index < 0 || index >= mSolutionClasses.length)
		{
			throw new Exception("invalid solution index");
		}

		Class class_object = mSolutionClasses[index];

		if (!net.kenvanhoeylandt.solutions.Solution.class.isAssignableFrom(class_object))
		{
			throw new Exception("class is not a Solution type");
		}

		@SuppressWarnings("unchecked")
		Class<net.kenvanhoeylandt.solutions.Solution> solution_class_object = (Class<net.kenvanhoeylandt.solutions.Solution>)class_object;

		return solution_class_object.newInstance();
	}
}
