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
package paddingSchemes;

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
 * Returns full encoding/decoding formulas.
 * Returns full encoding+encrypting formulas from plaintext.
 * Returns full decrypting+decoding formulas from cryptotext.
 * Secure method to allow encode+encrypt to return only cryptotext. 
 * Secure method to allow decrypt+decode to return only plaintext. 
 * 
 * NOTE: Maximum value of a group (ZZZ) is 25*26^2 + 25*26^1 + 25*26^0 = 17575(=26^3-1)
 * 		 so a modulus n greater than this value is required.
 * 
 * @author Petri Tuononen
 * @since 25.1.2009
 */
public class BlocksOf3Padding {

	//variable declarations and initializations
	private AlphabetNum alphaNum = new AlphabetNum();
	private final int BASE = 26;
	private final String pow_zero = "\u00B0";
	private final String pow_one = "\u00B9";
	private final String pow_two = "\u00B2";
	private final String times = "\u00d7";
	private final String line = "--------------------------" +
								"--------------------------";
	private final double BASE_double = Double.parseDouble(Integer.toString(BASE)); 
	private int[] products = new int[3];
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
		
		StringBuilder sB = new StringBuilder(message);
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
	
	/**
	 * Returns text in three letter blocks.
	 * e.g. ATTACK AT SEVEN --> ATT ACK XAT XSE VEN
	 * @param plaintext Text in three letter blocks.
	 */
	public String getBlocktext(String plaintext) {
		StringBuilder sB = new StringBuilder();
		for (String s : plaintextToBlocks(plaintext)) {
			sB.append(s+ " ");
		}
		sB.append("\n");
		return sB.toString();
	}
	
	/**
	 * Converts blocks of three letters to plaintext.
	 * @param blocks Three letter blocks in a String array.
	 * @return	Plaintext.
	 */
	private String blocksToPlaintext(String[] blocks) {
		StringBuilder sB = new StringBuilder();
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
	 * Returns a formula as a text.
	 * @param text	Three letter string.
	 * @return Mathematical formula.
	 */
	private String getEncodeFormula(String text) {
		String formula;
		setProductNumbers(text);
		formula = products[0]+times+BASE+pow_two+" + "+products[1]+times+BASE+pow_one+" + "+products[2]+times+BASE+pow_zero;
		return formula;
	}
	
	/**
	 * Store products that represents each character of three letter text block. 
	 * @param text Three letter text block.
	 */
	private void setProductNumbers(String text) {
		products[0] = alphaNum.getNum(String.valueOf(text.charAt(0)));
		products[1] = alphaNum.getNum(String.valueOf(text.charAt(1)));
		products[2] = alphaNum.getNum(String.valueOf(text.charAt(2)));
	}
	
	/**
	 * Returns full formula as a text.
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
		StringBuilder sB = new StringBuilder();
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
	 * Returns whole mathematical formulas of each and every step.
	 * 
	 * @param encoded Text block encoded to number.
	 * @return decoding formula.
	 */
	private String getDecodecBlockFormula(int encoded) {
		StringBuilder sB = new StringBuilder();
		//divide encoded num with base number
		division = encoded/BASE_double;
		//check if the result is < 26
		if (division < BASE) { //two products
			firstProduct = 0;
			//separate integer and desimal part of double
			secondProduct = (int) division; //integer part
			dPart = division-secondProduct; //desimal part
			lastProduct = (int) roundDouble(BASE*dPart, 1);
			sB.append((int)encoded+" = ("+(int)encoded+"/"+BASE+")"+times+BASE+"\n");
			sB.append((int)encoded+" = "+division+times+BASE+"\n");
			sB.append((int)encoded+" = "+secondProduct+times+BASE+" + "+dPart+times+BASE+"\n");
			sB.append((int)encoded+" = "+secondProduct+times+BASE+" + "+lastProduct+times+BASE+pow_zero+"\n");
		}
		else { //three products
			//result after the first division is >26 so another division has to be done
			sB.append(encoded+" = ("+encoded+"/"+BASE+")"+times+BASE+"\n");
			sB.append(encoded+" = "+roundDouble(division, 3)+times+BASE+"\n");
			sB.append(encoded+" = ("+roundDouble(division, 3)+"/"+BASE+")"+times+BASE+pow_two+"\n");
			division = division/BASE_double;
			/*
			 * Now after second division the result should be <26.
			 * Text block 'ZZZ' has the biggest number when encoded (17575) and [17575/(26*26)] < 26.
			 */
			if (division < BASE) { //to be 100% sure that the division result is <26
				sB.append(encoded+" = "+roundDouble(division, 3)+times+BASE+pow_two+"\n");
				firstProduct = (int) division; //we get first product
				dPart = division-firstProduct; //desimal part
				sB.append(encoded+" = "+firstProduct+times+BASE+pow_two+" + "+roundDouble(dPart, 3)+times+BASE+pow_two+"\n");
				sB.append(encoded+" = "+firstProduct+times+BASE+pow_two+" + ("+roundDouble(dPart, 3)+times+BASE+")"+times+BASE+"\n");
				temp = dPart*26;
				sB.append(encoded+" = "+firstProduct+times+BASE+pow_two+" + "+roundDouble(temp, 3)+times+BASE+"\n");
				secondProduct = (int) temp;
				dPart2 = temp - secondProduct;
				sB.append(encoded+" = "+firstProduct+times+BASE+pow_two+" + "+secondProduct+times+BASE+" + "+roundDouble(dPart2, 3)+times+BASE+"\n");
				lastProduct = (int) roundDouble(BASE*dPart2, 1);
				sB.append(encoded+" = "+firstProduct+times+BASE+pow_two+" + "+secondProduct+times+BASE+" + "+lastProduct+times+BASE+pow_zero+"\n");
			}
		}
	    //save products to arrays
	    products[0] = firstProduct;
	    products[1] = secondProduct;
	    products[2] = lastProduct;
		//print result
	    sB.append("Products: ["+products[0]+", "+products[1]+", "+products[2]+"]\n");
		//print number to alphabet counterparts
	    sB.append(products[0]+"="+alphaNum.getLetter(products[0])+", "
						  +products[1]+"="+alphaNum.getLetter(products[1])+", "
						  +products[2]+"="+alphaNum.getLetter(products[2])+"\n");
		sB.append("Result: ");
		for (int i=0; i < products.length; i++) { 
			sB.append(alphaNum.getLetter(products[i])); //append letters
		}
		return sB.toString();
	}
	
	/**
	 * Returns a full encoding part from one three letter block.
	 * @param block three letter text block.
	 */
	private String getEncodedBlock(String block) {
		StringBuilder sB = new StringBuilder();
		setProductNumbers(block);
		sB.append(alphaNum.getLetter(products[0])+"="+products[0]
		    +", "+alphaNum.getLetter(products[1])+"="+products[1]
		    +", "+alphaNum.getLetter(products[2])+"="+products[2]+"\n");
		sB.append(getFullEncodeFormula(block)+"\n\n");
		return sB.toString();
	}
	
	/**
	 * Returns encoding formula of each block of three letters.
	 * @param blocks String array of blocks of three letters.
	 * @return encoding formula of each block of three letters.
	 */
	private String getEncodeFormulas(String[] blocks) {
		StringBuilder sB = new StringBuilder();
		//encode every block of three
		sB.append("Encode: \n");
		sB.append(line+"\n");
		//encode every block of three
		for (String s : blocks) {
			sB.append(getEncodedBlock(s));
		}
		return sB.toString();
	}
	
	/**
	 * Returns decoding from full number list.
	 * @param decoded Numbers with white spaces to be decoded.
	 */
	private String getDecodeFormulas(String decoded) {
		StringBuilder sB = new StringBuilder();
		sB.append("Decode: \n");
		sB.append(line+"\n");
		/*
		 * 'String decoded' is numbers with spaces
		 * Numbers are split into string array cells.
		 */
		String[] numbersStr = decoded.split(" ");
		for (String s : numbersStr) {
			sB.append(getDecodecBlockFormula(Integer.parseInt(s))+"\n\n");
		}
		return sB.toString();
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
	 * Returns an array of encoded numbers consecutively.
	 * @param enc	Array of encoded numbers.
	 */
	private String getEncodedConsecutively(int[] enc) {
		StringBuilder sB = new StringBuilder();
		for (int i : enc) {
			sB.append(i+" ");
		}
		sB.append("\n");
		return sB.toString();
	}
	
	/**
	 * Decodes a String of encoded numbers.
	 * 
	 * @param encoded	Message to decode.
	 * @return Decoded message.
	 */
	private String getDecodedFullPhrase(String encoded) {
		StringBuilder sB = new StringBuilder();
		//encoded numbers are split into string array.
		String[] array = encoded.split(" ");
		//numbers are decoded to three letter blocks.
		for (String s : array) {
			sB.append(decode(Integer.parseInt(s))+" ");
		}
		return sB.toString();
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
		EncryptDecrypt encDec = new EncryptDecrypt();
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
		EncryptDecrypt encDec = new EncryptDecrypt();
		
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
	 * Returns encoding and encrypting formulas.
	 * Encrypts using RSA cryptosystem.
	 * 
	 * @param plaintext	Plaintext message to be encoded and encrypted.
	 * @param e Public exponent.
	 * @param n Modulus.
	 * @return cryptotext
	 */
	public String getEncodeAndEncryptBlocksOfThree(String plaintext, BigInteger e, BigInteger n) {
		StringBuilder sB = new StringBuilder();
		plaintext = plaintext.toUpperCase();
		
		//Encoding part
		//----------------
		sB.append(plaintext+"\n");
		
		//convert plaintext to blocks of three letters
		String[] blocks = plaintextToBlocks(plaintext);
		
		//print blocks of three letters
		sB.append(printArray(blocks)+"\n");
		
		//print encoding formula from every block
		sB.append(getEncodeFormulas(blocks));
		sB.append("Encoded: ");
		
		//encode blocks
		int[] encoded = encodeBlocks(blocks);
		
		//print encoded numbers consecutively
		sB.append(getEncodedConsecutively(encoded)+"\n");
		
		//print encrypting formulas
		sB.append("Encrypt: \n");
		sB.append(line+"\n");
		for (int i: encoded) {
			sB.append(new CalculationPhase(BigInteger.valueOf(i), e).getAll(n));
			sB.append("\n\n");
		}
		
		//Encrypting part
		//----------------
		ArrayList<BigInteger> arrayL = new ArrayList<BigInteger>();
		//encrypt blocks to arraylist
		arrayL = encryptBlocksOfThree(encoded, e, n);
		sB.append("Encrypted: ");
		
		//print arraylist contents consecutively.
		Iterator<BigInteger> iter = arrayL.iterator();
		StringBuilder encNumbers = new StringBuilder();
		while (iter.hasNext()) {
			BigInteger encrypted = iter.next();
			sB.append(encrypted + " ");
			encNumbers.append(encrypted.toString() + " ");
		}
		
		//print 0=A, 1=B,..., 25=Z 
		sB.append("\n\n"+alphaNum.getNumbers()+"\n\n");
		
		//print cryptotext
		sB.append("Cryptotext: ");
		sB.append(alphaNum.stringOfNumbersToLetters(encNumbers.toString()));
		return sB.toString().trim();
	}
	
	/**
	 * Returns decrypting and decoding formulas.
	 * Decrypts using RSA cryptosystem.
	 * 
	 * @param cryptotext Cryptotext.
	 * @param d Private exponent.
	 * @param n Modulus.
	 * @return decrypted text
	 */
	public String getDecryptAndDecodeBlocksOfThree(String cryptotext, BigInteger d, BigInteger n) {
		StringBuilder sB = new StringBuilder();
		StringBuilder sB2 = new StringBuilder();

		//print cryptotext
		sB.append("Cryptotext: "+cryptotext+"\n\n");
		
		//print A=0, B=1,..., Z=25
		sB.append(alphaNum.getLetters()+"\n\n");
		
		//convert alphabets to numbers
		String encryptedText = alphaNum.stringOfLettersToNumbers(cryptotext);
		
		//Decrypting part
		//----------------
		sB.append("Encrypted: "+encryptedText+"\n\n");
		
		sB.append("Decrypt: \n");
		sB.append(line+"\n");
		
		//print decrypting formulas
		//String to BigInteger ArrayList
		ArrayList<BigInteger> encryptedAL = stringToBiArrayList(encryptedText);
		Iterator<BigInteger> iter = encryptedAL.iterator();
		while (iter.hasNext()) {
			sB.append(new CalculationPhase(iter.next(), d).getAll(n));
			sB.append("\n\n");
		}

		//print decrypted numbers
		ArrayList<BigInteger> encrypted = stringToBiArrayList(encryptedText);
		BigInteger[] decryptedArray = decryptBlocksOfThree(encrypted, d, n);
		for (BigInteger bi : decryptedArray){
			sB2.append(bi+" ");
		}
		String decrypted = sB2.toString();
		sB.append("Decrypted: "+decrypted+"\n\n");

		//Decoding part
		//----------------
		//print decoding formulas from every encoded number
		sB.append(getDecodeFormulas(decrypted));
		
		//get decoding result
		String decoded = getDecodedFullPhrase(decrypted);
		sB.append("Decoded: \n");
		sB.append(decoded+"\n");
		
		//remove white spaces
		String decoded_no_ws = decoded.replace(" ", "");
		sB.append(decoded_no_ws+"\n");
		
		//remove x letters
		String decoded_final = decoded_no_ws.replaceAll("X", " ");
		sB.append(decoded_final);
		return sB.toString().trim();
	}
	
	/**
	 * Encodes and encrypts plaintext.
	 * Returns only encrypted text.
	 * Encrypts using RSA cryptosystem.
	 * 
	 * @param plaintext
	 * @param e Public exponent.
	 * @param n Modulus.
	 * @return cryptotext
	 */
	public String getEncodeAndEncryptSecure(String plaintext, BigInteger e, BigInteger n) {
		StringBuilder sB = new StringBuilder();
		plaintext = plaintext.toUpperCase();
		Iterator<BigInteger> iter = encryptBlocksOfThree(encodeFullPhrase(plaintext), e, n).iterator();
		while (iter.hasNext()) {
			sB.append(iter.next()+" ");
		}
		//return cryptotext
		return alphaNum.stringOfNumbersToLetters(sB.toString()).trim();
	}
	
	/**
	 * Decrypts and decodes encrypted text.
	 * Returns only decoded text.
	 * Decrypts using RSA cryptosystem.
	 * 
	 * @param cryptotext Cryptotext.
	 * @param d Private exponent.
	 * @param n Modulus.
	 * @return decrypted text
	 */
	public String getDecryptAndDecodeSecure(String cryptotext, BigInteger d, BigInteger n) {
		StringBuilder sB = new StringBuilder();
		StringBuilder sB2 = new StringBuilder();
		//convert alphabets to numbers
		String encrypted = alphaNum.stringOfLettersToNumbers(cryptotext);
		for (BigInteger bi : decryptBlocksOfThree(stringToBiArrayList(encrypted), d, n)){
			sB2.append(bi+" ");
		}
		sB.append(blocksToPlaintext(getDecodedFullPhrase(sB2.toString()).split(" "))+"\n");
		return sB.toString().trim();
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
	 * Returns a String array consecutively with white spaces.
	 * @param array	String array.
	 */
	public String printArray(String[] array) {
		StringBuilder sB = new StringBuilder();
		for (String s : array) {
			sB.append(s+" ");
		}
		sB.append("\n");
		return sB.toString();
	}

}
