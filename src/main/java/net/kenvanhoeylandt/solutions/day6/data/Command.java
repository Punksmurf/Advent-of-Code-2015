package net.kenvanhoeylandt.solutions.day6.data;

import net.kenvanhoeylandt.exceptions.InputParsingException;

public class Command
{
	public enum Action
	{
		TURN_ON,
		TURN_OFF,
		TOGGLE
	}

	private Action mAction;

	private Area mArea;

	public Command(Action action, Area area) throws InputParsingException
	{
		mAction = action;
		mArea = area;
	}

	public Action getAction()
	{
		return mAction;
	}

	public Area getArea()
	{
		return mArea;
	}
}
