package net.kenvanhoeylandt.solutions.day11;

import net.kenvanhoeylandt.solutions.Solution;

public class Day11Solution extends Solution
{
	public Day11Solution()
	{
		super(11);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		SecurityElf security_elf = new SecurityElf();

		String part_one_result = harassSecurityElfForPassword(security_elf, input);
		String part_two_result = harassSecurityElfForPassword(security_elf, part_one_result);

		return String.format("part one: %s, part two: %s", part_one_result, part_two_result);
	}

	public String harassSecurityElfForPassword(SecurityElf securityElf, String value)
	{
		do
		{
			// Get the new password by increasing the last one
			value = WeirdStringUtils.increase(value);

			// We're stuck in this loop until the Elf approves...
		}
		while (!securityElf.approves(value));

		return value;
	}
}
