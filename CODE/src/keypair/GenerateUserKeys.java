/*
	Copyright (C) 2009 Petri Tuononen

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
package keypair;

import java.math.BigInteger;
import javax.swing.*;

/**
 * Generates keys based on the user input.
 * Tests suitability of primes p & q and public exponent e.
 * 
 * @author Petri Tuononen
 * @since 19.2.2009
 */
public class GenerateUserKeys {

    private RsaPublicKey publicKey;
    private RsaPrivateKey privateKey;
    
	/**
	 * Test user input for prime p & q and public exponent e.
	 * 
	 * @param p	Prime p.
	 * @param q	Prime q.
	 * @param e	Public exponent.
	 * @return boolean True, if input is ok.
	 */
	public boolean testInputEligibility(BigInteger p, BigInteger q, BigInteger e) {
		TestPrimality primeTester = new TestPrimality();
		if (!(primeTester.isPrime(p))) {
			return false;
		}
		if (!(primeTester.isPrime(q))) {
			return false;
		}
		if (p.equals(q)) {
			return false;
		}
		if (!(e.intValue() > 1)) {
			return false;
		}
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));  
		if (!(phi.gcd(e).intValue() == 1)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Shows an error dialog if p, q or e is not suitable.
	 * 
	 * @param frame
	 * @param p	Prime p.
	 * @param q	Prime q.
	 * @param e	Public exponent.
	 */
	public void showInputError(JFrame frame, BigInteger p, BigInteger q, BigInteger e) {
		TestPrimality primeTester = new TestPrimality();
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		if (!(primeTester.isPrime(p))) {
			JOptionPane.showMessageDialog(frame,
				    "P is not a prime.", "Input error",
				    JOptionPane.ERROR_MESSAGE);
		}
		else if (!(primeTester.isPrime(q))) {
			JOptionPane.showMessageDialog(frame,
				    "Q is not a prime.", "Input error",
				    JOptionPane.ERROR_MESSAGE);
		}
		else if (p.equals(q)) {
			JOptionPane.showMessageDialog(frame,
				    "P and Q should not be equal.", "Input error",
				    JOptionPane.ERROR_MESSAGE);
		}
		else if (!(e.intValue() > 1)) {
			JOptionPane.showMessageDialog(frame,
				    "E must be greater than 1.", "Input error",
				    JOptionPane.ERROR_MESSAGE);
		}
		else if (!(phi.gcd(e).intValue() == 1)) {
			JOptionPane.showMessageDialog(frame,
				    "E not suitable. gcd(e, (p-1)(q-1)) must equal one .", "Input error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Create public and private keys.
	 * Doesn't check if parameters are suitable.
	 * It is done by testInputEligibility() method.
	 * 
	 * @param p	Prime p.
	 * @param q	Prime q.
	 * @param e	Public exponent.
	 */
	public void createKeys(BigInteger p, BigInteger q, BigInteger e) {
		//Calculate the Euler's function.
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));  
		
		//Compute private exponent  
		BigInteger d = e.modInverse(phi);
        
        //Calculate modulo  
        BigInteger n = p.multiply(q);  
        
        //Create keys
        publicKey = new RsaPublicKey(n, e);
        privateKey = new RsaPrivateKey(p, q, e, d);
	}
	
    /**
     * Returns a public key instance.
     * @return publicKey.
     */
    public RsaPublicKey getPublicKey() {
    	return publicKey;
    }
    
    /**
     * Returns a private key instance.
     * @return privateKey.
     */
    public RsaPrivateKey getPrivateKey() {
    	return privateKey;
    }
	
}
