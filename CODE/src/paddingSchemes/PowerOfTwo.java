/*
	Copyright (C) 2009 Jukka Tuominen

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package paddingSchemes;
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