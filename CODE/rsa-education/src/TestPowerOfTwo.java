

import java.math.BigInteger;


public class TestPowerOfTwo {

	/**
	 * 
	 *  @author Jukka Tuominen
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigInteger testi = new BigInteger("113");
		DividedPower dp = new DividedPower();
		System.out.println(dp.calculate(testi));
		System.out.println(dp.powerDivison(testi));
	}

}
