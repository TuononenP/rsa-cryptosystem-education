package padding_schemes;
import java.math.BigInteger;
import keypair.Encrypt_Decrypt;
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

	public Integer[] enCode(String msg){
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

	public String deCode(Integer[] msg){
		String unpadded = "";
		for (int i = 0; i < msg.length; i++) {
			unpadded=unpadded+alphaNum.getLetter(msg[i]);
		}
		return unpadded;
	}


	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Encrypt_Decrypt c = new Encrypt_Decrypt();
		PaddingType1 koe = new PaddingType1();
		String message = "help";
		Integer[] testi = new Integer[message.length()];
		System.out.println(message);
		testi = koe.enCode(message);
		System.out.print("Type1 encoded text: ");
		for (int i = 0; i < testi.length; i++) {
			System.out.print(testi[i]+" ");
		}
		String[] padded = new String[testi.length];

		for (int i = 0; i < testi.length; i++) {
			padded[i] = testi[i].toString();
		}
		System.out.println();
		System.out.print("Encrypted text: ");
		BigInteger[] crypto = new BigInteger[padded.length];

		for (int i = 0; i < crypto.length; i++) {
			crypto[i]=(c.encrypt(testi[i],BigInteger.valueOf(29),BigInteger.valueOf(91)));
		}

		for (BigInteger integer : crypto){
			System.out.print(integer+" ");
		}
		System.out.println();
		System.out.print("Decrypted text:");
		for (int i = 0; i < crypto.length; i++) {
			testi[i]=c.decrypt(crypto[i], BigInteger.valueOf(29), BigInteger.valueOf(91));
		}
		for (Integer integer : testi) {
			System.out.print(integer+" ");
		}
		System.out.println();
		System.out.print("Type1 decoded text:");
		message=koe.deCode(testi);
		System.out.println(message);
	}
}
