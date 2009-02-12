package padding_schemes;
import java.awt.Font;
import java.math.BigInteger;
import javax.swing.JTextArea;
import gui.FullScreen;

public class CalculationPhase extends DividedPower {

	private BigInteger number;
	private BigInteger exponent;
	private UnicodeConverter sc = new UnicodeConverter();
	/**
	 * Constructor
	 * 
	 * @param num number
	 * @param exp exponent
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
		s.append(number.toString());
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
		s.append(number.toString());
		//s.append("^");
		String[] a = powerDivison(exponent).toString().split("");
		for (int i = 1; i < a.length; i++) {
			s.append(sc.superScript(a[i]));
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
				s.append("\u00d7");
			}
		}
		return s;
	}

	/**
	 * Returns modulo phase
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
					s.append("\u00d7");
					begin.append(s);
				}
			} else {
				help = number.modPow(new BigInteger(p[i]), mod);
				s.append(help);
				begin.append(help);
				if (i < p.length - 1) {
					s.append("\u00d7");
					begin.append("\u00d7");
				}

				a = i;
				for (int j = i; j < p.length; j++) {
					if (j < p.length - 1) {
						s.append(help);
						//s.append("^");
						s.append(sc.superScript(new BigInteger(p[j + 1])
						.divide(new BigInteger(p[a])).toString()));
						if (j + 1 < p.length - 1) {
							s.append("\u00d7");
						}
					}
				}
				if (i<p.length-1){
					s.append("\n \u2261 ");
					s.append(begin);
				}
			}
		}

		s.append("\n \u2261 ");
		BigInteger answer = BigInteger.ONE;
		String[] v = begin.toString().split("\u00d7");
		for (int i = 0; i < v.length; i++) {
			answer = answer.multiply(new BigInteger(v[i]));
		}

		s.append(answer.mod(mod));
		s.append("(mod " + mod + ")");
		return s;
	}

	public StringBuilder getAll(BigInteger modulo){
		StringBuilder s = new StringBuilder();
		//SymbolicCalculation f = new SymbolicCalculation(num, exp);
		s.append(getFormula()+ "\n = ");
		s.append(getExpDiv()+"\n = ");
		s.append(getAddition()+"\n \u2261 ");
		s.append(getModulo(modulo));
		return s;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// testing....


		CalculationPhase f = new CalculationPhase(BigInteger.valueOf(63), new BigInteger("29"));
		/*System.out.print(f.getFormula() + " = ");
		System.out.print(f.getExpDiv() + " = ");
		System.out.println(f.getAddition() + " = ");
		System.out.println(f.getModulo(new BigInteger("91")));
		 */


		JTextArea j = new JTextArea();
		j.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		j.setText(f.getAll(new BigInteger("91")).toString());
		FullScreen screen = new FullScreen(j);


	}

}
