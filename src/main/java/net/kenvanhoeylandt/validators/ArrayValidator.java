package net.kenvanhoeylandt.validators;

import net.kenvanhoeylandt.exceptions.InputParsingException;

/**
 * Used for input validation.
 */
public class ArrayValidator
{
	public static <T> void assertSize(T[] array, int size) throws InputParsingException
	{
		if (array.length != size)
		{
			throw new InputParsingException("expected array of exactly " + Integer.toString(size) + " parts");
		}
	}
}
