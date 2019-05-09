
public class Capture {
	
	private int[] captr = new int[9];
	
	public Capture(int[] set)
	{
		captr[0] = set[0];
		captr[1] = set[1];
		captr[2] = set[2];
		captr[3] = set[3];
		captr[4] = set[4];
		captr[5] = set[5];
		captr[6] = set[6];
		captr[7] = set[7];
		captr[8] = set[8];
	}
	
	public int[] getCapture()
	{
		return captr;
	}
}
