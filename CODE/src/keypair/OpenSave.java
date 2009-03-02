/*
	Copyright (C) 2009 Petri Tuononen

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
package keypair;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import guiLogics.*;

/**
 * Save/load public/private key to/from the file.
 * 
 * @author Petri Tuononen
 * @since 2.2.2009
 */
public class OpenSave {

	private EncodeDecode encDec;
	private LoadSaveKey loadSave;
	private RsaPublicKey pubKey;
	private RsaPrivateKey privKey;
	private File file;
	private JFrame frame;
	byte[] encoded;
	
	/**
	 * Constructor.
	 * @param frame
	 */
	public OpenSave(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * Returns the user selected file.
	 * File to load.
	 * @return file File to load.
	 */
	public File chooseFileToLoad() {
		//Show load dialog.
		final JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new RsaKeyFileFilter());
		int result = fc.showOpenDialog(frame);
		switch (result) {
			case JFileChooser.APPROVE_OPTION : 
				if (fc.getSelectedFile() != null) { // A file was selected
					file = fc.getSelectedFile();
				}
				else { // No file selected 
					JOptionPane.showMessageDialog(frame, "No file was selected", "File selection info", JOptionPane.INFORMATION_MESSAGE);
				}
				break ; 
			case JFileChooser.CANCEL_OPTION : // Selection canceled
				break ; 
			case JFileChooser.ERROR_OPTION : // An error has occurred 
				JOptionPane.showMessageDialog(frame, "An error occured while selecting a file to load", "File selection error", JOptionPane.ERROR_MESSAGE);
				break ; 
		}
		return file;
	}
	
	/**
	 * Returns the user selected file.
	 * @return file File to save.
	 */
	public File chooseFileToSave() {
		//Show save dialog.
		final JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new RsaKeyFileFilter());
		int result = fc.showSaveDialog(frame);
		switch (result) { 
			case JFileChooser.APPROVE_OPTION : 
				if (fc.getSelectedFile() != null) { // A file was selected
					file = fc.getSelectedFile();
				}
				else { // No file selected 
					JOptionPane.showMessageDialog(frame, "No file was selected", "File selection info", JOptionPane.INFORMATION_MESSAGE);
				}
				break ; 
			case JFileChooser.CANCEL_OPTION : // Selection canceled
				break ; 
			case JFileChooser.ERROR_OPTION : // An error has occurred 
				JOptionPane.showMessageDialog(frame, "An error occured while selecting a file to save", "File selection error", JOptionPane.ERROR_MESSAGE);
				break ; 
			} 
		return file;
	}
	
	/**
	 * Saves public key in encoded format to the file.
	 * @param publicKey Public key to be saved.
	 */
	public void savePublicKey(RsaPublicKey publicKey) {
		//Encode public key to a byte array
		encDec = new EncodeDecode();
		encoded = encDec.encPublicKey(publicKey);
		
		//Select the file where to save
		file = chooseFileToSave();
		
		//file found
		if (file!=null) {
			//get file extension
			String[] splits = file.getName().split("\\.");
			String extension = splits[splits.length-1];
			
			//extension is not .pub
			if (!extension.equals("pub")) {
				//new file name
				String newFileName = file.getName() +".pub";
				//new file path
				int length = (int)file.getPath().length() - (int)file.getName().length();
				String newFilePath =  file.getPath().substring(0, length)+newFileName;
				
				//create new file with correct extension
				file = new File(newFilePath);
			}
			
			//Save encoded byte array to a file
			loadSave = new LoadSaveKey(frame);
			loadSave.saveKeyToFile(encoded, file);
		}
	}
	
	/**
	 * Saves private key in encoded format to the file.
	 * @param privateKey Private key to be saved.
	 */
	public void savePrivateKey(RsaPrivateKey privateKey) {
		//Encode private key to a byte array
		encDec = new EncodeDecode();
		encoded = encDec.encPrivateKey(privateKey);
		
		//Select the file where to save
		file = chooseFileToSave();
		
		//file found
		if (file!=null) {
			//get file extension
			String[] splits = file.getName().split("\\.");
			String extension = splits[splits.length-1];
			
			//extension is not .priv
			if (!extension.equals("priv")) {
				//new file name
				String newFileName = file.getName() +".priv";
				//new file path
				int length = (int)file.getPath().length() - (int)file.getName().length();
				String newFilePath =  file.getPath().substring(0, length)+newFileName;
				
				//create new file with correct extension
				file = new File(newFilePath);
			}
			
			//Save encoded byte array to a file
			loadSave = new LoadSaveKey(frame);
			loadSave.saveKeyToFile(encoded, file);
		}
	}
	
	/**
	 * Loads public key from the file to instance.
	 * Encoded byte array from the file is first decoded.
	 * 
	 * @return Public key instance.
	 */
	public RsaPublicKey loadPublicKey() {
		//Select the file to open
		file = chooseFileToLoad();
		
		if (file==null) {
			//file not found
			pubKey = null;
		}else {
			//Load encoded bytes from the file
			loadSave = new LoadSaveKey(frame);
			encoded = loadSave.loadKeyFromFile(file);
			
			//Decode to key instance
			encDec = new EncodeDecode();
			pubKey = encDec.decPublicKey(encoded);
		}
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
		file = chooseFileToLoad();
		
		if (file==null) {
			//file not found
			privKey = null;
		}else {
			//Load encoded bytes from the file
			loadSave = new LoadSaveKey(frame);
			encoded = loadSave.loadKeyFromFile(file);
			
			//Decode to key instance
			encDec = new EncodeDecode();
			privKey = encDec.decPrivateKey(encoded);
		}
		return privKey;
	}
	
}
