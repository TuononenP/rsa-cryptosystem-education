package padding_schemes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

import keypair.*;

/**
 * Padding scheme for cryptography.
 * 
 * Divides plaintext into blocks of 3 letters.
 * Encodes text blocks to numbers.
 * Decodes numbers back to text blocks.
 * Restructures text blocks to plaintext.
 * Prints full encoding/decoding formulas.
 * Prints full encoding+encrypting formulas from plaintext.
 * Prints full decrypting+decoding formulas from encrypted text.
 * Secure method to allow encode+encrypt to print only encrypted text. 
 * Secure method to allow decrypt+decode to print only plaintext. 
 * 
 * @author Petri Tuononen
 * @since 25.1.2009
 */
public class Blocks_Of_3_Padding {

	//variable declarations and initializations
	private AlphabetNum alphaNum = new AlphabetNum();
	private final int BASE = 26;
	private final String pow_zero = "\u00B0";
	private final String pow_one = "\u00B9";
	private final String pow_two = "\u00B2";
	private final double BASE_double = Double.parseDouble(Integer.toString(BASE)); 
	private int[] products = new int[3];
	private StringBuilder sB;
	private int firstProduct = 0, secondProduct = 0, lastProduct = 0;
	private double dPart, dPart2, division, temp;
	
	/**
	 * Divide plaintext into 3 letter blocks.
	 * @param msg	Plaintext to be divided into 3 letter blocks.
	 * @return		Table containing one 3 letter block per cell.
	 */
	private String[] plaintextToBlocks(String message) {
		//replace white spaces with x
		message = message.replace(" ", "X");
		
		sB = new StringBuilder(message);
		long length = sB.length();
		
		//calculate remainder when divided by three
		int remainder = (int) (length%3);
		//if remainder == 2 --> add x to the end of message
		if (remainder == 2) {
			sB.append("X");
		}
		//if remainder == 1 --> add xx to the end message
		if (remainder == 1) {
			sB.append("XX");
		}
		
		//get new length
		length = sB.length();
		
		//divide phrase to blocks of three
		String[] divided = new String[(int) (length/3)];
		for (int start=0, end=3, i=0; end<=length; start+=3, end+=3, i++) {
			divided[i] = sB.substring(start, end);
		}
		
		return divided;
	}
	
//	/**
//	 * Prints text in three letter blocks.
//	 * e.g. ATTACK AT SEVEN --> ATT ACK XAT XSE VEN
//	 * @param message Text in three letter blocks.
//	 */
//	public void printBlocktext(String message) {
//		for (String s : plaintextToBlocks(message)) {
//			System.out.println(s);
//		}
//		System.out.println("");
//	}
	
	/**
	 * Converts blocks of three letters to plaintext.
	 * 
	 * @param blocks Three letter blocks in a String array.
	 * @return	Plaintext.
	 */
	private String blocksToPlaintext(String[] blocks) {
		sB = new StringBuilder();
		//append string array to stringbuilder
		for (int i=0; i < blocks.length; i++) {
			sB.append(blocks[i]);
		}
		String plaintext = sB.toString();
		//replace x with white spaces
		plaintext = plaintext.replace("X", " ");
		//remove white spaces from the end
		plaintext.trim();
		return plaintext;
	}
	
	/**
	 * Encodes three letter text block to a number.
	 * Let A=0, B=1,...,Z=25
	 * 1st letter*BASE^2 + 2nd letter*BASE^1 + 3rd letter*BASE^0
	 * @param text	 Three letter string.
	 * @return Number of encoded text.
	 */
	private int encodeBlock(String block) {
		int num = (int) (alphaNum.getNum(block.substring(0, 1))*Math.pow(BASE, 2)) +
				  (int) (alphaNum.getNum(block.substring(1, 2))*Math.pow(BASE, 1)) +
				  (int) (alphaNum.getNum(block.substring(2, 3))*Math.pow(BASE, 0));
		return num;
	}
	
	/**
	 * Return a formula as a text.
	 * @param text	Three letter string.
	 * @return Mathematical formula.
	 */
	private String getEncodeFormula(String text) {
		String formula;
		setProductNumbers(text);
		formula = products[0]+"*"+BASE+pow_two+" + "+products[1]+"*"+BASE+pow_one+" + "+products[2]+"*"+BASE+pow_zero;
		return formula;
	}
	
	/**
	 * Store products that represents each character of three letter text block. 
	 * 
	 * @param text Three letter text block.
	 */
	private void setProductNumbers(String text) {
		products[0] = alphaNum.getNum(String.valueOf(text.charAt(0)));
		products[1] = alphaNum.getNum(String.valueOf(text.charAt(1)));
		products[2] = alphaNum.getNum(String.valueOf(text.charAt(2)));
	}
	
	/**
	 * Return full formula as a text.
	 * e.g. ATT = 0*26^2 + 19*26^1 + 19*26^0 = 513
	 * @param text	Three letter string.
	 * @return Whole mathematical formula.
	 */
	private String getFullEncodeFormula(String text) {
		return text+" = "+getEncodeFormula(text)+" = "+encodeBlock(text);
	}
	
	/**
	 * Rounds a number upwards to closest integer.
	 * @param nro Number to round.
	 * @param desPlace Defines number of desimals used to round. 
	 * @return Rounded number.
	 */
	public double roundDouble(double nro, int desPlace) {
	    BigDecimal bd = new BigDecimal(nro);
	    bd = bd.setScale(desPlace,BigDecimal.ROUND_UP);
	    nro = bd.doubleValue();
	    return nro;
	}
	
	/**
	 * Decodes a number back to three letter text block.
	 * 
	 * @param encoded Text block encoded to number.
	 * @return three letter text block.
	 */
	private String decode(int encoded) {
		sB = new StringBuilder();
		//divide encoded num with base number
		division = encoded/BASE_double;
		//check if the result is < 26
		if (division < BASE_double) { //two products
			firstProduct = 0;
			//separate integer and desimal part of double
			secondProduct = (int) division; //integer part
			dPart = division-secondProduct; //desimal part
			lastProduct = (int) roundDouble(BASE*dPart, 1);
		}
		else { //three products
			//result after the first division is >26 so another division has to be done
			division = division/BASE_double;
			/*
			 * Now after second division the result should be <26.
			 * Text block 'ZZZ' has the biggest number when encoded (17575) and [17575/(26*26)] < 26.
			 */
			if (division < BASE) { //to be 100% sure that the division result is <26
				firstProduct = (int) division; //we get first product
				dPart = division-firstProduct; //desimal part
				temp = dPart*26;
				secondProduct = (int) temp;
				dPart2 = temp - secondProduct;
				lastProduct = (int) roundDouble(BASE*dPart2, 1);
			}
		}
	    //save products to table
	    products[0] = firstProduct;
	    products[1] = secondProduct;
	    products[2] = lastProduct;
		//print result
		for (int i=0; i < products.length; i++) { 
			sB.append(alphaNum.getLetter(products[i])); //append letters
		}
		return sB.toString();
	}
	
	/**
	 * Decodes a number back to three letter text block.
	 * Prints whole mathematical formulas of each and every step.
	 * 
	 * @param encoded Text block encoded to number.
	 */
	private void printBlockDecode(int encoded) {
		sB = new StringBuilder();
		//divide encoded num with base number
		division = encoded/BASE_double;
		//check if the result is < 26
		if (division < BASE) { //two products
			firstProduct = 0;
			//separate integer and desimal part of double
			secondProduct = (int) division; //integer part
			dPart = division-secondProduct; //desimal part
			lastProduct = (int) roundDouble(BASE*dPart, 1);
			System.out.println((int)encoded+" = ("+(int)encoded+"/"+BASE+")*"+BASE);
			System.out.println((int)encoded+" = "+division+"*"+BASE);
			System.out.println((int)encoded+" = "+secondProduct+"*"+BASE+" + "+dPart+"*"+BASE);
			System.out.println((int)encoded+" = "+secondProduct+"*"+BASE+" + "+lastProduct+"*"+BASE+pow_zero);
		}
		else { //three products
			//result after the first division is >26 so another division has to be done
			System.out.println(encoded+" = ("+encoded+"/"+BASE+")*"+BASE);
			System.out.println(encoded+" = "+roundDouble(division, 3)+"*"+BASE);
			System.out.println(encoded+" = ("+roundDouble(division, 3)+"/"+BASE+")*"+BASE+pow_two);
			division = division/BASE_double;
			/*
			 * Now after second division the result should be <26.
			 * Text block 'ZZZ' has the biggest number when encoded (17575) and [17575/(26*26)] < 26.
			 */
			if (division < BASE) { //to be 100% sure that the division result is <26
				System.out.println(encoded+" = "+roundDouble(division, 3)+"*"+BASE+pow_two);
				firstProduct = (int) division; //we get first product
				dPart = division-firstProduct; //desimal part
				System.out.println(encoded+" = "+firstProduct+"*"+BASE+pow_two+" + "+roundDouble(dPart, 3)+"*"+BASE+pow_two);
				System.out.println(encoded+" = "+firstProduct+"*"+BASE+pow_two+" + ("+roundDouble(dPart, 3)+"*"+BASE+")*"+BASE);
				temp = dPart*26;
				System.out.println(encoded+" = "+firstProduct+"*"+BASE+pow_two+" + "+roundDouble(temp, 3)+"*"+BASE);
				secondProduct = (int) temp;
				dPart2 = temp - secondProduct;
				System.out.println(encoded+" = "+firstProduct+"*"+BASE+pow_two+" + "+secondProduct+"*"+BASE+" + "+roundDouble(dPart2, 3)+"*"+BASE);
				lastProduct = (int) roundDouble(BASE*dPart2, 1);
				System.out.println(encoded+" = "+firstProduct+"*"+BASE+pow_two+" + "+secondProduct+"*"+BASE+" + "+lastProduct+"*"+BASE+pow_zero);
			}
		}
	    //save products to table
	    products[0] = firstProduct;
	    products[1] = secondProduct;
	    products[2] = lastProduct;
		//print result
		System.out.println("Products: ["+products[0]+", "+products[1]+", "+products[2]+"]");
		//print number to alphabet counterparts
		System.out.println(products[0]+"="+alphaNum.getLetter(products[0])+", "
						  +products[1]+"="+alphaNum.getLetter(products[1])+", "
						  +products[2]+"="+alphaNum.getLetter(products[2]));
		
		for (int i=0; i < products.length; i++) { 
			sB.append(alphaNum.getLetter(products[i])); //append letters
		}
		System.out.println("Result: "+sB.toString());
	}
	
	/**
	 * Prints a full encoding part from one three letter block.
	 * @param block
	 */
	private void printBlockEncode(String block) {
		setProductNumbers(block);
		System.out.println(alphaNum.getLetter(products[0])+"="+products[0]
		             +", "+alphaNum.getLetter(products[1])+"="+products[1]
		             +", "+alphaNum.getLetter(products[2])+"="+products[2]);
		System.out.println(getFullEncodeFormula(block));
		System.out.println("");
	}
	
	private void printEncodeFormulas(String[] blocks) {
		//encode every block of three
		System.out.println("Encode: ");
		System.out.println("------------------------------------");
		//encode every block of three
		for (int i=0; i<blocks.length; i++) {
			printBlockEncode(blocks[i]);
		}
	}
	
	/**
	 * Print decoding from full number list.
	 * @param decoded Numbers with white spaces to be decoded.
	 */
	private void printDecodeFormulas(String decoded) {
		System.out.println("Decode: ");
		System.out.println("------------------------------------");
		/*
		 * 'String decoded' is numbers with spaces
		 * Numbers are split into string array cells.
		 */
		String[] numbersStr = decoded.split(" ");
		for (int i=0; i < numbersStr.length; i++) {
			printBlockDecode(Integer.parseInt(numbersStr[i]));
			System.out.println("");
		}
	}
	
	/**
	 * Encodes whole message into int array.
	 * @param msg	Message.
	 * @return	int array.
	 */
	private int[] encodeFullPhrase(String msg) {
		//plaintext is converted to blocks of three letters.
		String[] blocks = plaintextToBlocks(msg);
		int[] encoded = new int[blocks.length];
		int i = 0;
		//letter blocks are encoded to numbers.
		for (String s : blocks) {
			encoded[i] = encodeBlock(s);
			i++;
		}
		return encoded;
	}
	
	/**
	 * Encodes blocks of three letters.
	 * 
	 * @param blocks String array of blocks of three letters.
	 * @return int[].
	 */
	private int[] encodeBlocks(String[] blocks) {
		int[] encoded = new int[blocks.length];
		int i = 0;
		//letter blocks are encoded to numbers.
		for (String s : blocks) {
			encoded[i] = encodeBlock(s);
			i++;
		}
		return encoded;
	}
	
	/**
	 * Prints an array for encoded numbers consecutively.
	 * @param enc	Array of encoded numbers.
	 */
	private void printEncodedConsecutively(int[] enc) {
		for (int i : enc) {
			System.out.print(i+" ");
		}
		System.out.println("");
	}
	
	/**
	 * Decodes message of encoded numbers to blocks of three letters.
	 * @param msg Message.
	 * @return String array.
	 */
	private String[] decodeFullPhrase(String msg) {
		//encoded numbers are split into string array.
		String[] array = msg.split(" ");
		int i = 0;
		//numbers are decoded to three letter blocks.
		for (String s : array) {
			array[i] = decode(Integer.parseInt(s));
			i++;
		}
		return array;
	}
	
	/**
	 * Decodes a String of encoded numbers.
	 * 
	 * @param msg	Message to decode.
	 * @return Decoded message.
	 */
	private String decodeFullPhraseStr(String msg) {
		StringBuffer sb = new StringBuffer();
		//encoded numbers are split into string array.
		String[] array = msg.split(" ");
		//numbers are decoded to three letter blocks.
		for (String s : array) {
			sb.append(decode(Integer.parseInt(s))+" ");
		}
		return sb.toString();
	}
	
	/**
	 * Prints decoded message from multiple numbers.
	 * @param msg
	 */
	public void printDecodeFullPhrase(String msg) {
		String[] dec = decodeFullPhrase(msg);
		for (String s : dec) {
			System.out.print(s+" ");
		}
		System.out.println("");
	}
	
	/**
	 * Encrypts int array of encoded numbers to ArrayList.
	 * Encrypts using RSA cryptosystem.
	 * 
	 * @param encoded int array of encoded numbers.
	 * @param e Public exponent.
	 * @param n Modulus.
	 * @return ArrayList.
	 */
	private ArrayList<BigInteger> encryptBlocksOfThree(int[] encoded, BigInteger e, BigInteger n) {
		Encrypt_Decrypt encDec = new Encrypt_Decrypt();
		ArrayList<BigInteger> arrayL = new ArrayList<BigInteger>();
		for (int i : encoded){ //goes through int array cells of encoded numbers
			/*
			 * It is still important to keep track of blocks of three, so
			 * encoded number in each cell are encrypted to BigInteger and 
			 * they are kept in the same arraylist index as their array cell counterparts.
			 */
			//encrypts and stores one three letter block of encoded numbers.
			arrayL.add(encDec.encrypt(i, e, n)); 
		}
		return arrayL;
	}
	
	/**
	 * Decrypts ArrayList of encrypted BigInteger numbers to a BigInteger array.
	 * Decrypts using RSA cryptosystem.
	 * 
	 * @param encrypted ArrayList of encrypted BigInteger numbers. 
	 * @param d Private exponent.
	 * @param n Modulus.
	 * @return BigInteger[].
	 */
	private BigInteger[] decryptBlocksOfThree(ArrayList<BigInteger> encrypted, BigInteger d, BigInteger n) {
		Encrypt_Decrypt encDec = new Encrypt_Decrypt();
		
		Iterator<BigInteger> iter = encrypted.iterator();
		BigInteger[] decrypted = new BigInteger[encrypted.size()];
		int i=0;
		while (iter.hasNext()) {
			decrypted[i] = encDec.decrypt(iter.next(), d, n);
			i++;
	    }
		return decrypted;
	}
	
	/**
	 * Prints encoding and encrypting formulas.
	 * Encrypts using RSA cryptosystem.
	 * 
	 * @param plaintext	Plaintext message to be encoded and encrypted.
	 * @param e Public exponent.
	 * @param n Modulus.
	 */
	public void printEncodeAndEncryptBlocksOfThree(String plaintext, BigInteger e, BigInteger n) {
		//Encoding part
		//----------------
		System.out.println(plaintext);
		//convert plaintext to blocks of three letters
		String[] blocks = plaintextToBlocks(plaintext);
		//print blocks of three letters
		printArray(blocks);
		//print encoding formula from every block
		printEncodeFormulas(blocks);
		System.out.print("Encoded: ");
		//encode blocks
		int[] encoded = encodeBlocks(blocks);
		//print encoded numbers consecutively
		printEncodedConsecutively(encoded);
		
		//Encrypting part
		//----------------
		ArrayList<BigInteger> arrayL = new ArrayList<BigInteger>();
		//encrypt blocks to arraylist
		arrayL = encryptBlocksOfThree(encoded, e, n);
		System.out.print("Encrypted: ");
		//print arraylist contents consecutively.
		Iterator<BigInteger> iter = arrayL.iterator();
			while (iter.hasNext()) {
				BigInteger encrypted = iter.next();
				System.out.print(encrypted + " ");
		    }
	}

	/**
	 * Prints decrypting and decoding formulas.
	 * Decrypts using RSA cryptosystem.
	 * 
	 * @param encryptedText Encrypted text.
	 * @param d Private exponent.
	 * @param n Modulus.
	 */
	public void printDecryptAndDecodeBlocksOfThree(String encryptedText, BigInteger d, BigInteger n) {
		//Decrypting part
		//----------------
		System.out.println("Encrypted: "+encryptedText);
		ArrayList<BigInteger> encrypted = stringToBiArrayList(encryptedText);
		BigInteger[] decryptedArray = decryptBlocksOfThree(encrypted, d, n);
		sB = new StringBuilder();
		for (BigInteger bi : decryptedArray){
			sB.append(bi+" ");
		}
		String decrypted = sB.toString();
		System.out.println("Decrypted: "+decrypted+"\n");

		//Decoding part
		//----------------
		//print decoding formulas from every encoded number
		printDecodeFormulas(decrypted);
		//get decoding result
		String decoded = decodeFullPhraseStr(decrypted);
		System.out.println("Decoded:");
		System.out.println(decoded);
		//remove white spaces
		String decoded_no_ws = decoded.replace(" ", "");
		System.out.println(decoded_no_ws);
		//remove x letters
		String decoded_final = decoded_no_ws.replaceAll("X", " ");
		System.out.println(decoded_final);
	}
	
	/**
	 * Encodes and encrypts plaintext.
	 * Prints only encrypted text.
	 * Encrypts using RSA cryptosystem.
	 * 
	 * @param plaintext
	 * @param e Public exponent.
	 * @param n Modulus.
	 */
	public void encodeAndEncrypt_secure(String plaintext, BigInteger e, BigInteger n) {
		Iterator<BigInteger> iter = encryptBlocksOfThree(encodeFullPhrase(plaintext), e, n).iterator();
		while (iter.hasNext()) {
			System.out.print(iter.next()+" ");
		}
	}
	
	/**
	 * Decrypts and decodes encrypted text.
	 * Prints only decoded text.
	 * Decrypts using RSA cryptosystem.
	 * 
	 * @param encrypted Encrypted text.
	 * @param d Private exponent.
	 * @param n Modulus.
	 */
	public void decryptAndDecode_secure(String encrypted, BigInteger d, BigInteger n) {
		sB = new StringBuilder();
		for (BigInteger bi : decryptBlocksOfThree(stringToBiArrayList(encrypted), d, n)){
			sB.append(bi+" ");
		}
		System.out.println(blocksToPlaintext(decodeFullPhraseStr(sB.toString()).split(" ")));
	}
	
	/**
	 * Returns encrypted text.
	 * 
	 * @param plaintext Plaintext.
	 * @param e Public exponent.
	 * @param n Modulus.
	 * @return
	 */
	private String getEncrypted(String plaintext, BigInteger e, BigInteger n) {
		Iterator<BigInteger> iter = encryptBlocksOfThree(encodeBlocks(plaintextToBlocks(plaintext)), e, n).iterator();
		sB = new StringBuilder();
		while (iter.hasNext()) {
			sB.append(iter.next()+" ");
		}
		return sB.toString();
	}
	
	/**
	 * Converts String to ArrayList<BigInteger>.
	 * 
	 * @param msg String message.
	 * @return ArrayList<BigInteger>
	 */
	public ArrayList<BigInteger> stringToBiArrayList(String msg) {
		//String is split to array cells
		String[] array = msg.split(" ");
		ArrayList<BigInteger> al = new ArrayList<BigInteger>();
		for (String s : array) {
			al.add(new BigInteger(s));
		}
		return al;
	}
	
	/**
	 * Prints a String array consecutively with white spaces.
	 * @param array	String array.
	 */
	public void printArray(String[] array) {
		for (String s : array) {
			System.out.print(s+" ");
		}
		System.out.println("\n");
	}
	
	/**
	 * Tests methods.
	 * @param args
	 */
	public static void main(String[] args) {
		Blocks_Of_3_Padding pg = new Blocks_Of_3_Padding();
		GenerateKeys genKeys = new GenerateKeys(100);
		RsaPublicKey publicKey = genKeys.getPublicKey();
		RsaPrivateKey privateKey = genKeys.getPrivateKey();
		
		//print encode and encrypt
//		Load_Save_Key open = new Load_Save_Key();
//		Encode_Decode encDec = new Encode_Decode();
//		File pub_file = new File("C:\\Users\\Pepe\\Documents\\rsa_testi\\100bit_rsa.pub");
//		byte[] encoded = open.loadKeyFromFile(pub_file);
//		RsaPublicKey publicKey = encDec.decPublicKey(encoded); //decode byte array to form a public key
		pg.printEncodeAndEncryptBlocksOfThree("ATTACK AT SEVEN", publicKey.getE(), publicKey.getN());
		
		//print decrypt and decode
		System.out.println("\n\n");
//		File priv_file = new File("C:\\Users\\Pepe\\Documents\\rsa_testi\\100bit_rsa.priv");
//		byte[] encoded2 = open.loadKeyFromFile(priv_file);
//		RsaPrivateKey privateKey = encDec.decPrivateKey(encoded2);
		String encrypted = pg.getEncrypted("ATTACK AT SEVEN", publicKey.getE(), publicKey.getN());
		pg.printDecryptAndDecodeBlocksOfThree(encrypted, privateKey.getPrivateExponent(), privateKey.getModulus());
		
		System.out.println("\n");
		//secure methods
		System.out.println("Secure methods: \n");
		System.out.println("Encode and encrypt: ");
		pg.encodeAndEncrypt_secure("ATTACK AT SEVEN", publicKey.getE(), publicKey.getN());
		System.out.println("\n");
		System.out.println("Decrypt and decode: ");
		pg.decryptAndDecode_secure(encrypted, privateKey.getPrivateExponent(), privateKey.getModulus());
	}
}
