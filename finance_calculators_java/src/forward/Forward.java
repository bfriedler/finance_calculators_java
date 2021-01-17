package forward;

public class Forward 
{
	public static double FairValue(double S0, double r, double q, double t0, double T)
	{

		if(S0 <= 0 || q<0 || r<0 || t0>T)
		{
			return 0;
		}

		
		return S0*Math.exp(((r/100)-(q/100))*(T-t0));
	}

}
