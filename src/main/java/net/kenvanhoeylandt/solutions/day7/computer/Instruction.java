package net.kenvanhoeylandt.solutions.day7.computer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Instruction
{
	private static Pattern mPattern = Pattern.compile("^([a-z0-9]+)?\\s?([A-Z]+)?\\s?([a-z0-9]+)?\\s->\\s([a-z0-9]+)$");

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

	private boolean mAlreadyRan = false;

	private String mInstruction;

	private Operator mOperator;
	private String mDestination;
	private Source mSourceA;
	private Source mSourceB;

	public static Instruction create(String instruction)
	{
		Instruction c = new Instruction();

		c.mInstruction = instruction;

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
		Matcher matcher = mPattern.matcher(instruction);

		if (matcher.find())
		{
			String value_a = matcher.group(1);
			String operator = matcher.group(2);
			String value_b = matcher.group(3);
			String destination = matcher.group(4);

			// rewire value b to value a for NOT operation
			if (value_a == null && value_b != null && operator.equals("NOT"))
			{
				value_a = value_b;
				value_b = null;
			}

			c.mSourceA = Source.create(value_a);
			c.mSourceB = Source.create(value_b);
			c.mOperator = Operator.getFromString(operator);
			c.mDestination = destination;

			return c;
		}
		else
		{
			throw new RuntimeException("Unable to parse instruction: " + instruction);
		}
	}

	public String getDestination()
	{
		return mDestination;
	}

	public Source getSourceA()
	{
		return mSourceA;
	}

	public Source getSourceB()
	{
		return mSourceB;
	}

	public boolean execute(Variables variables)
	{
		if (mAlreadyRan)
		{
			return false;
		}

		Integer source_a_value = variables.getValue(mSourceA);
		Integer source_b_value = variables.getValue(mSourceB);
		if (source_a_value == null)
		{
			// ignore this instruction; we cannot do anything yet
			return false;
		}

		switch (mOperator)
		{
			case ASSIGN:
				variables.setValue(mDestination, source_a_value);
				break;
			case AND:
				if (source_b_value == null)
				{
					// ignore this instruction; we cannot do anything yet
					return false;
				}
				variables.setValue(mDestination, source_a_value & source_b_value);
				break;
			case OR:
				if (source_b_value == null)
				{
					// ignore this instruction; we cannot do anything yet
					return false;
				}
				variables.setValue(mDestination, source_a_value | source_b_value);
				break;
			case NOT:
				variables.setValue(mDestination, ~source_a_value);
				break;
			case LSHIFT:
				if (source_b_value == null)
				{
					// ignore this instruction; we cannot do anything yet
					return false;
				}
				variables.setValue(mDestination, source_a_value << source_b_value);
				break;
			case RSHIFT:
				if (source_b_value == null)
				{
					// ignore this instruction; we cannot do anything yet
					return false;
				}
				variables.setValue(mDestination, source_a_value >>> source_b_value);
				break;
		}

		mAlreadyRan = true;
		return true;
	}
}
