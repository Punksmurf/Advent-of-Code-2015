package net.kenvanhoeylandt.solutions.day11;

public class SecurityElf
{
	public boolean approves(String password)
	{
		return SecurityRequirements.isStraightMatch(password)
			&& !SecurityRequirements.containsForbiddenLetters(password)
			&& SecurityRequirements.containsTwoDifferentNonOverlappingPairs(password);
	}
}
