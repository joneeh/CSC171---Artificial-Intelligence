
public class CheckAnswer {
	
	public boolean isCorrect(int[] ans, int[] set)
	{
		for(int i=0; ans[i] == set[i]; i++)
		{
			if(i == 8)
			{
				return true;
			}
		}
		return false;
	}
	
	public int choosePath_BFS(int[] ans, Mem[] mem, int lst_mem)
	{
		int max_h = -99;
		int mem_num = 1;
		
		for(int i=1; i<=lst_mem; i++)
		{	
			int h;
			
			try
			{
				h = mem[i].getHeuristic(ans);
			}
			catch(Exception e)
			{
				break;
			}
			
			if(!mem[i].isOld() && h>max_h)
			{
				max_h = h;
				mem_num = i;
			}
		}
		System.out.println("max_h = " + max_h + " mem_num = " + mem_num);
		
		return mem_num;
	}
	
	public int choosePath_Astar(int[] ans, Mem[] mem, int lst_mem)
	{
		int max_h = -99;
		int mem_num = 1;
		
		for(int i=1; i<=lst_mem; i++)
		{	
			int h;
			int j = i;
			int how_far = 0;
			
			try
			{
				while(j > 0)
				{	
					j = mem[j].getCameFrom();
					how_far--;
				}
			
				h = mem[i].getHeuristic(ans) + how_far;
			}
			catch(Exception e)
			{
				break;
			}
			
			if(!mem[i].isOld() && h>max_h)
			{
				max_h = h;
				mem_num = i;
			}
		}
		System.out.println("max_h = " + max_h + " mem_num = " + mem_num);
		
		return mem_num;
	}
}
