package padding_schemes;

import java.math.BigInteger;
import java.util.ArrayList;

public class Formula extends DividedPower{
	
	private BigInteger number;
	private BigInteger exponent;
	//private String[] exp;
	/**
	 * Constructor
	 * @param num number
	 * @param exp exponent
	 */
	public Formula(BigInteger num, BigInteger exp){
		number=num;
		exponent=exp;
	}
	
	/**
	 * 
	 * @return exponent
	 */
	public BigInteger getExponent(){
		return exponent;
	}
	
	/**
	 * 
	 * @return number
	 */
	public BigInteger getNumber(){
		return number;
	}
	
	/**
	 * Returns original formula
	 * @return StringBuilder number^exponent
	 */
	public StringBuilder getFormula(){
		StringBuilder s = new StringBuilder();
		s.append(number.toString());
		s.append("^");
		s.append(exponent.toString());
		return s;
	}
	/**
	 * Returns formulas exponent in divided form.
	 * @return StringBuilder number^divided_exponent
	 */
	public StringBuilder getExpDiv(){
		StringBuilder s = new StringBuilder();
		s.append(number.toString());
		s.append("^");
		s.append(powerDivison(exponent));
		return s;
		
	}
	
	/**
	 * Returns formulas addition phase
	 * @return StringBuilder number^exp1+number^exp2...
	 */
	public StringBuilder getAddition(){
		StringBuilder s = new StringBuilder();
		ArrayList<BigInteger> num = new ArrayList<BigInteger>();
		while (num.compareTo(BigInteger.valueOf(2))>0){
			num.add(calculate(number)) ;
			System.out.println(num);
		}
		
	return s;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// testing....
		Formula f = new Formula(BigInteger.valueOf(63),new BigInteger("113"));
		System.out.print(f.getFormula()+"=");
		System.out.println(f.getExpDiv());
		System.out.println(f.getAddition());
		
	}

}