package keypair;

import java.math.BigInteger;

public class GenerateKeys {

    private BigInteger p, q, n, phi, e, d;  
    private int bitsize;  
    GnuRsaPublicKey publicKey;
    GnuRsaPrivateKey privateKey;
	
    public GenerateKeys(int bitsize) {
    	this.bitsize = bitsize;
    	createKeys();
    }
    
    /** 
     * Create public and private keys.
     */  
    private void createKeys() {
    	Miller_Rabin_Primality_Test primeTest = new Miller_Rabin_Primality_Test();
    	/* Generate two big primes.
    	 * p and q aren't allowed to be equal for security reasons.
    	 * Generate until p and q are unique.
    	 */  
    	do {
        	p = primeTest.genPrime(bitsize);
        	q = primeTest.genPrime(bitsize);
    	} while (testPrimeAffinity(p, q));
    	
        //Calculate modulo  
        n = p.multiply(q);  
          
        /*
         * Computes the value of Euler's function.
         * Phi is needed to compute the exponent e for encryption.
         */
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));  
          
//        //Compute the exponent necessary for encryption (part of public key)  
//        e = primeTest.genPrime(bitsize/2);  
//        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 ) {  
//            e.add(BigInteger.ONE);  
//        }  
        
        //Fast method to get e
        e = new BigInteger("3");
        
        while(phi.gcd(e).intValue() > 1) {
        	e = e.add(new BigInteger("2"));
        }
        
        //Compute private exponent  
        d = e.modInverse(phi);
        
        //Save keys
        publicKey = new GnuRsaPublicKey(n, e);
        privateKey = new GnuRsaPrivateKey(p, q, e, d);
        
        //Destroy p & q for security reasons
        p = null;
        q = null;
    }
    
    public GnuRsaPublicKey getPublicKey() {
    	return publicKey;
    }
    
    public GnuRsaPrivateKey getPrivateKey() {
    	return privateKey;
    }
    
    /**
     * Test if prime p is equal to prime q.
     * They should not be that modulus n would be secure.
     * 
     * @param p		BigInteger prime
     * @param q		BigInteger prime
     * @return		Boolean
     */
    public boolean testPrimeAffinity(BigInteger p, BigInteger q) {
    	if (p.equals(q)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
}
