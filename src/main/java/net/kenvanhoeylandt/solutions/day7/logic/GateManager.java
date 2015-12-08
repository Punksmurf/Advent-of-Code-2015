package net.kenvanhoeylandt.solutions.day7.logic;

import java.util.HashMap;
import java.util.Map;

public class GateManager
{
	Map<String, Gate> mGateMap = new HashMap<>();

	public void register(Gate gate)
	{
		mGateMap.put(gate.getOutputName(), gate);
	}

	public Gate getGate(String outputName)
	{
		return mGateMap.get(outputName);
	}
}
