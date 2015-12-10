package net.kenvanhoeylandt.solutions.day4;

import bolts.Task;
import net.kenvanhoeylandt.solutions.Solution;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4Solution extends Solution
{
	MessageDigest mMD5;

	public Day4Solution()
	{
		super(4);
		try
		{
			mMD5 = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Task<Object> solve(String input) throws Exception
	{
		// input has a newline
		input = input.trim();

		int five_zeroes = -1;
		int six_zeroes = -1;

		for (int i = 0; i < Integer.MAX_VALUE; i++)
		{
			byte[] hash = hash(input, i);

			if (hash[0] == 0 && hash[1] == 0)
			{
				// any value below 0x10 starts with 0x0? when represented as a string
				if (five_zeroes == -1 && (hash[2] & 0xff) < 0x10)
				{
					five_zeroes = i;
				}
				if (six_zeroes == -1 && hash[2] == 0)
				{
					six_zeroes = i;
				}
			}

			if (five_zeroes != -1 && six_zeroes != -1)
			{
				break;
			}
		}
		String result = String.format("The mining result is %d for five zeroes and %d for six zeroes", five_zeroes, six_zeroes);

		return Task.forResult(result);
	}

	private byte[] hash(String input, int value)
	{
		String key = String.format("%s%d", input, value);
		byte[] hash = mMD5.digest(key.getBytes());

		return hash;
	}
}
