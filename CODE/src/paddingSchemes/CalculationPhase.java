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
import java.awt.Font;
import java.math.BigInteger;
import javax.swing.JTextArea;
import gui.FullScreen;

/**
 * CalculationPhase is used in padding scheme calculations.
 * Constructs encrypting and decrypting calculation phases.
 * 
 * @author Jukka Tuominen
 */
public class CalculationPhase extends DividedPower {

	private BigInteger number;
	private BigInteger exponent;
	private UnicodeConverter sc = new UnicodeConverter();
	private final String kongru = "\u00d7";
	private final String times = "\u2261";
	
	/**
	 * Constructor
	 * 
	 * @param num number BigInteger
	 * @param exp exponent BigInteger
	 */
	public CalculationPhase(BigInteger num, BigInteger exp) {
		number = num;
		exponent = exp;
	}

	/**
	 * Return exponent
	 * 
	 * @return exponent
	 */
	public BigInteger getExponent() {
		return exponent;
	}

	/**
	 * Returns number
	 * 
	 * @return number
	 */
	public BigInteger getNumber() {
		return number;
	}

	/**
	 * Returns original formula
	 * 
	 * @return StringBuilder number^exponent
	 */
	public StringBuilder getFormula() {
		StringBuilder s = new StringBuilder();
		s.append(number);
		//s.append("^");
		s.append(sc.superScript(exponent.toString()));
		return s;
	}

	/**
	 * Returns formulas exponent in divided form.
	 * 
	 * @return StringBuilder number^divided_exponent
	 */
	public StringBuilder getExpDiv() {
		StringBuilder s = new StringBuilder();
		s.append(number);
		//s.append("^");
		String[] a = powerDivison(exponent).toString().split("");
		for (String i : a) {
			s.append(sc.superScript(i));
		}
		//s.append(powerDivison(exponent));
		return s;
	}

	/**
	 * Returns formulas addition phase
	 * 
	 * @return StringBuilder number^exp1+number^exp2...
	 */
	public StringBuilder getAddition() {
		StringBuilder s = new StringBuilder();
		String[] p = powerDivison(exponent).toString().split("\\+");
		for (int i = 0; i < p.length; i++) {
			s.append(number.toString());
			//s.append("^");
			s.append(sc.superScript(p[i]));
			if (i < p.length - 1) {
				s.append(kongru);
			}
		}
		return s;
	}

	/**
	 * Returns modulo calculation phase
	 * 
	 * @param mod BigInteger modulo
	 *            
	 * @return StringBuilder modulo_phase
	 */
	public StringBuilder getModulo(BigInteger mod) {
		StringBuilder s = new StringBuilder();
		StringBuilder begin = new StringBuilder();
		String[] p = powerDivison(exponent).toString().split("\\+");
		BigInteger help = new BigInteger("0");

		int a = 0;
		for (int i = 0; i < p.length; i++) {
			if (new BigInteger(p[i]).compareTo(BigInteger.ONE) < 1) {
				s.append(number);
				if (i < p.length - 1) {
					s.append(kongru);
					begin.append(s);
				}
			} else {
				help = number.modPow(new BigInteger(p[i]), mod);
				s.append(help);
				begin.append(help);
				if (i < p.length - 1) {
					s.append(kongru);
					begin.append(kongru);
				}

				a = i;
				for (int j = i; j < p.length; j++) {
					if (j < p.length - 1) {
						s.append(help);
						//s.append("^");
						s.append(sc.superScript(new BigInteger(p[j + 1])
						.divide(new BigInteger(p[a])).toString()));
						if (j + 1 < p.length - 1) {
							s.append(kongru);
						}
					}
				}
				if (i<p.length-1){
					s.append("\n "+times);
					s.append(begin);
				}
			}
		}

		s.append("\n "+times);
		BigInteger answer = BigInteger.ONE;
		String[] v = begin.toString().split(kongru);
		for (int i = 0; i < v.length; i++) {
			answer = answer.multiply(new BigInteger(v[i]));
		}

		s.append(answer.mod(mod));
		s.append("(mod " + mod + ")");
		return s;
	}
	
	/**
	 * Returns all calculation phases
	 * for encrypting and decrypting.
	 * @param modulo BigInteger
	 * @return StringBuilder
	 */
	public StringBuilder getAll(BigInteger modulo){
		StringBuilder s = new StringBuilder();
		//SymbolicCalculation f = new SymbolicCalculation(num, exp);
		s.append(getFormula()+ "\n = ");
		s.append(getExpDiv()+"\n = ");
		s.append(getAddition()+"\n "+times+" ");
		s.append(getModulo(modulo));
		return s;
	}

	/**
	 * Main for testing
	 * @param args
	 */
	public static void main(String[] args) {
		CalculationPhase f = new CalculationPhase(BigInteger.valueOf(63), new BigInteger("29"));

		JTextArea j = new JTextArea();
		j.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		j.setText(f.getAll(new BigInteger("91")).toString());
		new FullScreen(j);
	}

}
