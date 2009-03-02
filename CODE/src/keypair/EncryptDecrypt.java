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

/**
 * RSA cryptosystem.
 * Encrypts/decrypts one number at a time. 
 * 
 * @author Petri Tuononen
 * @since 1.2.2009
 */
public class EncryptDecrypt {

    /**
     * Encrypts one encoded int.
     * 
     * @param encoded One encoded int.
     * @param e Public exponent.
     * @param n Modulus.
     * @return BigInteger Encrypted BigInteger.
     */
    public BigInteger encrypt(int encoded, BigInteger e, BigInteger n) {       
    	BigInteger bIntMsg = new BigInteger(Integer.toString(encoded)); //int msg to BigInteger
    	BigInteger c = bIntMsg.modPow(e, n); //encrypted
    	return c;
    }  
    
    /**
     * Decrypts one BigInteger and returns BigInteger. 
     * 
     * @param encrypted Encrypted BigInteger.
     * @param d Private exponent.
     * @param n	Modulus.
     * @return BigInteger Decrypted BigInteger.
     */
    public BigInteger decrypt(BigInteger encrypted, BigInteger d, BigInteger n) {  
    	BigInteger m = encrypted.modPow(d, n); //decrypted
    	return m;
    }  
    
    /**
     * Decrypts one BigInteger and returns int. 
     * 
     * @param encrypted Encrypted BigInteger.
     * @param d Private exponent.
     * @param n Modulus.
     * @return Decrypted int.
     */
    public int decryptToInt(BigInteger encrypted, BigInteger d, BigInteger n) {  
    	BigInteger m = encrypted.modPow(d, n); //decrypted
    	return m.intValue();
    } 
}
