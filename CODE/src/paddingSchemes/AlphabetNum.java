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
	
	/**
	 * Returns String containing letters with numbers.
	 * A=0, B=1
	 * @return String 
	 */
	public String getNumbers(){
		String s ="";
		for (int i=0; i<26; i++) {
			s=s+i+"=";
			s=s+getLetter(i);
			if (i!=25) {
				s=s+", ";	
			}
			if (i==10 || i==20) {
				s = s+"\n";
			}
		}
		return s;
	}
	
	/**
	 * Returns String containing numbers with letters.
	 * 0=A, 1=B etc.
	 * @return String 
	 */
	public String getLetters(){
		String s ="";
		for (int i=0; i<26; i++) {
			s=s+getLetter(i)+"=";
			s=s+i;
			if (i!=25) {
				s=s+", ";	
			}
			if (i==10 || i==20) {
				s = s+"\n";
			}
		}
		return s;
	}
	
	/**
	 * Converts a full string of numbers to letters.
	 * Can be used to convert encrypted numbers to cryptotext.
	 * @return String
	 */
	public String stringOfNumbersToLetters(String numbers) {
		StringBuilder sB = new StringBuilder();
		for (int i=0; i<numbers.length(); i++) {
			char c = numbers.charAt(i);
			if (numbers.charAt(i)!= ' ') { //not white space
				sB.append(getLetter(Character.getNumericValue(c)));
			}
			else { //white space
				sB.append(" ");
			}
		}
		return sB.toString();
	}
	
	/**
	 * Convers a full string of letters to numbers.
	 * Can be used to convert cryptotext to encrypted numbers.
	 * @param letters
	 * @return String
	 */
	public String stringOfLettersToNumbers(String letters) {
		StringBuilder sB = new StringBuilder();
		for (int i=0; i<letters.length(); i++) {
			char c = letters.charAt(i);
			if (letters.charAt(i)!= ' ') { //not white space
				sB.append(getNum(Character.toString(c)));
			}
			else { //white space
				sB.append(" ");
			}
		}
		return sB.toString();
	}
	
	public static void main(String[] args) {
		String numbers = "0123 314 32 4354 36443 574354 835 394";
		System.out.println(numbers);
		String letters = new AlphabetNum().stringOfNumbersToLetters(numbers);
		System.out.println(letters);
		System.out.println(new AlphabetNum().stringOfLettersToNumbers(letters));
		System.out.println("\n");
		System.out.println(new AlphabetNum().getNumbers());
		System.out.println("\n");
		System.out.println(new AlphabetNum().getLetters());
		System.out.println("\n");
	}
		
}