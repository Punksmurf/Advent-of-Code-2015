package net.kenvanhoeylandt.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface MatchFinder
{
	void onMatch(int from, int to);

	static void find(String regex, String input, MatchFinder matchFinder)
	{
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		// Find all matches
		while (matcher.find())
		{
			matchFinder.onMatch(matcher.start(), matcher.end());
		}
	}
}
