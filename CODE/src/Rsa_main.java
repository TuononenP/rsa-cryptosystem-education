import java.math.BigInteger;  
//import java.security.KeyPair;
import java.util.Scanner;

import keypair.*;

/**
 * @author Petri Tuononen
 * Project: RSA encryption/decryption software for educational use.
 * @since 18.1.2009
 * @version 0.15
 */
public class Rsa_main {  
      
    private RsaPublicKey publicKey;
    private RsaPrivateKey privateKey;
//    private KeyPair keyPair;
      
    /**
     * Default constructor.
     */
    public Rsa_main() {  
    	GenerateKeys gen = new GenerateKeys(512);
    	publicKey = gen.getPublicKey();
    	privateKey = gen.getPrivateKey();
//    	StoreKeyPair storeKeys = new StoreKeyPair(publicKey, privateKey);
//    	keyPair = storeKeys.getKeyPair();
    }  
    
    public RsaPublicKey getPublicKey() {
    	return publicKey;
    }
    
    public RsaPrivateKey getPrivateKey() {
    	return privateKey;
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
    
//    /**
//     * Calculate the amount of time used to initialize keys with different bitsizes.
//     */
//    public void testInitSpeed() {
//    	System.out.println("Testing computer's speed to initialize keys with different bitsizes.");
//    	System.out.println("512 bit, 1024 bit and 2048 bit key initialization times are calculated.");
//    	System.out.println("The test may take some time depending on the computer hardware used.\n");
//    	initTime(512);
//    	initTime(1024);
//    	initTime(2048);
//    }

//    /**
//     * Calculate time to create keys with given bitsize.
//     * 
//     * @param bitsize
//     */
//    public void initTime(int bitsize) {
//    	System.out.println("Initializing " +bitsize+ " bit keys...");
//    	long startTime = System.currentTimeMillis();
//    	createKeys(bitsize);
//    	long timeElapsed = System.currentTimeMillis()-startTime;
//    	System.out.println("Initialization completed in "+timeElapsed+" ms.\n");
//    }
    
    public static void main (String[] args) {  
    	//Generate keys
    	long startTime = System.currentTimeMillis();
    	System.out.println("Initializing keys...");
    	Rsa_main rsa = new Rsa_main();  
//    	rsa.testInitSpeed();
    	System.out.print("Initialization completed ");
    	long timeElapsed = System.currentTimeMillis()-startTime;
    	System.out.println("in "+timeElapsed+" ms.");
    	BigInteger e = rsa.getPrivateKey().getPublicExponent();
    	BigInteger d = rsa.getPrivateKey().getPrivateExponent();
    	BigInteger n = rsa.getPrivateKey().getN();
    	
    	System.out.println("e: "+e);
    	System.out.println("d: "+d);
    	System.out.println("n: "+n);
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