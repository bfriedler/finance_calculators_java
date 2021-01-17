package binomial_option;

public class BinomialOption 
{
	public static enum AmEur {AM, EUR};
	public static enum PutCall {PUT, CALL};
	
	private double K;
	private double T;
	private AmEur ae;
	private PutCall pc;
	private int n;
	private double [][] S_tree;
	private double [][] V_tree;
	
	public BinomialOption(double strike, double t_exp, PutCall pc, AmEur ae, int n)
	{
		K=strike;
		T = t_exp;
		this.pc=pc;
		this.ae=ae;
		if(n<1)
		{
			n=1;
		}
		this.n=n;
		
		S_tree = new double[n+1][n+1];
		V_tree = new double [n+1][n+1];		
	}
	
	public double[][] getS_tree()
	{
		double [][] S_tree2=new double [n+1][n+1];
		for (int i=0; i<S_tree.length; i++)
		{
			for(int j=0; j<=i; j++)
			{
				S_tree2[i][j]=S_tree[i][j];
			}
		}
		return S_tree2;
	}
	
	public double FairValue(double S0, double t0, double r, double q, double sigma)
	{
		double FV=0;
		if ((S0<=0) || (K<=0)|| (sigma<=0) || (t0>=T))
		{
			return FV;
		}
		r*=0.01;
		q*=0.01;
		sigma*=0.01;
		
		double tau = (T-t0)/n;
		double u=Math.exp(((sigma)*(Math.sqrt(tau))));
		double d=1/u;
		double  pUp= ((Math.exp((r-q)*tau)-d)/(u-d));
		double pDown = (u-(Math.exp((r-q)*tau)))/(u-d);
		double df= Math.exp(-r*tau);
		
		if(pUp<=0.0 || pUp>=1.0) return 0.0;
		
		S_tree[0][0]= S0;
		

		for  (int i=1; i<=n; i++)
		{
			S_tree[i][0] = S_tree[i-1][0]*d;
		
			for (int j=1; j<=i; j++)
			{
				S_tree[i][j] = S_tree[i][j-1]*u*u;
			}
		}

		
		for(int j=0; j<=n; j++) 
		{
			double St = S_tree[n][j];
			if(pc.equals(PutCall.PUT))
			{
				V_tree[n][j] = Math.max(K-St, 0);
			}
			else 
			{
				V_tree[n][j] = Math.max(St-K, 0);
			}
		}
		
		for(int i=n-1; i>=0; i--)
		{
			for(int j = 0; j<=i; j++)
			{
				double Vdisc = df*( pDown*V_tree[i+1][j] + pUp*V_tree[i+1][j+1]);
				if (ae.equals(AmEur.EUR))
				{
					V_tree[i][j] = Vdisc;
				}
				else 
				{
					double Vintr;
					if(pc.equals(PutCall.PUT))
					{
						 Vintr = Math.max(K-(S_tree[i][j]), 0);
					}
					else
					{
						 Vintr = Math.max((S_tree[i][j])-K, 0);
					}
					V_tree[i][j] = Math.max(Vdisc, Vintr);
				}
			}
		}
		FV = V_tree[0][0];
		return FV;
	}
	
	public double impliedVolatitlity(double target, double S0, double t0, double r, double q, double f_tol, double v_tol, int max_iter)
	{
		final double bad_vol =-1;
		if ((target <= 0) || (S0 <= 0) || (t0 >= T))return bad_vol;
		if ((f_tol <= 0) || (v_tol <= 0) || (max_iter <= 0)) return bad_vol;
		
		double sigma1=40;
		double FV1 = FairValue(S0, t0, r, q, sigma1);
		double diff_FV1 = FV1-target;
		if (Math.abs(diff_FV1) <= f_tol) return sigma1;
		
		double scale =0;
		if (diff_FV1<0.0)scale=2.0;
		else if (diff_FV1>0.0) scale =0.5;
		
		double sigma2=sigma1;
		double FV2=FV1;
		double diff_FV2 = FV2-target;
		int i=0;
		for(i=0; i<max_iter; i++)
		{
			sigma2*=scale;
			FV2 = FairValue(S0, t0, r, q, sigma2);
			diff_FV2=FV2-target;
			if(Math.abs(diff_FV2)<= f_tol) return sigma2;
			if(diff_FV1*diff_FV2<0.0) break;
		}
		if(i == max_iter) return bad_vol;
		
		for(int iter=0; iter<max_iter; iter++)
		{
			double sigma = (sigma1+sigma2)/2.0;
			double FV = FairValue(S0, t0, r, q, sigma);
			double diff_FV = FV-target;
			if(Math.abs(diff_FV)<= v_tol) return sigma;
			else if(diff_FV*diff_FV1>0) sigma1=sigma;
			else sigma2=sigma;
			if(Math.abs(sigma2-sigma1)<= v_tol)
			{
				sigma= (sigma1+sigma2)/2.0;
				return sigma;
			}
		}
		return bad_vol;
	}
	
}
