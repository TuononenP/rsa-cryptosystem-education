package padding_schemes;
import java.util.HashMap;

/**
 * Defines an order number to every letter in English alphabet.
 * The correct letter for a given order number can be returned.
 * The order number for each letter can be returned.
 * Can be used in basic cryptography padding schemes.
 * 
 * @author Petri Tuononen
 * @since 25.1.2009
 * 
 * added getStringNum for more flexibility
 * @author Jukka Tuominen
 */
public class AlphabetNum {

	private HashMap<String, Integer> alphaNum;
	private String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
			"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	
	/**
	 * Constructor.
	 */
	public AlphabetNum() {
		this.alphaNum = createHashMap();	
	}
		
	/**
	 * Creates HashMap.
	 * @return		HashMap<String, Integer>
	 */
	public HashMap<String, Integer> createHashMap() {
		HashMap<String, Integer> alphaNum = new HashMap<String, Integer>();
		for (int i=0; i<26; i++) {
			alphaNum.put(alphabet[i], i);
		}
		return alphaNum;
	}
	
	/**
	 * Return an order number for a given letter.
	 * @param letter
	 * @return 		Number
	 */
	public int getNum(String letter) {
		return alphaNum.get(letter);
	}
	
	/**
	 * Return a correct letter for a given order number.
	 * @param num
	 * @return		Letter.
	 */
	public String getLetter(int num) {
		return alphabet[num];
	}
	/**
	 * Return an order number as String for a given letter.
	 * @param letter
	 * @return String
	 */
	public String getStringNum(String letter){
		return alphaNum.get(letter).toString();
	}
}
