package padding_schemes;
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
	//private int round = 1;

	/**
	 * Calculates biggest power of two < given prime. 
	 * @param prime 
	 * @return power_of_two
	 */
	public BigInteger calculate(BigInteger prime){
		result=BigInteger.ONE;
		//round=1;
		return calc(prime);
	}

	private BigInteger calc(BigInteger pri){
		while((result.compareTo(pri))<0){
			result=power(result);
		}
		if ((result.compareTo(pri))==0){
			return result;
		}
		return result.shiftRight(1);
	}


	private BigInteger power(BigInteger k){ // left shifting equals power of two
		return k.shiftLeft(1);
	}
}
