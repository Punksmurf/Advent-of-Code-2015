package net.kenvanhoeylandt.solutions.day3;

public class Santa
{
	private final GridCity mGridCity;
	private int mX = 0;
	private int mY = 0;

	public Santa(GridCity gridCity)
	{
		this.mGridCity = gridCity;
	}

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
		mGridCity.getHouseFor(mX, mY).addPresent();
	}
}
