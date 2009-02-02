import java.math.BigInteger;	
import java.util.ArrayList;
/**
 *  @author Jukka Tuominen
 */
public class PaddingType2 {
	private AlphabetNum alphaNum = new AlphabetNum();
	/**
	 * 
	 * @Constructor
	 */
	public PaddingType2(){

	}


	public ArrayList<Integer> padded(String msg){
		msg=msg.toUpperCase();
		msg=msg.replace(" ", "X");
		System.out.println(msg);
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		char[] table = msg.toCharArray();
		for (int j=0,i = 0; i < table.length;j++, i = i+2) {
			System.out.println(numbers);
			System.out.println(j+" "+i);
			numbers.add(j,(alphaNum.getNum(String.valueOf(table[i])))*100);
			System.out.println(numbers);
			if (i+1<table.length){
				numbers.set(j, (numbers.get(j)+(alphaNum.getNum(String.valueOf(table[i+1])))));
				System.out.println(j+" "+i);
				System.out.println(numbers);
			} 

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PaddingType2 koe = new PaddingType2();
		String message = "help";
		ArrayList<Integer> testi = null;
		System.out.println(message);
		testi = koe.padded(message);
		System.out.print("Type2 padded text: ");
		System.out.println(testi);

		String[] padded = new String[testi.size()];

		for (int i = 0; i < testi.size(); i++) {
			padded[i] = testi.get(i).toString();
		}
		
		System.out.println("\n");
		System.out.print("Cryptotext: ");
		BigInteger[] crypto = new BigInteger[padded.length];
		crypto=(koe.encrypt(padded,BigInteger.valueOf(113),BigInteger.valueOf(3893)));

		for (BigInteger integer : crypto){
			System.out.print(integer+" ");
		}
	}

}
