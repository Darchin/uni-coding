int function(int y, double x)
{
	//smallest integer bigger than x
	int int_x = (int)x+1;
	int check;
	for(int i=int_x; ;i++)
	{
		check = 1;
		//checking if the numbers share a factor
		for(int j=2; j<=i && j<=y; j++)
		{
			if (i % j == 0 && y % j == 0)
			{
				check = 0;
				break;
			}
		}
		//if 'check' is still 1, the numbers did not share a factor
		if (check == 1)
		{
			return i;
		}
	}
}