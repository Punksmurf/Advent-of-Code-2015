package net.kenvanhoeylandt.solutions.day3;

public class Santa
{
	private int mX = 0;
	private int mY = 0;

	public void move(char direction)
	{
		switch (direction)
		{
			case '<':
				mX--;
				break;
			case '>':
				mX++;
				break;
			case '^':
				mY--;
				break;
			case 'v':
				mY++;
				break;
			default:
				throw new RuntimeException("Invalid move");
		}
		House.getHouseFor(mX, mY).addPresent();
	}
}
