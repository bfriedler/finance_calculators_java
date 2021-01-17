//Bas Tziyon Friedler
// T00347724
package bond;

import java.lang.Math;
import java.util.Arrays;

public class Bond 
{
	protected final double Face = 100;
	protected double cpn=0;
	protected double t_issue =0;
	protected double T_mat=0;
	protected double cpn_dates [];
	protected int freq=2;
	protected int num_cpn_periods = 1;
	
	
	public Bond(double c, double issue_date, int f, int n) 
	{
		t_issue=issue_date;
		if(c>0) 
		{
			cpn=c;
		}
		if(f==1 || f==2 || f==4 || f==12) 
		{
			freq=f;
		}
		if(n>0) 
		{
			num_cpn_periods=n;
		}
		T_mat = t_issue + (double)num_cpn_periods/f;
		cpn_dates= new double[num_cpn_periods];
		for(int i=0; i<num_cpn_periods; i++)
		{
			cpn_dates[i] = t_issue+ ((double)i+1)/freq;
		}
	}
	
	
	public double getMaturity()
	{
		return T_mat;
	}
	
	public double getIssueDate() 
	{
		return t_issue;
	}
	
	public int getFrequency() 
	{
		return freq;
	}
	
	public int getNumCpnPeriods() 
	{
		return num_cpn_periods;
	}
	
	public double getMaturityDate()
	{
		return T_mat;
	}
	
	public String  getCpnDates() 
	{
		return Arrays.toString(cpn_dates);
	}
	
	public double [] getCpnDatesAddress() 
	{
		return cpn_dates;
	}
	
	public double getFace()
	{
		return Face;
	}
	
	
	public double FairValue(double t0, double y)
	{
		double y_decimal = y/100.0; 
		double B=0;
		final double eps = 1.0e-9;
		int i;

		for( i=0; i<=num_cpn_periods-2; i++)
		{
			if(cpn_dates [i] > t0 + eps) 
			{
				B+= ((cpn/freq))/Math.pow((1+(y_decimal/freq)), (freq*(cpn_dates[i] - t0)));
			}
		}
		if(cpn_dates [i] > t0 + eps) 
		{
			B+=(Face+(cpn/freq))/Math.pow((1+(y_decimal/freq)), (freq*(cpn_dates[i] - t0)));
		}
		
		
		return B;
	}
	
	
	public static double yield(Bond b, double t0, double B_target, double B_tol, double y_tol, int max_iter) 
	{
		double bad_yield = -1.0;
		double y_low;
		double B_low;
		double diff_B_low;
		double y_high;
		double diff_B_high;
		double B_high;
		double y;
		double FV;
		double diff_FV;
		
		if(B_target <=0 || t0>=b.T_mat || B_tol<=0 || y_tol<=0 || max_iter<=0)
		{
			return bad_yield;
		}
		
		y_low=0;
		B_low= b.FairValue(t0, y_low);
		if(B_low < B_target) 
		{
			return bad_yield;
		}
		
		diff_B_low=B_low-B_target;
		if (Math.abs(diff_B_low) <= B_tol)
		{
			return y_low;
		}
		
		y_high=0.5;
		B_high = B_low;
		diff_B_high = diff_B_low;
		while(B_high > B_target)
		{
			y_high *= 2.0;
			B_high = b.FairValue(t0, y_high);
			diff_B_high = B_high - B_target;
			if(Math.abs(diff_B_high) <= B_tol) 
			{
				return y_high;
			}
		}
		
		for (int iter=0; iter<max_iter; iter++)
		{
			y = (y_high+y_low)/2.0;
			FV = b.FairValue(t0, y);
			diff_FV = FV- B_target;
			if (Math.abs(diff_FV) <= B_tol)
			{
				return y;
			}
			
			else if(FV > B_target) 
			{
				y_low = y;
			}
			else
			{
				y_high = y;
			}
			
			if(Math.abs(y_high - y_low) <= y_tol)
			{
				y = (y_low+y_high)/2.0;
				return y;
			}
		}
		return bad_yield;
	}
}
