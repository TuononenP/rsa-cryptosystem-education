package keypair;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * Save/load public/private key to/from the file.
 * 
 * @author Petri Tuononen
 * @since 2.2.2009
 */
public class Open_Save {

	Encode_Decode encDec;
	Open_Save OS;
	Load_Save_Key loadSave;
	RsaPublicKey pubKey;
	RsaPrivateKey privKey;
	File file;
	JFrame frame;
	byte[] encoded;
	
	/**
	 * Constructor.
	 * @param frame
	 */
	public Open_Save(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * Default constructor.
	 */
	public Open_Save() {
	}
	
	/**
	 * Creates a frame.
	 * @return frame
	 */
	public JFrame createFrame() {
		JFrame frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true); 
    	frame.toFront();
    	frame.setSize(500, 500);
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
    	return frame;
	}

	/**
	 * Returns the user selected file.
	 * File to load.
	 * @return file File to load.
	 */
	private File loadKey() {
		//Show load dialog.
		final JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(createFrame());
		//Get user selected file.
		file = fc.getSelectedFile();
		return file;
	}
	
	/**
	 * Returns the user selected file.
	 * File to save.
	 * @return file File to save.
	 */
	private File saveKey() {
		//Show save dialog.
		final JFileChooser fc = new JFileChooser();
		fc.showSaveDialog(createFrame());
		//Get user selected file.
		file = fc.getSelectedFile();
		return file;
	}
	
	/**
	 * Saves public key in encoded format to the file.
	 * @param publicKey Public key to be saved.
	 */
	public void savePublicKey(RsaPublicKey publicKey) {
		//Encode public key to a byte array
		encDec = new Encode_Decode();
		encoded = encDec.encPublicKey(publicKey);
		
		//Select the file where to save
		OS = new Open_Save();
		file = OS.saveKey();
		
		//Save encoded byte array to a file
		loadSave = new Load_Save_Key();
		loadSave.saveKeyToFile(encoded, file);
	}
	
	/**
	 * Saves private key in encoded format to the file.
	 * @param privateKey Private key to be saved.
	 */
	public void savePrivateKey(RsaPrivateKey privateKey) {
		//Encode private key to a byte array
		encDec = new Encode_Decode();
		encoded = encDec.encPrivateKey(privateKey);
		
		//Select the file where to save
		OS = new Open_Save();
		file = OS.saveKey();
		
		//Save encoded byte array to a file
		loadSave = new Load_Save_Key();
		loadSave.saveKeyToFile(encoded, file);
	}
	
	/**
	 * Loads public key from the file to instance.
	 * Encoded byte array from the file is first decoded.
	 * 
	 * @return Public key instance.
	 */
	public RsaPublicKey loadPublicKey() {
		//Select the file to open
		OS = new Open_Save();
		file = OS.loadKey();
		
		//Load encoded bytes from the file
		loadSave = new Load_Save_Key();
		try {
			encoded = loadSave.loadKeyFromFile(file);
		} catch (IOException e) {
			System.out.println("Error in reading file.");
			e.printStackTrace();
		}
		
		//Decode to key instance
		encDec = new Encode_Decode();
		pubKey = encDec.decPublicKey(encoded);
		
		return pubKey;
	}
	
	/**
	 * Loads private key from the file to instance.
	 * Encoded byte array from the file is first decoded.
	 * 
	 * @return Private key instance.
	 */
	public RsaPrivateKey loadPrivateKey() {
		//Select the file to open
		OS = new Open_Save();
		file = OS.loadKey();
		
		//Load encoded bytes from the file
		loadSave = new Load_Save_Key();
		try {
			encoded = loadSave.loadKeyFromFile(file);
		} catch (IOException e) {
			System.out.println("Error in reading file.");
			e.printStackTrace();
		}
		
		//Decode to key instance
		encDec = new Encode_Decode();
		privKey = encDec.decPrivateKey(encoded);
		
		return privKey;
	}
	
	public static void main(String[] args) {
		//Generate keys
		GenerateKeys gen = new GenerateKeys(512); //generates keys
		RsaPublicKey publicKey = gen.getPublicKey();
		
		Open_Save OS = new Open_Save();
		OS.savePublicKey(publicKey);
	}
	
}
