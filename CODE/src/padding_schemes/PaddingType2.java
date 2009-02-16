package padding_schemes;
import java.math.BigInteger;	
import java.util.ArrayList;
import keypair.Encrypt_Decrypt;
/**
 *  Padding type 2 for rsa cryptography
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

	/**
	 * Encodes message with padding type 2
	 * @param msg 
	 * @return ArrayList<Integer> containing encoded message
	 */
	public ArrayList<Integer> enCode(String msg){
		msg=msg.toUpperCase();
		msg=msg.replace(" ", "X");
		//System.out.println(msg);
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		char[] table = msg.toCharArray();
		for (int j=0,i = 0; i < table.length;j++, i = i+2) {
			numbers.add(j,(alphaNum.getNum(String.valueOf(table[i])))*100);
			if (i+1<table.length){
				numbers.set(j, (numbers.get(j)+(alphaNum.getNum(String.valueOf(table[i+1])))));
			} 

		}

		return numbers;
	}
	
	/**
	 * Decodes message with padding type 2
	 * @param msg ArrayList<Integer>
	 * @return String decoded message
	 */
	public String deCode(ArrayList<Integer> msg){
		String decoded = "";
		Integer help, apu = null;
		for (int i = 0; i < msg.size(); i++) {
			help=(msg.get(i));
			apu = help/100;
			decoded=decoded+alphaNum.getLetter(apu);
			decoded=decoded+alphaNum.getLetter(help-(apu*100));
		}
		return decoded;
	}

	public static void main(String[] args) {
		Encrypt_Decrypt c = new Encrypt_Decrypt();
		PaddingType2 koe = new PaddingType2();
		String message = "help";
		ArrayList<Integer> testi = null;
		System.out.println(message);
		testi = koe.enCode(message);
		System.out.print("Type2 encoded text: ");
		System.out.print(testi);

		String[] padded = new String[testi.size()];

		for (int i = 0; i < testi.size(); i++) {
			padded[i] = testi.get(i).toString();
		}
		
		System.out.println();
		System.out.print("Encrypted text: ");
		BigInteger[] crypto = new BigInteger[padded.length];
		for (int i = 0; i < crypto.length; i++) {
			crypto[i]=(c.encrypt(testi.get(i),BigInteger.valueOf(113),BigInteger.valueOf(3893)));
		}
		
		for (BigInteger integer : crypto){
			System.out.print(integer+" ");
		}
		System.out.println();
		System.out.print("Decrypted text:");
		testi.clear();
		for (int i = 0; i < crypto.length; i++) {
			testi.add(c.decryptToInt(crypto[i], BigInteger.valueOf(1937), BigInteger.valueOf(3893)));
		}
		System.out.println(testi);
		System.out.print("Type2 decoded text:");
		String d=koe.deCode(testi);
		System.out.println(d);
	}
	
}
