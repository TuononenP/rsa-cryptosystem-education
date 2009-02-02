package padding_schemes;

/**
 * Padding scheme for cryptography.
 * Divide text into blocks of 3 letters.
 * 
 * @author Petri Tuononen
 * @since 25.1.2009
 */
public class Blocks_Of_3_Padding {

	private AlphabetNum alphaNum = new AlphabetNum();
	final int BASE = 26;
	
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
	public int textBlockToNum(String text) {
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
		String formula, char1, char2, char3;
		String pow_zero = "\u00B0";
		String pow_one = "\u00B9";
		String pow_two = "\u00B2";
		char1 = Integer.toOctalString(alphaNum.getNum(text.substring(0, 1)));
		char2 = Integer.toOctalString(alphaNum.getNum(text.substring(0, 1)));
		char3 = Integer.toOctalString(alphaNum.getNum(text.substring(0, 1)));
		formula = char1+"*"+BASE+pow_two+" + "+char2+"*"+BASE+pow_one+" + "+char3+"*"+BASE+pow_zero;
		return formula;
	}
	
	/**
	 * Return full formula as a text.
	 * e.g. ATT = 0*26^2 + 19*26^1 + 19*26^0 = 513
	 * @param text	Three letter string.
	 * @return
	 */
	public String getFullFormula(String text) {
		return text+" = "+getFormula(text)+" = "+textBlockToNum(text);
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
	
	public static void main(String[] args) {
		Blocks_Of_3_Padding padding = new Blocks_Of_3_Padding();
		String message = "The purpose of this message is to test a method";
		System.out.println(message);
		padding.printBlockText(message);
		System.out.println("\n");
		String example = "ATTACK AT SEVEN";
		System.out.println(example);
		padding.printBlockText(example);
		System.out.println("\n");
		System.out.println(padding.getFullFormula("ATT"));
		System.out.println(padding.getFullFormula("ACK"));
		System.out.println(padding.getFullFormula("XAT"));
		System.out.println(padding.getFullFormula("XSE"));
		System.out.println(padding.getFullFormula("VEN"));
	}
}