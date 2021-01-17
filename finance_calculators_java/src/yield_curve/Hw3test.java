package yield_curve;

public class Hw3test 
{
	public static void main(String [] args)
	{
		double [] inputYields = {6.0, 6.2, 6.4};

		YieldCurve testCurve = new YieldCurve(inputYields);
	
		for(int i=0; i< testCurve.lenSpotCurve(); i++) 
			{
				System.out.println("d"+(i+1)+": "+testCurve.DiscountFactor(i)+"    r"+(i+1)+": "+testCurve.SpotRate(i));
				
			}
		
		double [] interpTimes = {.7, 1, 1.45};
		for(int i=0; i<interpTimes.length; i++)
		{
			System.out.println("t_interp: "+interpTimes[i]+"    rLin: "+testCurve.lin_interp(interpTimes[i])+"    rCfr: "+testCurve.cfr_interp(interpTimes[i]));
		}
		/*int index =0;
		System.out.println("len par curve: "+testCurve.lenParCurve());
		System.out.println("len spot curve: "+testCurve.lenSpotCurve());
		System.out.println("Discount factor at index "+index+": "+testCurve.DiscountFactor(index));
		System.out.println("Spot rate at index: "+index+":"+testCurve.SpotRate(index));
		System.out.println("Spot tenor at index: "+index+": "+testCurve.SpotTenor(index));
		System.out.println("Par Yield at index: "+index+": "+testCurve.ParYield(index));
		System.out.println("Par Tenor at index: "+index+": "+testCurve.ParTenor(index));*/
	}
	
}
