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
import java.math.BigInteger;	
import java.util.ArrayList;
import keypair.EncryptDecrypt;

/**
 *  Padding type 2 for rsa cryptography
 *  @author Jukka Tuominen
 */
public class PaddingType2 {
	
	private AlphabetNum alphaNum = new AlphabetNum();
	
	//---------------------------enCode----------------------------
	/**
	 * Encodes message with padding type 2
	 * @param msg String
	 * @return ArrayList<Integer> containing encoded message
	 */
	public ArrayList<Integer> enCode(String msg){
		if (msg.length()%2!=0) { // if %2!=0 add x to get %2=0
			msg=msg+"x";
		}
		msg=msg.toUpperCase(); // to big letters
		msg=msg.replace(" ", "X"); // replace all white spaces with X
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

	//-------------------deCode--------------------------
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
		decoded=decoded.replaceAll("X", " ");
		return decoded;
	}
	
//----------------getLetters----------------------------------------
/**
 * Returns String containing letters
 * with numbers.
 * @param msg ArrayList<Integer> 
 * @return s String
 */
	public String getLetters(ArrayList<Integer> msg){
		String s="";
		Integer help, apu = null;
		for (Integer integer : msg) {
			help = integer;
			apu=help/100;
			s=s+apu.toString()+"=";
			s=s+alphaNum.getLetter(apu);
			s=s+" ; ";
			s=s+(help-(apu*100))+"=";
			s=s+alphaNum.getLetter(help-(apu*100));
			s=s+" ; ";
		}
		return s;
		
	}
	/**
	 * Returns whole enCode + enCrypt phase
	 * @param msg String message to encrypt
	 * @param exp BigInteger
	 * @param mod BigInteger
	 * @return String containing phases
	 */
	public String getEnCrypted(String msg, BigInteger exp, BigInteger mod){
		StringBuilder s = new StringBuilder();
		AlphabetNum alpha = new AlphabetNum();
		EncryptDecrypt encrypter = new EncryptDecrypt();
		
		//--------------------- enCoding-------------------------
		ArrayList<Integer> numberMessage = enCode(msg);
		s.append(msg+"\n\n");
		s.append(alpha.getLetters()+"\n\n");
		s.append("Type 2 encoded text:\n");
		for (Integer integer : numberMessage) {
			s.append(integer.toString()+" ");
		}
		
		//------------------------enCrypting-----------------------
		s.append("\n\nEncrypting:\n");
		for (int i = 0; i < numberMessage.size(); i++) {
		CalculationPhase phase = new CalculationPhase(new BigInteger(numberMessage.get(i).toString()), exp);
		s.append(phase.getAll(mod));
		s.append("\n\n");
		}
		s.append("Encryped: \n");
		
		BigInteger[] enCrypted = new BigInteger[numberMessage.size()];
		String[] cryptotext = new String[numberMessage.size()];
		for (int i = 0; i < enCrypted.length; i++) {
			enCrypted[i]=(encrypter.encrypt(numberMessage.get(i),exp,mod));
			cryptotext[i]=alpha.stringOfNumbersToLetters(enCrypted[i].toString());
			s.append(enCrypted[i]+" ");
		}
		s.append("\n\n"+alpha.getNumbers());
		s.append("\n\nCryptotext:\n");
		for (String string : cryptotext) {
			s.append(string+" ");
		}
		return s.toString();
	}
	
	/**
	 * Returns all phases of deCrypt and deCode
	 * @param msg message
	 * @param exp BigInteger
	 * @param mod BigInteger
	 * @return String
	 */
	public String getDeCrypted(String msg, BigInteger exp, BigInteger mod){
		StringBuilder s = new StringBuilder();
		AlphabetNum alpha = new AlphabetNum();
		EncryptDecrypt decrypter = new EncryptDecrypt();
		
		s.append("Cryptotext:\n");
		s.append(msg+"\n\n");
		s.append(alpha.getLetters()+"\n\n");
		
		//-----------cryptotext to numbers----------------------------
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
	    ArrayList<Integer> integerMessage = new ArrayList<Integer>();
		for (int i = 0; i < numberMessage.length; i++) {
			integerMessage.add(i, decrypter.decryptToInt(numberMessage[i], exp, mod));
			s.append(integerMessage.get(i)+" ");
		}
		
		//-----------------------deCoding-------------------------------
		s.append("\n\n"+alpha.getNumbers());
		s.append("\n\nDecoded text: ");
		s.append(deCode(integerMessage)+"\n");
		return s.toString();
	}	
	
	/**
	 * Returns encrypted message. 
	 * @param msg String message to encrypt
	 * @param exp BigInteger
	 * @param mod BigInteger
	 * @return String containing phases
	 */
	public String getEnCryptedSecure(String msg, BigInteger exp, BigInteger mod){
		StringBuilder s = new StringBuilder();
		AlphabetNum alpha = new AlphabetNum();
		EncryptDecrypt encrypter = new EncryptDecrypt();
		
		//--------------------- enCoding-------------------------
		ArrayList<Integer> numberMessage = enCode(msg);
		
		//------------------------enCrypting-----------------------
		BigInteger[] enCrypted = new BigInteger[numberMessage.size()];
		String[] cryptotext = new String[numberMessage.size()];
		for (int i = 0; i < enCrypted.length; i++) {
			enCrypted[i]=(encrypter.encrypt(numberMessage.get(i),exp,mod));
			cryptotext[i]=alpha.stringOfNumbersToLetters(enCrypted[i].toString());
		}
		for (String string : cryptotext) {
			s.append(string+" ");
		}
		return s.toString().trim();
	}
	
	/**
	 * Returns decoded message
	 * @param msg message
	 * @param exp BigInteger
	 * @param mod BigInteger
	 * @return String
	 */
	public String getDeCryptedSecure(String msg, BigInteger exp, BigInteger mod){
		StringBuilder s = new StringBuilder();
		AlphabetNum alpha = new AlphabetNum();
		EncryptDecrypt decrypter = new EncryptDecrypt();
		
		//-----------cryptotext to numbers----------------------------
		msg=alpha.stringOfLettersToNumbers(msg);
		String[] stringMessage = msg.split(" "); // Message in String[]
		
		//----------------------deCrypting--------------------------
		BigInteger[] numberMessage = new BigInteger[stringMessage.length];
		
		for (int i = 0; i < numberMessage.length; i++) {
			numberMessage[i]=new BigInteger(stringMessage[i]);   // message to BigInteger[]	
		}
	    ArrayList<Integer> integerMessage = new ArrayList<Integer>();
		for (int i = 0; i < numberMessage.length; i++) {
			integerMessage.add(i, decrypter.decryptToInt(numberMessage[i], exp, mod));
		}
		
		//-----------------------deCoding-------------------------------
		s.append(deCode(integerMessage));
		return s.toString().trim();
	}	
	
//------------------------main for testing----------------------------	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EncryptDecrypt c = new EncryptDecrypt();
		PaddingType2 koe = new PaddingType2();
		String message = "help helppi";
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
