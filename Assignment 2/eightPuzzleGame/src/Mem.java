
public class Mem {
	
	private int[] memo = new int[9];
	private boolean old = false;
	private int came_from = -1;
	
	public int getCameFrom()
	{
		return came_from;
	}
	public void setCameFrom(int came_from)
	{
		this.came_from = came_from;
	}
	
	public void save(int[] memo, int came_from)
	{
		this.memo = memo;
		this.came_from = came_from;
	}
		
	public int[] getMem()
	{
		return memo;
	}
	
	public void setOld(boolean old)
	{
		this.old = old;
	}
	public boolean isOld()
	{
		return old;
	}
	
	public int getHeuristic(int[] ans)
	{
		int h = 0;
				
		for(int i = 0; i < ans.length; i++)
		{
			int j = 0;
			for(j=0; j<=ans.length; j++)
			{
				if(memo[i] == ans[j])
				{
					break;
				}
			}
			
			try
			{
				h += giveHeuristic_Pos1toPos2(i,j);
			}
			catch(Exception e)
			{
				break;
			}
		}
		
		return h;
	}
	
	public int giveHeuristic_Pos1toPos2(int pos1, int pos2)// value of position
	{
//		_____________
//		|_0_|_1_|_2_|
//		|_3_|_4_|_5_|
//		|_6_|_7_|_8_|
		
		if(pos1 == pos2)
		{
			return 1;
		}
		if(		(pos1 == 0 && (pos2 == 1 || pos2 == 3)) ||
				(pos1 == 2 && (pos2 == 1 || pos2 == 5)) ||
				(pos1 == 6 && (pos2 == 3 || pos2 == 7)) ||
				(pos1 == 8 && (pos2 == 5 || pos2 == 7)) ||
				(pos1 == 1 && (pos2 == 0 || pos2 == 2 || pos2 == 4)) ||
				(pos1 == 3 && (pos2 == 0 || pos2 == 4 || pos2 == 6)) ||
				(pos1 == 5 && (pos2 == 2 || pos2 == 4 || pos2 == 8)) ||
				(pos1 == 7 && (pos2 == 4 || pos2 == 6 || pos2 == 8)) ||
				(pos1 == 4 && (pos2 == 1 || pos2 == 3 || pos2 == 5 || pos2 == 7)))
		{
			return 0;
		}
		if(		(pos1 == 0 && (pos2 == 2 || pos2 == 4 || pos2 == 6)) ||
				(pos1 == 2 && (pos2 == 0 || pos2 == 4 || pos2 == 8)) ||
				(pos1 == 6 && (pos2 == 0 || pos2 == 4 || pos2 == 8)) ||
				(pos1 == 8 && (pos2 == 2 || pos2 == 4 || pos2 == 6)) ||
				(pos1 == 1 && (pos2 == 3 || pos2 == 5 || pos2 == 7)) ||
				(pos1 == 3 && (pos2 == 1 || pos2 == 5 || pos2 == 7)) ||
				(pos1 == 5 && (pos2 == 1 || pos2 == 3 || pos2 == 7)) ||
				(pos1 == 7 && (pos2 == 1 || pos2 == 3 || pos2 == 5)) ||
				(pos1 == 4 && (pos2 == 0 || pos2 == 2 || pos2 == 6 || pos2 == 8)))
		{
			return -1;
		}
		if(		(pos1 == 0 && (pos2 == 5 || pos2 == 7)) ||
				(pos1 == 2 && (pos2 == 3 || pos2 == 7)) ||
				(pos1 == 6 && (pos2 == 1 || pos2 == 5)) ||
				(pos1 == 8 && (pos2 == 1 || pos2 == 3)) ||
				(pos1 == 1 && (pos2 == 6 || pos2 == 8)) ||
				(pos1 == 3 && (pos2 == 2 || pos2 == 8)) ||
				(pos1 == 5 && (pos2 == 0 || pos2 == 6)) ||
				(pos1 == 7 && (pos2 == 0 || pos2 == 2)))
		{
			return -2;
		}
		if(		(pos1 == 0 && (pos2 == 8)) ||
				(pos1 == 2 && (pos2 == 6)) ||
				(pos1 == 6 && (pos2 == 2)) ||
				(pos1 == 8 && (pos2 == 0)))
		{
			return -3;
		}
		
		System.out.println("99!! pos1 = " + pos1 + " pos2 = " + pos2);
		return 99;
	}
	
	public void showMem()
	{
		for(int i=0; i<memo.length; i++)
		{
			if(i%3 == 0 && i!=0)
				System.out.println();
			
			System.out.print(memo[i]);
		}
		System.out.println();
	}
	public String toString()
	{
		String msg = "";
		
		for(int i=0; i<memo.length; i++)
		{
			if(i%3 == 0 && i!=0)
				msg += "\n";
			
			msg += memo[i];
		}
		
		return msg;
	}
}
