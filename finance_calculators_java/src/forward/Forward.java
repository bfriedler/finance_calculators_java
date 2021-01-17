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

/*
 * Section 2
 * T=.75, F=104.7323
 * T=.65, F=98.01556
 * 
 *Section 3
	 F = 105.127096

	Question 1, F = 105.0:
	
	F< e^r(T-t0)S0
	
	1. Enter into a forward contract at time t0 to buy the stock at 105.0 at the later time T
	2. Sell one share of the stock today, t0. (for $100)
	3. The $100 is put in the bank
	4. At time T=1.0 years, the $100 in the bank has compounded to $105.13
	5. This money is used to close the forward contract by paying 105.0 and receiving one share of stock. 
	6. Since 105.13 > than 105.0, there is a 13 cent profit. 
	
	Question 2, F = 106.0
	
	F> e^r(T-t0)S0
	
	1. Enter into a forward contract at the time t0 to sell the stock at the 106.0 at the later time T
	2. Take out a loan of$100, and buy one share of the stock at today, t0. (for $100)
	3. At time T=1.0 years, close the forward contract by delivering the stock and receiving 106.0 payment for it. 
	4. At this point the loan to repay has compounded to 105.13
	5. Pay back the loan
	6. Since 105.13 < 106, there is an 87 cent profit. 
	
 *Section 4
	Day 1:
	Position
	AA-long 1000 shares
	BB-short 1000 shares
	Profit
	AA-n/a
	BB-n/a
	
	Day 2:
	Position
	AA-even
	BB- short 1000 shares
	CC- long 1000 shares
	Profit
	AA-loss $600
	BB-n/a
	CC-n/a
	
	Day 3:
	Same as day 2
	
	Day 4:
	Position
	AA- even
	BB- even
	CC- even
	Profit
	AA- loss $600
	BB- profit $1300
	CC- loss $700
	
	Day 5:
	same as day 4
	
	Total profit of AA+BB+CC = -600+1300-700=0
	
 *Section 5
  5.1 
  i=1: paid 2.2
  i=2: received .8
  i=3: paid 2
  i=4: paid .8
  i=5 paid 2
  total amount paid 105.5
  
  5.2
  i=1: received .8
  i=2: received 2.4
  i=3: received .5
  i=4: paid .9
  i=5: 0 paid/received
  total amount paid 105.5
  
  5.3
  Question 1: No, not if the investor holds the futures contract until the end
  Question 2: No, not if the investor holds the futures contract until the end
  
  5.5
  i=1: received .8
  i=2: paid 1.2
  i=3: received .7
  i=4: paid 1.6
  i=5 paid 1.9
  sells on day 2: loss .4
  sells on day 3: profit .3
  
  5.6
  same answers as 5.5
  
  5.7
  Question: no
 */

