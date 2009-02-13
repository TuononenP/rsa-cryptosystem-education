package padding_schemes;
import java.awt.Font;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.swing.JTextArea;

import keypair.Encrypt_Decrypt;

import gui.FullScreen;


public class Testing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//SymbolicCalculation f = new SymbolicCalculation(BigInteger.valueOf(63), new BigInteger("29"));
		JTextArea j = new JTextArea();
		StringBuilder s = new StringBuilder();
		
		Encrypt_Decrypt c = new Encrypt_Decrypt();
		PaddingType2 koe = new PaddingType2();
		String message = "HELP";
		ArrayList<Integer> testi = null;
		
		//---------------encoding----------------------------
		//System.out.println(message);
		s.append(message+"\n");
		testi = koe.enCode(message);
		//System.out.print("Type2 encoded text: ");
		s.append("Type2 encoded text: ");
//		System.out.print(testi);
		s.append(testi.toString()+"\n");
		
		String[] padded = new String[testi.size()];

		for (int i = 0; i < testi.size(); i++) {
			padded[i] = testi.get(i).toString();
		}
		
		//-----------------encrypting---------------------------------
		s.append("Encrypting: \n");
		for (int i = 0; i < padded.length; i++) {
			//s.append();
			CalculationPhase f = new CalculationPhase(new BigInteger(padded[i]), new BigInteger("113"));
		s.append(f.getAll(new BigInteger("3893")));
		s.append("\n\n");
		}
		s.append("Encryped text: ");
		
		BigInteger[] crypto = new BigInteger[padded.length];
		for (int i = 0; i < crypto.length; i++) {
			crypto[i]=(c.encrypt(testi.get(i),BigInteger.valueOf(113),BigInteger.valueOf(3893)));
		}
		for (BigInteger integer : crypto){
			s.append(integer.toString()+" ");
		}
		//----------------decrypting-----------------------------
		
		s.append("\n\nDecrypting: \n");
		for (int i = 0; i < crypto.length; i++) {
			CalculationPhase f = new CalculationPhase(crypto[i],BigInteger.valueOf(1937));
			s.append(f.getAll(new BigInteger("3893")));
			s.append("\n\n");
		}
		s.append("Decrypted text: ");
		testi.clear();
		for (int i = 0; i < crypto.length; i++) {
			testi.add(c.decrypt(crypto[i], BigInteger.valueOf(1937), BigInteger.valueOf(3893)));
			s.append(crypto[i]+" ");
		}
		
		
		//---------------------decoding--------------------------
		
		s.append("\n\n Decoded text: ");
		s.append(koe.deCode(testi)+"\n");
		
		//--------------------setting FullScreen--------------------
		j.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		j.setText(s.toString());
		FullScreen screen = new FullScreen(j);
	}

}