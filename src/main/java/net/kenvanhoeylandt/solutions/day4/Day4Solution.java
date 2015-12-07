package net.kenvanhoeylandt.solutions.day4;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class Day4Solution extends Solution
{
	private static final String sPrefix = "00000";

	public Day4Solution()
	{
		super(4);
	}

	@Override
	protected Task<Object> solve(String input) throws Exception
	{
		input = input.substring(0, input.length() - 1);

		String hash = "";
		int count = 0;
		String secret;

		MessageDigest digest = MessageDigest.getInstance("MD5");

		while (!hash.startsWith(sPrefix))
		{
			count++;

			secret = input + Integer.valueOf(count);

			byte[] result_bytes = digest.digest(secret.getBytes());

			hash = DatatypeConverter.printHexBinary(result_bytes);
		}

		return Task.forResult(count);
	}
}
