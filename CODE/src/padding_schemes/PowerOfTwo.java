package padding_schemes;
import java.math.BigInteger;

/**
 * Calculates biggest power of two, 
 * that is smaller than given prime.
 * NOTE! Class assumes that it gets a prime
 * as a parameter.
 * 
 * @author Jukka Tuominen
 *
 */
public class PowerOfTwo {

	private BigInteger result = BigInteger.ONE;

	/**
	 * Calculates biggest power of two < given prime. 
	 * @param prime BigInteger
	 * @return power_of_two BigInteger
	 */
	public BigInteger calculate(BigInteger prime){
		result=BigInteger.ONE;
		return calc(prime);
	}

	/**
	 * Private method for calculate.
	 * Calculates biggest power of two < given prime. 
	 * @param pri BigInteger
	 * @return BigInteger
	 */
	private BigInteger calc(BigInteger pri){
		while((result.compareTo(pri))<0){
			result=power(result);
		}
		if ((result.compareTo(pri))==0){
			return result;
		}
		return result.shiftRight(1);
	}

	/**
	 * Power of two.
	 * @param k BigInteger
	 * @return BigInteger
	 */
	private BigInteger power(BigInteger k){
		// left shifting equals power of two
		return k.shiftLeft(1);
	}
	
}