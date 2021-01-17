package binomial_option;

import binomial_option.BinomialOption.AmEur;
import binomial_option.BinomialOption.PutCall;

public class Test 
{
	public static void main(String[] args)
	{
		double K=101;
		double T=1;
		int n=100;
		
		//BinomialOption eur_call = new BinomialOption(K, T, PutCall.CALL, AmEur.EUR, n);
		//BinomialOption am_call = new BinomialOption(K, T, PutCall.CALL, AmEur.AM, n);
		//BinomialOption eur_put = new BinomialOption(K, T, PutCall.PUT, AmEur.EUR, n);
		BinomialOption am_put = new BinomialOption(K, T, PutCall.PUT, AmEur.AM, n);
		
		double S0=100;
		double t0=0;
		double r=5;
		double q=1;
		double target=10;
		double f_tol = .001;
		double v_tol =.01;
		int max_iter = 100;
		
		//Fair Value
		//System.out.println("eur_call FV: "+eur_call.FairValue(S0, t0, r, q, sigma));
		//System.out.println("am_call FV: "+am_call.FairValue(S0, t0, r, q, sigma));
		//System.out.println("eur_put FV: "+eur_put.FairValue(S0, t0, r, q, sigma));
		//System.out.println("am_put FV: "+am_put.FairValue(S0, t0, r, q, sigma));
		
		//implied volatility
		//System.out.println("eur_call iVol: "+eur_call.impliedVolatitlity(target, S0, t0, r, q, f_tol, v_tol, max_iter));
		//System.out.println("am_call iVol: "+am_call.impliedVolatitlity(target, S0, t0, r, q, f_tol, v_tol, max_iter));
		//System.out.println("eur_put iVol: "+eur_put.impliedVolatitlity(target, S0, t0, r, q, f_tol, v_tol, max_iter));
		System.out.println("am_put iVol: "+am_put.impliedVolatitlity(target, S0, t0, r, q, f_tol, v_tol, max_iter));
		
		//stock prices
		/*double [][] S_tree3 = eur_put.getS_tree();
		for (int i=0; i<S_tree3.length; i++)
		{
			for(int j=0; j<=i; j++)
			{
				System.out.print(S_tree3[i][j]+", ");
			}
			System.out.println();
		}*/
		
		//terminal payoff (put = max(K-St, 0), call= max(St-k,0)
		
		
	}

}
