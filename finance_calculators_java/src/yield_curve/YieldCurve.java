package yield_curve;

import java.util.ArrayList;

public class YieldCurve 
{
	private ArrayList <Double> par_yields = new ArrayList<>();
	private ArrayList <Double> par_tenors = new ArrayList<>();
	private ArrayList <Double> discount_factors = new ArrayList<>();
	private ArrayList <Double> spot_rates = new ArrayList<>();
	private ArrayList <Double> spot_tenors = new ArrayList<>();
	
	public YieldCurve(double y[])
	{
		for (int i=0; i<y.length; i++) 
		{
			par_yields.add(y[i]);
			par_tenors.add(0.5*(i+1));
		}
		
		bootstrap();
	}
	
	private void bootstrap() 
	{
		double dFSums = 0;
		double temp;
		double di;
		double ri;
		
		
		for (int i=0; i<par_yields.size(); i++)
		{
			double y_dec=par_yields.get(i)/100;
			if (i==0) 
			{
				temp = 1/(1+(y_dec/2));
			}
			
			else 
			{
				temp = (1-((y_dec)*(dFSums)/2)) / (1 + (y_dec/2));
			}
			
			if(temp <=0) 
			{	
				break;
				
			}
			
			else 
			{
				di = temp;
				discount_factors.add(di);
				dFSums += di;
				spot_tenors.add(par_tenors.get(i));
				
				ri = (-(Math.log(di))/par_tenors.get(i))*100;
				spot_rates.add(ri);	
			}
		}
	}
	
	
	public int lenParCurve() 
	{
		return par_tenors.size();
	}
	
	public int lenSpotCurve() 
	{
		return spot_tenors.size();
	}
	
	public double DiscountFactor(int i) 
	{
		if (i > discount_factors.size()-1)
		{
			return 0;
		}
		
		else
		{
			return discount_factors.get(i);
		}
	}
	
	public double SpotRate(int i) 
	{
		if( i > spot_rates.size()-1)
		{
			return 0;
		}
		
		else 
		{
			return spot_rates.get(i);
		}
	}
	
	public double SpotTenor(int i)
	{
		if( i > spot_tenors.size()-1)
		{
			return 0;
		}
		
		else 
		{
			return spot_tenors.get(i);
		}
	}
	
	public double ParYield(int i)
	{
		if( i > par_yields.size()-1)
		{
			return 0;
		}
		
		else 
		{
			return par_yields.get(i);
		}
	}
	
	public double ParTenor(int i)
	{
		if( i > par_tenors.size()-1)
		{
			return 0;
		}
		
		else 
		{
			return par_tenors.get(i);
		}
	}
	
	
	private double interp(double t, char m) 
	{
		double f;
		double ti=0;
		double tj=0;
		double ri=0;
		double rj=0;
		double rLin;
		double rCfr;
		
		if(t<= spot_tenors.get(0)) 
		{
			return spot_rates.get(0);
		}
		
		else if (t>= spot_tenors.get(spot_tenors.size()-1)) 
		{
			return spot_rates.get(spot_tenors.size()-1);
		}
		
		else 
		{
			for(int i=0; i<spot_tenors.size(); i++)
			{
				if(t>=spot_tenors.get(i)) 
				{
					if(t<spot_tenors.get(i+1)) 
					{
						ti = spot_tenors.get(i);
						tj = spot_tenors.get(i+1);
						ri = spot_rates.get(i);
						rj= spot_rates.get(i+1);
						break;
					}
				}
			}
			
			f = (t-ti)/(tj-ti);
			
			if(m=='l') 
			{
				rLin = ((1-f)*ri) + (f*rj);
				return rLin;
			}
			
			else 
			{
				rCfr =(((1 - f)*ri*ti) + (f*rj*tj))/ t;
				return rCfr;
			}	
		}
	}
	
	public double lin_interp(double t) 
	{
		double rLin = interp(t, 'l');
		return rLin;
	}
	
	public double cfr_interp(double t) 
	{
		double rCfr = interp(t, 'c');
		return rCfr;
	}	
}
