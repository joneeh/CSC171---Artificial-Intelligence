
public class SwapLaw {
	
	public int[] spread(int[] memo, int ignore)
	{
		int zero = zero_pos(memo);
		int pass = 0;
		
		for(int i=0; i<memo.length; i++)
		{
			if(canSwap(zero, i))
			{
				pass++;
				
				if(pass > ignore)
				{
					swap(memo, zero, i);
					
					return memo;
				}
			}
		}
		
		return memo;
	}
	
	public int zero_pos(int[] memo)
	{
		for(int i=0; i<memo.length; i++)
		{
			if(memo[i] == 0)
			{
				return i;
			}
		}
		
		System.out.println("Error!: NO_ZERO");
		return -1;
	}
	public void swap(int[] memo, int a, int b)
	{
		if(canSwap(a, b))
		{
			int temp = memo[a];
			memo[a] = memo[b];
			memo[b] = temp;
		}
	}
	public boolean canSwap(int pos1, int pos2)
	{
		if(pos1 == 0 && (pos2 == 1 || pos2 == 3))
		{
			return true;
		}
		if(pos1 == 1 && (pos2 == 0 || pos2 == 2 || pos2 == 4))
		{
			return true;
		}
		if(pos1 == 2 && (pos2 == 1 || pos2 == 5))
		{
			return true;
		}
		if(pos1 == 3 && (pos2 == 0 || pos2 == 4 || pos2 == 6))
		{
			return true;
		}
		if(pos1 == 4 && (pos2 == 1 || pos2 == 3 || pos2 == 5 || pos2 == 7))
		{
			return true;
		}
		if(pos1 == 5 && (pos2 == 2 || pos2 == 4 || pos2 == 8))
		{
			return true;
		}
		if(pos1 == 6 && (pos2 == 3 || pos2 == 7))
		{
			return true;
		}
		if(pos1 == 7 && (pos2 == 4 || pos2 == 6 || pos2 == 8))
		{
			return true;
		}
		if(pos1 == 8 && (pos2 == 5 || pos2 == 7))
		{
			return true;
		}
		return false;
	}
}
