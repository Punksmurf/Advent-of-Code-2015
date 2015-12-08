package net.kenvanhoeylandt.solutions.day6.logic;

import net.kenvanhoeylandt.solutions.day6.data.Area;

public interface LightGrid
{
	void turnOn(Area area);
	void turnOff(Area area);
	void toggle(Area area);
}
