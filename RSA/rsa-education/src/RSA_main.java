import java.math.BigInteger;  
import java.security.SecureRandom;
import java.util.Scanner;

/**
 * 
 * @author Petri Tuononen
 * Project: RSA encryption/decryption software for educational usage.
 * @since 18.1.2009
 * @version 0.10
 */
public class RSA_main {  
      
    private BigInteger p, q, n, phi, e, d;  
    private int bitlength = 1024;  
//    private int blocksize = 256; //blocksize in byte //not in use yet, will improve security dramatically 
      
    /**
     * Constructor.
     */
    public RSA_main() {  
    	createKeys(bitlength);
    }  
    
    /**
     * Constructor
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
    public void createKeys(int bitlength) {
    	SecureRandom r = new SecureRandom();
        
        // get two big primes  
    	/*
    	 * probability that the number returned is not prime is < 1 / 2^100 (where 100 is the second parameter).
    	 */
    	p = new BigInteger(bitlength, 100, r); 
        q = new BigInteger(bitlength, 100, r);  
          
        // calculate modulo  
        n = p.multiply(q);  
        setN(n);
          
        // phi is needed to compute the exponent for encryption  
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));  
          
//        // compute the exponent necessary for encryption (private key)  
//        e = BigInteger.probablePrime(bitlength/2, r);  
//        
//        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 ) {  
//            e.add(BigInteger.ONE);  
//        }  
//        setE(e);
  
        e = new BigInteger("3");
        
        while(phi.gcd(e).intValue() > 1) e = e.add(new BigInteger("2"));
        
        // compute public key  
        d = e.modInverse(phi);
        setD(d);
        
        // destroy p & q for security reasons
        p = null;
        q = null;
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
     * Converts a byte array to String  
     * @param encrypted		Encrypted byte array
     * @return 				Encrypted String.
     */  
    private static String bytesToString(byte[] encrypted) {  
        String test = "";  
        for (byte b : encrypted) {  
            test += Byte.toString(b);  
        }  
        return test;  
    }  
      
    /** 
     * Encrypt byte array
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
     * Calculates the amount of time used to initialize keys with different bit sizes.
     */
    public void testInitSpeed() {
    	System.out.println("Testing computer's speed to initialize keys with different bit sizes.");
    	System.out.println("512 bit, 1024 bit and 2048 bit key initialization times are calculated.");
    	System.out.println("The test may take some time depending on the computer hardware used.\n");
    	
    	System.out.println("Initializing 512 bit keys...");
    	long startTime = System.currentTimeMillis();
    	createKeys(512);
    	long timeElapsed = System.currentTimeMillis()-startTime;
    	System.out.println("Initialization completed in "+timeElapsed+" ms.\n");
    	
    	System.out.println("Initializing 1024 bit keys...");
    	long startTime2 = System.currentTimeMillis();
    	createKeys(1024);
    	long timeElapsed2 = System.currentTimeMillis()-startTime2;
    	System.out.println("Initialization completed in "+timeElapsed2+" ms.\n");
    	
    	System.out.println("Initializing 2048 bit keys...");
    	long startTime3 = System.currentTimeMillis();
    	createKeys(2048);
    	long timeElapsed3 = System.currentTimeMillis()-startTime3;
    	System.out.println("Initialization completed in "+timeElapsed3+" ms.\n");
    }
    
    public static void main (String[] args) {  
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
  
        // encrypt  
        byte[] encrypted = rsa.encrypt(msg.getBytes(), e, n);                    
        System.out.println("Encrypted String in Bytes: " + bytesToString(encrypted));  
          
        // decrypt  
        byte[] decrypted = rsa.decrypt(encrypted, d, n);        
        System.out.println("Decrypted String in Bytes: " +  bytesToString(decrypted));  
        System.out.println("Decrypted String: " + new String(decrypted));  
    }  
      
}  