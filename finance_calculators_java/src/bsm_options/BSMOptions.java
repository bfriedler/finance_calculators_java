package bsm_options;

import funcs.Funcs;

public class BSMOptions 
{
	public static double Call(double S, double K, double r, double q, double sigma, double t, double T)
	{
		double FV=0;
		if ((S<=0) || (K<=0) || (sigma<=0) || (t>=T))
		{
			return FV;
		}
		
		double rDec= Convert(r);
		double qDec= Convert(q);
		double sigDec= Convert(sigma);
		
		double d1=GetD1(S, K, rDec, qDec, T, t, sigDec);
		double d2=GetD2(d1, sigDec, T, t);
		
		double Nd1=Funcs.NormalCDF(d1);
		double Nd2=Funcs.NormalCDF(d2);
		
		FV=(S*(Math.exp((-1*qDec)*(T-t)))*Nd1) - (K*(Math.exp((-1*rDec)*(T-t)))*Nd2);
		
		/*FV=(S*(Math.exp((-1*q)*(T-t)))*Funcs.NormalCDF(d1)) - (K*(Math.exp((-1*r)*(T-t)))*Funcs.NormalCDF(d2));*/
		return  FV;
		
	}
	
	public static double Put(double S, double K, double r, double q, double sigma, double t, double T)
	{
		double FV=0;
		if ((S<=0) || (K<=0) || (sigma<=0) || (t>=T))
		{
			return FV;
		}
		
		double rDec= Convert(r);
		double qDec= Convert(q);
		double sigDec= Convert(sigma);
		
		double d1=GetD1(S, K, rDec, qDec, T, t, sigDec);
		double d2=GetD2(d1, sigDec, T, t);
		
		FV= (K*(Math.exp((-1*rDec)*(T-t)))*Funcs.NormalCDF(-d2)) - (S*(Math.exp((-1*qDec)*(T-t)))*Funcs.NormalCDF(-d1));
		return FV;
	}
	
	private static double Convert( double x)
	{
		return x/100;
	}
	
	private static double GetD1(double S, double K, double r, double q, double T, double t, double sigma)
	{
		return (((Math.log(S/K))+((r-q)*(T-t)))/((sigma)*(Math.sqrt(T-t))))+(.5*sigma*Math.sqrt(T-t));
	}
	
	private static double GetD2(double d1, double sigma, double T, double t)
	{
		return d1-(sigma*Math.sqrt(T-t));
	}
	
	
}
