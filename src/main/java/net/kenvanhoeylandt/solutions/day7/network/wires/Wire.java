package net.kenvanhoeylandt.solutions.day7.network.wires;

import bolts.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Wire
{
	private static Map<String, Wire> sWires = new HashMap<>();
	private static Pattern mPattern = Pattern.compile("^([a-z0-9]+)?\\s?([A-Z]+)?\\s?([a-z0-9]+)?\\s->\\s([a-z0-9]+)$");

	private Operator mOperator;
	private String mInputA;
	private String mInputB;
	private String mName;

	private Task<Integer> mSolveTask = null;

	private static enum Operator
	{
		ASSIGN,
		AND, OR, NOT,
		LSHIFT, RSHIFT;

		public static Operator getFromString(String operator)
		{
			if (operator == null)
			{
				return Operator.ASSIGN;
			}

			switch (operator)
			{
				case "AND":
					return Operator.AND;
				case "OR":
					return Operator.OR;
				case "NOT":
					return Operator.NOT;
				case "LSHIFT":
					return Operator.LSHIFT;
				case "RSHIFT":
					return Operator.RSHIFT;
				default:
					throw new RuntimeException("Action not recognised: " + operator);
			}
		}
	}

	public static Wire create(String description)
	{
		// possible instructions

		// a -> dest
		// a AND b -> dest
		// a OR b -> dest
		// NOT a -> dest *
		// a LSHIFT b -> dest
		// a RSHIFT b -> dest

		// *) for NOT: a gets assigned to *b* by the regex

		//  (value a  )    (OPERATOR)  (value b  )   ->  (destination)
		// ^([a-z0-9]+)?\s?([A-Z]+)?\s?([a-z0-9]+)?\s->\s([a-z0-9]+)$
		Matcher matcher = mPattern.matcher(description);

		if (matcher.find())
		{
			Wire wire;
			Operator operator = Operator.getFromString(matcher.group(2));

			switch (operator)
			{
				case ASSIGN:
					wire = new AssignWire();
					break;
				case AND:
					wire = new AndWire();
					break;
				case OR:
					wire = new OrWire();
					break;
				case NOT:
					wire = new NotWire();
					break;
				case LSHIFT:
					wire = new LShiftWire();
					break;
				case RSHIFT:
					wire = new RShiftWire();
					break;
				default:
					throw new RuntimeException("Wire not supported");
			}

			wire.mOperator = operator;

			wire.mInputA = matcher.group(1);
			wire.mInputB = matcher.group(3);
			wire.mName = matcher.group(4);

			// rewire value b to value a for NOT operation for clarity
			if (wire.mInputA == null && wire.mInputB != null && wire.mOperator == Operator.NOT)
			{
				wire.mInputA = wire.mInputB;
				wire.mInputB = null;
			}

			sWires.put(wire.getName(), wire);
			return wire;
		}

		return null;
	}

	public static void resetAll()
	{
		for (String name : sWires.keySet())
		{
			sWires.get(name).reset();
		}
	}

	public static Wire getWire(String name)
	{
		return sWires.get(name);
	}

	public void reset()
	{
		mSolveTask = null;
	}

	public String getName()
	{
		return mName;
	}

	public final Task<Integer> solve()
	{
		if (mSolveTask == null)
		{
			mSolveTask = solveInteral();
		}

		return mSolveTask;
	}

	protected abstract Task<Integer> solveInteral();

	public void setInputA(String value)
	{
		if (mSolveTask != null)
		{
			throw new RuntimeException("Cannot set input value after solve request has been issued");
		}

		mInputA = value;
	}

	public void setInputB(String value)
	{
		if (mSolveTask != null)
		{
			throw new RuntimeException("Cannot set input value after solve request has been issued");
		}

		mInputB = value;
	}

	public Task<Integer> getInputA()
	{
		if (isInteger(mInputA))
		{
			return Task.forResult(Integer.valueOf(mInputA));
		}
		else
		{
			return getWire(mInputA).solve();
		}
	}

	public Task<Integer> getInputB()
	{
		if (isInteger(mInputB))
		{
			return Task.forResult(Integer.valueOf(mInputB));
		}
		else
		{
			return getWire(mInputB).solve();
		}
	}

	// Might be better off in a Utils class
	private static boolean isInteger(String input)
	{
		try
		{
			Integer i = Integer.valueOf(input);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}
}
