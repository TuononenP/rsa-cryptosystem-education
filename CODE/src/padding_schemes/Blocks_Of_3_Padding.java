package padding_schemes;

import java.math.BigDecimal;

/**
 * Padding scheme for cryptography.
 * Divides text into blocks of 3 letters.
 * Encodes text block to number.
 * Decodes number back to text block.
 * Prints full encoding/decoding formulas from full phrases.
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
	String decoded = null;
	int firstProduct = 0, secondProduct = 0, lastProduct = 0;
	double dPart, dPart2, division, temp;
	
	/**
	 * Divide plaintext into 3 letter blocks.
	 * @param msg	Plaintext to be divided into 3 letter blocks.
	 * @return		Table containing one 3 letter block per cell.
	 */
	public String[] plaintextToBlocks(String message) {
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
	
	/**
	 * Prints text in three letter blocks.
	 * e.g. ATTACK AT SEVEN --> ATT ACK XAT XSE VEN
	 * @param message Text in three letter blocks.
	 */
	public void printBlockText(String message) {
		String[] word_table = plaintextToBlocks(message);
		String word;
		for (int i=0; i < word_table.length; i++) {
			word = word_table[i];
			System.out.print(word+" ");
		}
		System.out.println("");
	}
	
	/**
	 * Converts blocks of three letters to plaintext.
	 * 
	 * @param blocks Three letter blocks in a String array.
	 * @return	Plaintext.
	 */
	public String blocksToPlaintext(String[] blocks) {
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
	 * 2st letter*BASE^2 + 2nd letter*BASE^1 + 3rd letter*BASE^0
	 * @param text	 Three letter string.
	 * @return Number of encoded text.
	 */
	public int encodeBlock(String block) {
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
	public String getEncodeFormula(String text) {
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
	public String getFullEncodeFormula(String text) {
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
	public String decode(int encoded) {
		sB = new StringBuilder();
		//divide encoded num with base number
		division = encoded/BASE_double;
		//check if the result is < 26
		if (division < BASE_double) { //two products
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
		decoded = sB.toString();
		return decoded;
	}
	
	/**
	 * Decodes a number back to three letter text block.
	 * Prints whole mathematical formulas of each and every step.
	 * 
	 * @param encoded Text block encoded to number.
	 */
	public void decode_print(int encoded) {
		sB = new StringBuilder();
		//divide encoded num with base number
		division = encoded/BASE_double;
		//check if the result is < 26
		if (division < BASE) { //two products
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
		decoded = sB.toString();
		System.out.println("Result: "+decoded);
	}
	
	/**
	 * Prints a full encoding part from one three letter block.
	 * @param block
	 */
	public void printBlockEncode(String block) {
		setProductNumbers(block);
		System.out.println(alphaNum.getLetter(products[0])+"="+products[0]
		             +", "+alphaNum.getLetter(products[1])+"="+products[1]
		             +", "+alphaNum.getLetter(products[2])+"="+products[2]);
		System.out.println(getFullEncodeFormula(block));
		System.out.println("");
	}
	
	/**
	 * Prints a full decoding part from one number that represents a three letter block.
	 * @param encoded
	 */
	public void printBlockDecode(int encoded) {
		decode_print(encoded);
	}
	
	/**
	 * Print encoding from full phrase.
	 * @param phrase Message to be encoded.
	 */
	public void printFullEncode(String phrase) {
		//converts message to blocks of three
		String[] msg = plaintextToBlocks(phrase);
		//encode every block of three
		System.out.println("Encode: ");
		System.out.println("------------------------------------");
		for (int i=0; i<msg.length; i++) {
			printBlockEncode(msg[i]);
		}
	}
	
	/**
	 * Print decoding from full number list.
	 * @param decoded Numbers with white spaces to be decoded.
	 */
	public void printFullDecode(String decoded) {
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
	 * Tests methods.
	 * @param args
	 */
	public static void main(String[] args) {
		Blocks_Of_3_Padding pg = new Blocks_Of_3_Padding();

		String msg = "ATTACK AT SEVEN";
		System.out.println(msg);
		pg.printBlockText(msg);
		System.out.println("");
		pg.printFullEncode(msg);
		pg.printFullDecode("513 62 15567 16020 14313");
	}
}
