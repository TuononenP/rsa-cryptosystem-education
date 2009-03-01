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
	//------------------enCode-------------------------


	/**
	 * Returns enCoded message
	 * @param msg String
	 * @return numbers Integer[]
	 */
	public Integer[] enCode(String msg){
		msg=msg.toUpperCase();
		msg=msg.replace(" ", "X");
		
		Integer[] numbers = new Integer[msg.length()];
		char[] table = msg.toCharArray();
		for (int j = 0; j < table.length; j++) {
			numbers[j]=alphaNum.getNum(String.valueOf(table[j]));
		}
		return numbers;
	}


	//------------deCode----------------------------------

	/**
	 * Returns decoded message
	 * @param msg Integer[]
	 * @return String 
	 */
	public String deCode(Integer[] msg){
		String unpadded = "";
		for (int i = 0; i < msg.length; i++) {
			unpadded=unpadded+alphaNum.getLetter(msg[i]);
		}
		unpadded = unpadded.replaceAll("X", " ");
		return unpadded;
	}

	/**
	 * Returns all phases of encoding and encrypting
	 * in a String
	 * @param msg String messge to be encrypted
	 * @param exp BigInteger exponend
	 * @param mod BigInteger modulo
	 * @return String 
	 */
	public String getEnCrypted(String msg, BigInteger exp, BigInteger mod){
		StringBuilder s = new StringBuilder();
		AlphabetNum alpha = new AlphabetNum();
		Encrypt_Decrypt encrypter = new Encrypt_Decrypt();
		
		//--------------------- enCoding-------------------------
		
		Integer[] numberMessage = enCode(msg);
		s.append(msg+"\n\n");
		s.append(alpha.getLetters()+"\n\n");
		s.append("Type 1 encoded text:\n");
		for (Integer integer : numberMessage) {
			s.append(integer.toString()+" ");
		}
		
		//------------------------enCrypting-----------------------
		
		s.append("\n\nEncrypting:\n");
		for (int i = 0; i < numberMessage.length; i++) {
		CalculationPhase phase = new CalculationPhase(new BigInteger(numberMessage[i].toString()), exp);
		s.append(phase.getAll(mod));
		s.append("\n\n");
		}
		s.append("Encrypted: \n");
		BigInteger[] enCrypted = new BigInteger[numberMessage.length];
		String[] enCryptedLetters = new String[enCrypted.length];
		for (int i = 0; i < enCrypted.length; i++) {
			enCrypted[i]=(encrypter.encrypt(numberMessage[i],exp,mod));
			enCryptedLetters[i]=alpha.stringOfNumbersToLetters(enCrypted[i].toString());
			s.append(enCrypted[i]+" ");
		}
		//-------------------------to cryptotext----------------------
		
		s.append("\n\n"+alpha.getNumbers());
		s.append("\n\nCryptotext: \n");
		for (String string : enCryptedLetters) {
			s.append(string+" ");
		}
		return s.toString();
	}
	
	
	/**
	 * Returns all phases of decrypting and decoding
	 * in a String
	 * @param msg String 
	 * @param exp BigInteger
	 * @param mod BigInteger
	 * @return String
	 */
	public String getDeCrypted(String msg, BigInteger exp, BigInteger mod){
		StringBuilder s = new StringBuilder();
		AlphabetNum alpha = new AlphabetNum();
		Encrypt_Decrypt decrypter = new Encrypt_Decrypt();
		//--------------------cryptotext to numbers------------------
		
		s.append("Cryptotext: \n");
		s.append(msg+"\n\n");
		s.append(alpha.getLetters()+"\n\n");
		msg=alpha.stringOfLettersToNumbers(msg);
		String[] stringMessage = msg.split(" "); // Message in String[]
		
		//----------------------deCrypting--------------------------
		
		BigInteger[] numberMessage = new BigInteger[stringMessage.length];
		
		for (int i = 0; i < numberMessage.length; i++) {
			numberMessage[i]=new BigInteger(stringMessage[i]);   // message to BigInteger[]	
		}
		s.append("Encrypted message: \n");
		s.append(msg);
		s.append("\n\nDecrypting: \n");
		for (int i = 0; i < numberMessage.length; i++) {
			CalculationPhase phase = new CalculationPhase(numberMessage[i],exp);
			s.append(phase.getAll(mod));
			s.append("\n\n");
		}
		s.append("Decrypted: \n");
	    Integer[] integerMessage = new Integer[numberMessage.length];
		for (int i = 0; i < numberMessage.length; i++) {
			integerMessage[i]=(decrypter.decryptToInt(numberMessage[i], exp, mod));
			s.append(integerMessage[i]+" ");
		}
		
		//-----------------------enCoding-------------------------------
		
		s.append("\n\n"+alpha.getNumbers());
		s.append("\n\nDecoded text: ");
		String unCoded=(deCode(integerMessage));
		s.append(unCoded+"\n");
		return s.toString();
	}
	//------------------------main for testing----------------------
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Encrypt_Decrypt c = new Encrypt_Decrypt();
		PaddingType1 koe = new PaddingType1();
		String message = "help help";
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
		System.out.print("Decrypted:");
		for (int i = 0; i < crypto.length; i++) {
			testi[i]=c.decryptToInt(crypto[i], BigInteger.valueOf(29), BigInteger.valueOf(91));
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
