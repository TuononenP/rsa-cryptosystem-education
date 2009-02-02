import java.math.BigInteger;

/**
 * Calculates biggest power of two, 
 * that is smaller than given prime.
 * NOTE! Class assumes that it will get prime.
 * 
 * @author Jukka Tuominen
 *
 */
public class PowerOfTwo {

	private BigInteger result = BigInteger.ONE;
	private int round = 1;
	
	/**
	 * Calculates biggest power of two < given prime. 
	 * @param prime 
	 * @return power_of_two
	 */
	public BigInteger calculate(BigInteger prime){
		
		if (result.compareTo(prime)<0){  // compare power against result until result is bigger
			round++;
			result = power(result);
			calculate(prime);
		}
		return result.shiftRight(1);
			
	}
	
	private BigInteger power(BigInteger k){ // left shifting equals power of two
		return k.shiftLeft(1);
	}
}
