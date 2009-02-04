package padding_schemes;
import java.math.BigInteger;

/**
 * This is for power dividing
 * @author Jukka Tuominen
 *
 */
public class DividedPower extends PowerOfTwo {

	public DividedPower(){

	}
	/**
	 * Divides power
	 * @param prime
	 * @return StringBuilder containing divided power
	 */
	public StringBuilder powerDivison(BigInteger prime){
		StringBuilder pwr = new StringBuilder();
		BigInteger tulos,stop = BigInteger.ZERO;
		tulos=calculate(prime);
		stop=stop.add(prime);
		pwr.append(tulos);
		stop=stop.subtract(tulos);
		while (stop.compareTo(BigInteger.ZERO)>0){
			tulos=calculate(stop);
			stop=stop.subtract(tulos);
			pwr.insert(0,tulos+"+");
		}
		return pwr;
	}


}
