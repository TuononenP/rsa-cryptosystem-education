package padding_schemes;

import java.math.BigDecimal;

/**
 * Padding scheme for cryptography.
 * Divide text into blocks of 3 letters.
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
	private StringBuilder sB = new StringBuilder();
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
	 * Rounds a number upwards to a closest 
	 * @param nro Number to round.
	 * @return
	 */
	public double roundDouble(double nro) {
	    int decimalPlace = 1;
	    BigDecimal bd = new BigDecimal(nro);
	    bd = bd.setScale(decimalPlace,BigDecimal.ROUND_UP);
	    nro = bd.doubleValue();
	    return nro;
	}
	
	public String decode(int encoded) {
		//divide encoded num with base number
		division = encoded/BASE_double;
		//check if the result is < 26
		if (division < BASE_double) { //two products
			//divide front and desimal part of double
			secondProduct = (int) division; //front part
			dPart = division-secondProduct; //desimal part
			lastProduct = (int) roundDouble(BASE*dPart);
		}
		else { //three products
			//result of division is >26 so another division has to be done
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
				lastProduct = (int) roundDouble(BASE*dPart2);
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
		//divide encoded num with base number
		division = encoded/BASE_double;
		//check if the result is < 26
		if (division < BASE_double) { //two products
			//divide front and desimal part of double
			secondProduct = (int) division; //front part
			dPart = division-secondProduct; //desimal part
			lastProduct = (int) roundDouble(BASE*dPart);
			System.out.println((int)encoded+" = ("+(int)encoded+"/"+BASE+")*"+BASE);
			System.out.println((int)encoded+" = "+division+"*"+BASE);
			System.out.println((int)encoded+" = "+secondProduct+"*"+BASE+" + "+dPart+"*"+BASE);
			System.out.println((int)encoded+" = "+secondProduct+"*"+BASE+" + "+lastProduct+"*"+BASE+pow_zero);
		}
		else { //three products
			//result of division is >26 so another division has to be done
			division = division/BASE_double;
			/*
			 * Now after second division the result should be <26.
			 * Text block 'ZZZ' has the biggest number when encoded (17575) and [17575/(26*26)] < 26.
			 */
			System.out.println("\nDEBUG:");
			if (division < BASE) { //to be 100% sure that the division result is <26
				firstProduct = (int) division; //we get first product
				System.out.println("first product: "+firstProduct);
				dPart = division-firstProduct; //desimal part
				System.out.println(dPart);
				temp = dPart*26;
				System.out.println(temp);
				secondProduct = (int) temp;
				System.out.println("second product: "+secondProduct);
				dPart2 = temp - secondProduct;
				System.out.println(dPart2);
				lastProduct = (int) roundDouble(BASE*dPart2);
				System.out.println(lastProduct);
			}
		}
	    //save products to table
	    products[0] = firstProduct;
	    products[1] = secondProduct;
	    products[2] = lastProduct;
		//print result
		System.out.println("Products: ["+products[0]+", "+products[1]+" ,"+products[2]+"]");
		//print 0=A
		System.out.println(products[0]+"="+alphaNum.getLetter(products[0])+", "
				+products[1]+"="+alphaNum.getLetter(products[1])+", "
				+products[2]+"="+alphaNum.getLetter(products[2]));
		
		for (int i=0; i < products.length; i++) { 
			sB.append(alphaNum.getLetter(products[i])); //append letters
		}
		decoded = sB.toString();
		System.out.println("Result: "+decoded);
	}
	
	public static void main(String[] args) {
		Blocks_Of_3_Padding pg = new Blocks_Of_3_Padding();
		
		//TEST DECODE METHOD
//		System.out.println("///////////////////////////////////");
//		System.out.println(pg.decode(513));
//		System.out.println(pg.decode(62));
//		System.out.println(pg.decode(15567));
//		System.out.println(pg.decode(16020));
//		System.out.println(pg.decode(14313));
//		System.out.println("///////////////////////////////////");
		//TEST END

		System.out.println("Encode: ");
		pg.setProductNumbers("ATT");
		System.out.println(pg.alphaNum.getLetter(pg.products[0])+"="+pg.products[0]+", "+pg.alphaNum.getLetter(pg.products[1])+"="+pg.products[1]+", "+pg.alphaNum.getLetter(pg.products[2])+"="+pg.products[2]);
		System.out.println(pg.getFullFormula("ATT"));
		System.out.println("");
		
		//TEST DECODE_PRINT METHOD
		System.out.println("Decode: ");
		pg.decode_print(513);
		//TEST END
		
//		AlphabetNum aN = new AlphabetNum();
//		double BASE_double = Double.parseDouble(Integer.toString(pg.BASE)); 
//		int BASE = pg.BASE;
		
//		String message = "The purpose of this message is to test a method";
//		System.out.println(message);
//		pg.printBlockText(message);
//		System.out.println("\n");
//		String example = "ATTACK AT SEVEN";
//		System.out.println(example);
//		pg.printBlockText(example);
//		System.out.println("\n");
//		System.out.println(pg.getFullFormula("ATT"));
//		System.out.println(pg.getFullFormula("ACK"));
//		System.out.println(pg.getFullFormula("XAT"));
//		System.out.println(pg.getFullFormula("XSE"));
//		System.out.println(pg.getFullFormula("VEN"));
		
		//Reverse padding test
//		String block = "XSE";
//		double encoded = Double.parseDouble(Integer.toString(pg.textBlockToNum(block))); //number to reverse into a String
//		System.out.println(encoded);
		
/*
		StringBuilder sB = new StringBuilder();
		String decoded = null;
		
		//variables
		int firstProduct = 0, secondProduct = 0, lastProduct = 0;
		double dPart;
		int[] products = new int[3];
		
		//1st phase - divide var with 26 aka base number
		double division = encoded/BASE_double;
		System.out.println(division);
		//2nd phase - check if the result is < 26
		if (division < BASE_double) { //two products
			//divide front and desimal part of double
			secondProduct = (int) division; //front part
			dPart = division-secondProduct; //desimal part
			lastProduct = (int) pg.roundDouble(BASE*dPart);
			System.out.println((int)encoded+" = "+secondProduct+"*"+BASE+" + "+dPart+"*"+BASE); //first line
		    System.out.println((int)encoded+" = "+secondProduct+"*"+BASE+" + "+lastProduct); //second line
		    //save products to table
		    products[0] = firstProduct;
		    products[1] = secondProduct;
		    products[2] = lastProduct;
		}
		else { //three products
			//result of division is >26 so another division has to be done
			division = division/BASE_double;
			/*
			 * Now after second division the result should be <26.
			 * Text block 'ZZZ' has the biggest number when encoded (17575) and [17575/(26*26)] < 26.
			 */
/*
			System.out.println("\nDEBUG:");
			if (division < BASE) { //to be 100% sure that the division result is <26
				firstProduct = (int) division; //we get first product
				System.out.println("first product: "+firstProduct);
				dPart = division-firstProduct; //desimal part
				System.out.println(dPart);
				double temp = dPart*26;
				System.out.println(temp);
				secondProduct = (int) temp;
				System.out.println("second product: "+secondProduct);
				double dPart2 = temp - secondProduct;
				System.out.println(dPart2);
				lastProduct = (int) pg.roundDouble(BASE*dPart2);
				System.out.println(lastProduct);
			    //save products to table
			    products[0] = firstProduct;
			    products[1] = secondProduct;
			    products[2] = lastProduct;
			}
		}
		//print result
		System.out.println("Products: ["+products[0]+", "+products[1]+" ,"+products[2]+"]");
		for (int i=0; i < products.length; i++) { 
			sB.append(aN.getLetter(products[i])); //append letters
		}
		decoded = sB.toString();
		System.out.println("Result: "+decoded);
*/
	}
}
