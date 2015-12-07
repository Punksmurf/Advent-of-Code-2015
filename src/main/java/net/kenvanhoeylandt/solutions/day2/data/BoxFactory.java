package net.kenvanhoeylandt.solutions.day2.data;

import net.kenvanhoeylandt.exceptions.InputParsingException;

import java.util.ArrayList;
import java.util.List;

public class BoxFactory
{
	public static Box create(String data) throws InputParsingException
	{
		String[] entries = data.split("x");

		if (entries.length != 3)
		{
			throw new InputParsingException("entry not in format [length]x[width]x[height]");
		}

		try
		{
			int length = Integer.parseInt(entries[0]);
			int width = Integer.parseInt(entries[1]);
			int height = Integer.parseInt(entries[2]);

			return new Box(length, width, height);
		}
		catch (Exception e)
		{
			throw new InputParsingException("failed to parse numbers in entry");
		}
	}

	public static List<Box> create(String[] dataArray) throws InputParsingException
	{
		ArrayList<Box> box_list = new ArrayList<>(dataArray.length);

		for (String data : dataArray)
		{
			Box box = create(data);
			box_list.add(box);
		}

		return box_list;
	}
}
