package padding_schemes;

import java.math.BigInteger;


public class TestPowerOfTwo {

	/**
	 * 
	 *  @author Jukka Tuominen
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PowerOfTwo p = new PowerOfTwo();
		long prime = 29;
		System.out.println(p.calculate(BigInteger.valueOf(prime)));
	}

}
