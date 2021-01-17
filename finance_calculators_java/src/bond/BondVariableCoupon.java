//Bas Tziyon Friedler
//T00347724

package bond;

public class BondVariableCoupon extends Bond
{
	protected double cpn_amt [];
	
	public BondVariableCoupon( double c[], double issue_date, int f, int n) 
	{
		super(0, issue_date, f, n);
		cpn_amt= new double[c.length];
		for(int i=0; i<c.length; i++)
		{
			if(c[i] !=0)
			{
				cpn_amt[i]=c[i];
			}
			else 
			{
				cpn_amt[i]=0;
			}
		}
	}
	
	public double coupon(int i)
	{
		if(i > cpn_amt.length-1 || i<0) 
		{
			return 0;
		}
		else 
		{
			return cpn_amt[i];
		}
	}
	
	@Override
	public double FairValue(double t0, double y)
	{	
		
		double y_decimal = y/100.0; 
		double B=0;
		int i;
		double [] cpn_dates = super.cpn_dates;
		int freq=super.freq;
		final double eps = 1.0e-9;
		double cpn;
		
		
		for( i=0; i<=cpn_amt.length-2; i++)
		{
			if(cpn_dates [i] > t0 + eps) 
			{
				cpn=cpn_amt[i];
				B+= ((cpn/freq))/Math.pow((1+(y_decimal/freq)), (freq*((cpn_dates[i])-t0)));
			}
			
		}
		if(cpn_dates [i] > t0 + eps) 
		{
			B+=(super.Face+(cpn_amt[i]/freq))/Math.pow((1+(y_decimal/freq)), (freq*((cpn_dates[i])-t0)));
		}
		
		
		return B;
	}

}
