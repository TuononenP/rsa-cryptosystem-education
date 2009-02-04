package keypair;

import java.math.BigInteger;

public class Encrypt_Decrypt {

    /**
     * Encrypts one letter that is already encoded to a number.
     * 
     * @param encoded One letter of message encoded to a number.
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
     * Decrypts one BigInteger (that represents one letter) to int. 
     * 
     * @param encrypted Encrypted number.
     * @param d Private exponent.
     * @param n	Modulus.
     * @return int Decrypted int.
     */
    public int decrypt(BigInteger encrypted, BigInteger d, BigInteger n) {  
    	BigInteger m = encrypted.modPow(d, n); //decrypted
    	return Integer.parseInt(m.toString());
    }  
}
