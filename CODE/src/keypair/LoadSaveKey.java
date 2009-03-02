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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoadSaveKey {
	
	private JFrame frame;
	
	/**
	 * Constructor.
	 */
	public LoadSaveKey(JFrame frame) {
		this.frame = frame;
	}

    /**
     * Returns a byte array of encoded key from the file.
     * @param file Source file to be read.
     * @return byte[] byte array of the encoded key.
     */
    public byte[] loadKeyFromFile(File file) {

        InputStream inputS = null;
		try {
			inputS = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(frame, "File "+file.getName()+" not found.", "File load error", JOptionPane.ERROR_MESSAGE);
		}

        //Size of the file.
        long length = file.length();

        /*
         * Array can't be created using a long type. It needs to be and int type.
         * Before converting to an int type, check that file is not larger than Integer.MAX_VALUE;
         */
        if (length > Integer.MAX_VALUE) {
        	JOptionPane.showMessageDialog(frame, "File "+file.getName()+" is too large to process", "File load error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        //Create the byte array.
        byte[] bytes = new byte[(int)length];

        //Read in the bytes.
        int offset = 0;
        int numRead = 0;
        try {
			while ( (offset < bytes.length)
			        &&
			        ( (numRead=inputS.read(bytes, offset, bytes.length-offset)) >= 0) ) {

			    offset += numRead;
			}
			//Ensure all the bytes have been read in.
	        if (offset < bytes.length) {
	            throw new IOException("Could not completely read file " + file.getName());
	        }
	        inputS.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Error in reading file "+ file.getName(), "File load error", JOptionPane.ERROR_MESSAGE);
		}
		return bytes;
    }
	
	/**
	 * Saves public/private key to a file.
	 * @param enc Encoded byte array of key.
	 * @param file File where encoded byte array will be written.
	 */
	public void saveKeyToFile(byte[] enc, File file) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(frame, "File "+file.getName()+" not found.", "File save error", JOptionPane.ERROR_MESSAGE);
		}
		try {
			out.write(enc);
			out.close();	
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Error in reading file "+file.getName(), "File save error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
