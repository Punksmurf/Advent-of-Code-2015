package net.kenvanhoeylandt.solutions.day9;

import net.kenvanhoeylandt.exceptions.InputParsingException;
import net.kenvanhoeylandt.solutions.day9.Connection;
import net.kenvanhoeylandt.validators.ArrayValidator;

public class ConnectionFactory
{
	public static Connection create(String input) throws InputParsingException
	{
		String[] logic_parts = input.split(" = ");

		ArrayValidator.assertSize(logic_parts, 2);

		String station_parts = logic_parts[0];
		String distance_part = logic_parts[1];

		String[] stations = station_parts.split(" to ");

		ArrayValidator.assertSize(stations, 2);

		int distance = Integer.parseInt(distance_part);

		return new Connection(stations[0], stations[1], distance);
	}

	public static Connection[] create(String[] inputs) throws InputParsingException
	{
		Connection[] connections = new Connection[inputs.length];

		for (int i = 0; i < inputs.length; ++i)
		{
			connections[i] = create(inputs[i]);
		}

		return connections;
	}
}
