package padding_schemes;

import java.util.HashMap;


/**
 * Returns strings containing numbers to mathematical
 * unicode superscript.
 * @author Jukka Tuominen
 *
 */
public class UnicodeConverter {
	
	private static HashMap<String,String> map = new HashMap<String,String>();
	private static String[] numbers={"\u2070","\u00B9","\u00B2","\u00b3","\u2074","\u2075","\u2076","\u2077",
		"\u2078","\u2079"};
	
	/**
	 * Constructor
	 */
	public UnicodeConverter(){
		for (int i = 0; i < numbers.length; i++) {
			map.put(((Integer)i).toString(), numbers[i]);
		}
		map.put("+", "\u207A");
	}
	
	/**
	 * Returns String numbers as unicode superscript.
	 * @return StringBuilder superscript_number
	 */
	public String superScript(String p){
		StringBuilder b = new StringBuilder();
		String[] a = p.split("");
		for (int i = 1; i < a.length; i++) {
			b.append(map.get(a[i]));
		}
		
		return b.toString();
	}
	/**
	 * Main for testing
	 * @param args
	 */
	public static void main(String[] args){
		UnicodeConverter h = new UnicodeConverter();
		String k = h.superScript("123456");
		System.out.println("63"+k);
	}
}
