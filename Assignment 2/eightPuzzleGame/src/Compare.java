
public class Compare {

	public boolean isSame(int[] set01, Mem[] mem, int lst_mem) 
	{
		for (int j=0; j<lst_mem; j++) 
		{
			int[] temp = new int[mem[j].getMem().length];
			for(int i=0; i<temp.length; i++)
			{
				temp[i] = mem[j].getMem()[i];
			}
			
			for (int i = 0; set01[i] == temp[i]; i++) 
			{
				if (i == 8) 
				{
					return true;
				}
			}
		}
		return false;
	}
}
