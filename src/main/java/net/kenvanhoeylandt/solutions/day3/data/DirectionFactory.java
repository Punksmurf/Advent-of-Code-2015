package net.kenvanhoeylandt.solutions.day3.data;

import net.kenvanhoeylandt.exceptions.InputParsingException;

public class DirectionFactory
{
	public static Direction create(char character) throws InputParsingException
	{
		switch (character)
		{
			case '^':
				return Direction.NORTH;

			case '>':
				return Direction.EAST;

			case 'v':
				return Direction.SOUTH;

			case '<':
				return Direction.WEST;

			default:
				throw new InputParsingException("invalid character: " + character);
		}
	}

	public static Direction[] create(String characters) throws InputParsingException
	{
		Direction[] directions = new Direction[characters.length()];

		for (int i = 0; i < characters.length(); ++i)
		{
			Character c = characters.charAt(i);
			directions[i] = create(c);
		}

		return directions;
	}
}
