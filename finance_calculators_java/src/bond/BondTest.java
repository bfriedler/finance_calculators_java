package bond;


public class BondTest 
{
	public static void main(String [] args)
	{
		Bond tBond=new Bond (6, 0, 2, 20);
		
		//System.out.println(tBond.FairValue(0, 3));
		//double price = price_from_yield(100, 6, 5, 6);
		//System.out.println(price);
		
		System.out.println(Bond.yield(tBond, 0, 99, 0.01, 0.0001, 100));
		//System.out.println(tBond.getCpnDates());
		//double [] cpnAmts = {2, 4, 6, 8, 10, 12};
		//BondVariableCoupon vctBond = new BondVariableCoupon(cpnAmts, -1.1, 2, 6);
		//System.out.println(vctBond.FairValue(0, 7.69));
		//
		//System.out.println(vctBond.yield(vctBond, 0, 103, 0.01, 0.01, 15));
		//double [] b = {5, 10, 12.5};
		//double sum = sum(b, 9);
		//System.out.println(sum);
		//double eps = 1.0e-9;
		//System.out.println(eps);
		
	}
	double price_from_yield(double F, double c, double y, int n)
	{
	double B = 0.0;
	if ((F <= 0.0) || (c < 0.0) || (y < 0.0) || (n <= 0)) {
	return B;
	}
	double temp1 = 1.0 + (0.01*y)/2.0;
	double temp2 = temp1;
	for (int i = 0; i < n; ++i) 
	{
		if (i == n-1) 
		{
			B = B + (F + 0.5*c) / temp2;
		}
		else 
		{
			
		B = B + (0.5*c) / temp2;
		temp2 = temp2 * temp1;
		}

	}
	return B;
	}
	


}
