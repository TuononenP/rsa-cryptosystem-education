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
