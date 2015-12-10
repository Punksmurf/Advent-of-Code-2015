package net.kenvanhoeylandt.solutions.day7.computer;

import java.util.ArrayList;
import java.util.List;

public class Computer
{
	private Variables mVariables = new Variables();
	private List<Instruction> mInstructions = new ArrayList<>();

	public void load(String[] instructions)
	{
		for (String instruction : instructions)
		{
			mInstructions.add(Instruction.create(instruction));
		}
	}

	public Instruction getInstructionForDestination(String destination)
	{
		for (Instruction instruction : mInstructions)
		{
			if (instruction.getDestination().equals(destination))
			{
				return instruction;
			}
		}
		return null;
	}

	public void run()
	{
		run(1000);
	}

	public void run(int maxIterations)
	{
		int iterations = 0;
		boolean did_execute;
		while ((did_execute = runOnce()) && iterations < maxIterations)
		{
			iterations++;
		}
		if (did_execute)
		{
			throw new RuntimeException("Computer run exceeded max iterations");
		}
	}

	public boolean runOnce()
	{
		boolean did_execute = false;
		for (Instruction instruction : mInstructions)
		{
			did_execute |= instruction.execute(mVariables);
		}
		return did_execute;
	}

	public int getResult()
	{
		return mVariables.getValue("a");
	}
}
