package funcs;

import java.util.concurrent.ThreadLocalRandom;
public final class Funcs
{
	private Funcs() 
	{
		;
	}
	
	public static double nextGaussian() 
	{
		return nextGaussian(0.0, 1.0);
	}
	
	public static double nextGaussian(double mu, double sigma) 
	{
		ThreadLocalRandom t = ThreadLocalRandom.current();
		return (mu + sigma*t.nextGaussian());
	}
	
	public static double NormalPDF(double x) 
	{
		return Math.exp(-0.5*x*x) / Math.sqrt(2.0*Math.PI);
	}
	
	public static double NormalCDF(double x) 
	{
		if (x == 0.0) return 0.5;
		if (x < 0.0) return 1.0 - NormalCDF(-x);
		double[] b = { 0.2316419, 0.319381530, -0.356563782,
		1.781477937, -1.821255978, 1.330274429 };
		double t = 1.0 / (1.0 + b[0] * x);
		double sum = t * (b[1] + t*(b[2] + t*(b[3] + t*(b[4] + t*(b[5])))));
		double result = 1.0 - NormalPDF(x) * sum;
		return result;
	}
}

