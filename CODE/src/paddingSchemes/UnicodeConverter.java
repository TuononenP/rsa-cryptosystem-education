/*
	Copyright (C) 2009 Jukka Tuominen

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
