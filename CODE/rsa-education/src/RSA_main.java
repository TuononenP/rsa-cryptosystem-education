import java.math.BigInteger;  
import java.util.Scanner;

/**
 * 
 * @author Petri Tuononen
 * Project: RSA encryption/decryption software for educational usage.
 * @since 18.1.2009
 * @version 0.15
 */
public class RSA_main {  
      
    private BigInteger p, q, n, phi, e, d;  
    private int bitsize = 512;  
      
    /**
     * Default constructor.
     */
    public RSA_main() {  
    	createKeys(bitsize);
    }  
    
    /**
     * Constructor.
     * 
     * @param e		Public exponent
     * @param d		Private exponent
     * @param n		Modulus
     */
    public RSA_main(BigInteger e, BigInteger d, BigInteger n) {  
        this.e = e;  
        this.d = d;  
        this.n = n;  
    }  
    
    /** 
     * Create public and private keys.
     */  
    public void createKeys(int bitsize) {
    	Miller_Rabin_primality_test primeTest = new Miller_Rabin_primality_test();
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
        setN(n);
          
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
        setE(e);
        
        while(phi.gcd(e).intValue() > 1) {
        	e = e.add(new BigInteger("2"));
        }
        
        //Compute private exponent  
        d = e.modInverse(phi);
        setD(d);
        
        //Destroy p & q for security reasons
        p = null;
        q = null;
    }
    
    /**
     * Test if prime p is equal to prime q.
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
    
    public void setE(BigInteger e) {
    	this.e = e;
    }
    
    public BigInteger getE() {
    	return e;
    }

    public void setD(BigInteger d) {
    	this.d = d;
    }
    
    public BigInteger getD() {
    	return d;
    }
    
    public void setN(BigInteger n) {
    	this.n = n;
    }
    
    public BigInteger getN() {
    	return n;
    }
      
    /** 
     * Convert a byte array to String.
     *  
     * @param encrypted		Encrypted byte array
     * @return 				Encrypted String.
     */  
    private static String bytesToString(byte[] encrypted) {  
        String str = "";  
        for (byte b : encrypted) {  
            str += Byte.toString(b);  
        }  
        return str;  
    }  
      
    /** 
     * Encrypt byte array.
     * 
     * @param message	Message to be enrypted
     * @return 			Encrypted byte array
     */  
    public byte[] encrypt(byte[] message, BigInteger e, BigInteger n) {       
        return (new BigInteger(message)).modPow(e, n).toByteArray();  
    }  
      
    /** 
     * Decrypt byte array 
     * @param message	Message to be decrypted
     * @return 			Decrypted byte array
     */  
    public byte[] decrypt(byte[] message, BigInteger d, BigInteger n) {  
        return (new BigInteger(message)).modPow(d, n).toByteArray();  
    }  
    
    /**
     * Calculate the amount of time used to initialize keys with different bitsizes.
     */
    public void testInitSpeed() {
    	System.out.println("Testing computer's speed to initialize keys with different bitsizes.");
    	System.out.println("512 bit, 1024 bit and 2048 bit key initialization times are calculated.");
    	System.out.println("The test may take some time depending on the computer hardware used.\n");
    	initTime(512);
    	initTime(1024);
    	initTime(2048);
    }

    /**
     * Calculate time to create keys with given bitsize.
     * 
     * @param bitsize
     */
    public void initTime(int bitsize) {
    	System.out.println("Initializing " +bitsize+ " bit keys...");
    	long startTime = System.currentTimeMillis();
    	createKeys(bitsize);
    	long timeElapsed = System.currentTimeMillis()-startTime;
    	System.out.println("Initialization completed in "+timeElapsed+" ms.\n");
    }
    
    public static void main (String[] args) {  
    	//Generate keys
    	long startTime = System.currentTimeMillis();
    	System.out.println("Initializing keys...");
    	RSA_main rsa = new RSA_main();  
//    	rsa.testInitSpeed();
    	System.out.print("Initialization completed ");
    	long timeElapsed = System.currentTimeMillis()-startTime;
    	System.out.println("in "+timeElapsed+" ms.");
    	BigInteger e = rsa.getE();
    	BigInteger d = rsa.getD();
    	BigInteger n = rsa.getN();
//    	System.out.println("e: "+e);
//    	System.out.println("d: "+d);
//    	System.out.println("n: "+n);
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the text to be encrypted: ");
        String msg = sc.nextLine();
        System.out.println("Encrypting String: " + msg);  
        System.out.println("String in Bytes: " + bytesToString(msg.getBytes()));  
  
        //Encrypt  
        byte[] encrypted = rsa.encrypt(msg.getBytes(), e, n);                    
        System.out.println("Encrypted String in Bytes: " + bytesToString(encrypted));  
          
        //Decrypt  
        byte[] decrypted = rsa.decrypt(encrypted, d, n);        
        System.out.println("Decrypted String in Bytes: " +  bytesToString(decrypted));  
        System.out.println("Decrypted String: " + new String(decrypted));  
    }  
      
}  