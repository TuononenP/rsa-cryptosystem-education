import java.math.BigInteger;

/**
 * Padding type 1 for RSA cryptography
 * @author Jukka Tuominen
 * 
 */

public class PaddingType1 {

	private AlphabetNum alphaNum = new AlphabetNum();
	/**
	 * Constructor
	 */
	public PaddingType1(){

	}

	public Integer[] padded(String msg){
		msg=msg.toUpperCase();
		msg=msg.replace(" ", "X");
		System.out.println(msg);
		Integer[] numbers = new Integer[msg.length()];
		char[] table = msg.toCharArray();
		for (int j = 0; j < table.length; j++) {
			numbers[j]=alphaNum.getNum(String.valueOf(table[j]));
		}
		return numbers;
	}

	public BigInteger[] encrypt(String[] message, BigInteger d, BigInteger n){
		BigInteger[] encrypted = new BigInteger[message.length];
		for (int i = 0; i < message.length; i++) {
			encrypted[i]=(new BigInteger(message[i])).modPow(d, n);
		}

		return encrypted;
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		PaddingType1 koe = new PaddingType1();
		String message = "help";
		Integer[] testi = new Integer[message.length()];
		System.out.println(message);
		testi = koe.padded(message);
		System.out.print("Type1 padded text: ");
		for (int i = 0; i < testi.length; i++) {
			System.out.print(testi[i]+" ");
		}
		String[] padded = new String[testi.length];
		
		for (int i = 0; i < testi.length; i++) {
			padded[i] = testi[i].toString();
		}
		System.out.println("\n");
		System.out.print("Cryptotext: ");
		BigInteger[] crypto = new BigInteger[padded.length];
		crypto=(koe.encrypt(padded,BigInteger.valueOf(29),BigInteger.valueOf(91)));
		
		for (BigInteger integer : crypto){
			System.out.print(integer+" ");
		}
	}
}
