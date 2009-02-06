package padding_schemes;

import java.math.BigDecimal;

/**
 * Padding scheme for cryptography.
 * Divides text into blocks of 3 letters.
 * Encodes text block to number.
 * Decodes number back to text block.
 * 
 * @author Petri Tuononen
 * @since 25.1.2009
 */
public class Blocks_Of_3_Padding {

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
	 * Constructor.
	 */
	public Blocks_Of_3_Padding() {
	
	}
	
	/**
	 * Divide text into 3 letter blocks.
	 * @param msg	Text to be divided into 3 letter blocks.
	 * @return		Table containing one 3 letter block per cell.
	 */
	public String[] DivideToBlocks(String message) {
		String msg = message.toUpperCase();
		String[] word_table;
		String[] xWord_table;
		String[] block_table;
		String word;
		String newWord;
		int wordLength, tableLengt, remainder, threeLetterBlocks = 0;
		//Divide message to words
		word_table = msg.split(" ");
		tableLengt = word_table.length;
		xWord_table = new String[tableLengt];
		//Test every word if they can be divided by 3
		for (int i=0; i < word_table.length; i++) {
			word = word_table[i];
			wordLength = word.length();
			remainder = wordLength%3;
			if (remainder == 2) {
				newWord = "X" + word;
				xWord_table[i] = newWord;
			}
			else if (remainder == 1) {
				newWord = "XX" + word;
				xWord_table[i] = newWord;
			}
			else {
				xWord_table[i] = word;
			}
		}
		//Define table size
		block_table = new String[numOf3LetterBlocks(xWord_table)];
		//Allocate each 3 letter block to one table cell
		int k = 0, j = 0, alku = 0, loppu = 3;
		for (int i=0; i < xWord_table.length; i++) {
			j = 0;
			alku = 0;
			loppu = 3;
			word = xWord_table[i];
			//3 letter blocks per word
			threeLetterBlocks = word.length()/3;
			while (j < threeLetterBlocks) {
				block_table[k+j] = word.substring(alku, loppu);
				alku += 3;
				loppu += 3;
				j++;
			}
			k += threeLetterBlocks;
		}
		return block_table;
	}
	
	/**
	 * Return number of three letter blocks in a table.
	 * @param table
	 * @return
	 */
	public int numOf3LetterBlocks(String[] table) {
		int num = 0;
		String word;
		for (int i=0; i < table.length; i++) {
			word = table[i];
			num += word.length()/3;
		}
		return num;
	}
	
	/**
	 * Converts 3 letter string to a encoded number.
	 * Let A=0, B=1,...,Z=25
	 * 2st letter*BASE^2 + 2nd letter*BASE^1 + 3rd letter*BASE^0
	 * @param text	 Three letter string.
	 * @return
	 */
	public int encode(String text) {
		int num = (int) (alphaNum.getNum(text.substring(0, 1))*Math.pow(BASE, 2)) +
		(int) (alphaNum.getNum(text.substring(1, 2))*Math.pow(BASE, 1)) +
		(int) (alphaNum.getNum(text.substring(2, 3))*Math.pow(BASE, 0));
		return num;
	}
	
	/**
	 * Return a formula as a text.
	 * @param text	Three letter string.
	 * @return
	 */
	public String getFormula(String text) {
		String formula;
		setProductNumbers(text);
		formula = products[0]+"*"+BASE+pow_two+" + "+products[1]+"*"+BASE+pow_one+" + "+products[2]+"*"+BASE+pow_zero;
		return formula;
	}
	
	public void setProductNumbers(String text) {
		products[0] = alphaNum.getNum(String.valueOf(text.charAt(0)));
		products[1] = alphaNum.getNum(String.valueOf(text.charAt(1)));
		products[2] = alphaNum.getNum(String.valueOf(text.charAt(2)));
	}
	
	/**
	 * Return full formula as a text.
	 * e.g. ATT = 0*26^2 + 19*26^1 + 19*26^0 = 513
	 * @param text	Three letter string.
	 * @return
	 */
	public String getFullFormula(String text) {
		return text+" = "+getFormula(text)+" = "+encode(text);
	}
	
	/**
	 * Print 3 letter text.
	 * e.g. ATTACK AT SEVEN --> ATT ACK XAT XSE VEN
	 * @param message
	 */
	public void printBlockText(String message) {
		Blocks_Of_3_Padding padding = new Blocks_Of_3_Padding();
		String[] word_table = padding.DivideToBlocks(message);
		String word;
		for (int i=0; i < word_table.length; i++) {
			word = word_table[i];
			System.out.print(word+" ");
		}
	}
	
	/**
	 * Rounds a number upwards to closest integer.
	 * @param nro Number to round.
	 * @return Rounded number.
	 */
	public double roundDouble(double nro, int desPlace) {
	    BigDecimal bd = new BigDecimal(nro);
	    bd = bd.setScale(desPlace,BigDecimal.ROUND_UP);
	    nro = bd.doubleValue();
	    return nro;
	}
	
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
//			System.out.println(encoded+" = "+division+"*"+BASE);
			System.out.println(encoded+" = "+roundDouble(division, 3)+"*"+BASE);
//			System.out.println(encoded+" = ("+division+"/"+BASE+")*"+BASE+pow_two);
			System.out.println(encoded+" = ("+roundDouble(division, 3)+"/"+BASE+")*"+BASE+pow_two);
			division = division/BASE_double;
			/*
			 * Now after second division the result should be <26.
			 * Text block 'ZZZ' has the biggest number when encoded (17575) and [17575/(26*26)] < 26.
			 */
			if (division < BASE) { //to be 100% sure that the division result is <26
//				System.out.println(encoded+" = "+division+"*"+BASE+pow_two);
				System.out.println(encoded+" = "+roundDouble(division, 3)+"*"+BASE+pow_two);
				firstProduct = (int) division; //we get first product
				dPart = division-firstProduct; //desimal part
//				System.out.println(encoded+" = "+firstProduct+"*"+BASE+pow_two+" + "+dPart+"*"+BASE+pow_two);
				System.out.println(encoded+" = "+firstProduct+"*"+BASE+pow_two+" + "+roundDouble(dPart, 3)+"*"+BASE+pow_two);
//				System.out.println(encoded+" = "+firstProduct+"*"+BASE+pow_two+" + ("+dPart+"*"+BASE+")*"+BASE);
				System.out.println(encoded+" = "+firstProduct+"*"+BASE+pow_two+" + ("+roundDouble(dPart, 3)+"*"+BASE+")*"+BASE);
				temp = dPart*26;
//				System.out.println(encoded+" = "+firstProduct+"*"+BASE+pow_two+" + "+temp+"*"+BASE);
				System.out.println(encoded+" = "+firstProduct+"*"+BASE+pow_two+" + "+roundDouble(temp, 3)+"*"+BASE);
				secondProduct = (int) temp;
				dPart2 = temp - secondProduct;
//				System.out.println(encoded+" = "+firstProduct+"*"+BASE+pow_two+" + "+secondProduct+"*"+BASE+" + "+dPart2+"*"+BASE);
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
	
	//prints encode & decode functions
	public void testMethods(String block) {
		System.out.println("Encode: ");
		setProductNumbers(block);
		System.out.println(alphaNum.getLetter(products[0])+"="+products[0]
		             +", "+alphaNum.getLetter(products[1])+"="+products[1]
		             +", "+alphaNum.getLetter(products[2])+"="+products[2]);
		System.out.println(getFullFormula(block));
		System.out.println("");
		int encoded = encode(block);
		System.out.println("Decode: ");
		decode_print(encoded);
	}
	
	public static void main(String[] args) {
		Blocks_Of_3_Padding pg = new Blocks_Of_3_Padding();
		
		pg.testMethods("ATT");
		System.out.println("");
		pg.testMethods("XSE");
		
		//Tests decode method
//		System.out.println("///////////////////////////////////");
//		System.out.println(pg.decode(513));
//		System.out.println(pg.decode(62));
//		System.out.println(pg.decode(15567));
//		System.out.println(pg.decode(16020));
//		System.out.println(pg.decode(14313));
//		System.out.println("///////////////////////////////////");
		//Test end
	}
}
