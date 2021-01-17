package bsm_options;

public class Test 
{
	public static void main(String[] args)
	{	
		double c;
		double p;
		 c=BSMOptions.Call(100, 101, 5, 1, 35, 0, 1);
		 p=BSMOptions.Put(100, 101, 5, 1, 35, 0, 1);
		 System.out.println(c);
		 System.out.println(p);
	}
}
