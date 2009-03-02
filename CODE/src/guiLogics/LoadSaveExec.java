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
package guiLogics;

import java.io.*;

import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;


import keypair.OpenSave;

/**
 * Saves execution text to a file.
 * Loads execution from a text file to a textfield.
 * 
 * @author Petri Tuononen
 * @since 8.2.2009
 */
public class LoadSaveExec {

	OpenSave openSave;
	JFrame frame;
	JTextArea textArea;
	File file;
	
	/**
	 * Constructor.
	 * @param frame
	 * @param textArea
	 */
	public LoadSaveExec(JFrame frame, JTextArea textArea) {
		this.frame = frame;
		this.textArea = textArea;
	}
	
	/**
	 * Saves execution text to a file.
	 */
	public void saveExecToFile() {
		//show file chooser to select file to save.
		File file = chooseFileToSave();
		if (file != null) {
			//get file extension
			String[] splits = file.getName().split("\\.");
			String extension = splits[splits.length-1];
			
			//extension is not .pub
			if (!extension.equals("txt")) {
				//new file name
				String newFileName = file.getName() +".txt";
				//new file path
				int length = (int)file.getPath().length() - (int)file.getName().length();
				String newFilePath =  file.getPath().substring(0, length)+newFileName;
				
				//create a new file with a correct extension
				file = new File(newFilePath);
			}
			try {
				//write contents of a textfield into a chosen file. 
				BufferedWriter fileOut = new BufferedWriter(new FileWriter(file));
				fileOut.write(textArea.getText());
				fileOut.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, "Writing execution into a file failed.", "File write error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * One of the fastest methods to read a file.
	 * Uses FileChannel with direct ByteBuffer and byte array reads.
	 * This method reduces the amount of data copying and enables
	 * the JVM to read new data into the application's own array without
	 * going through multiple intermediate buffers.
	 */
	public void loadExecFromFile() {
		//show file chooser to select file to load.
		File file = chooseFileToLoad();

		if (file != null) {
			//clear textArea
			textArea.setText("");

			long file_size = file.length();
			int byteSize = (int) file_size;
			FileInputStream f = null;
			try {
				f = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(frame, "File "+file.getName()+" not found.", "File load error", JOptionPane.ERROR_MESSAGE);
			}
			FileChannel ch = f.getChannel( );
			ByteBuffer bb = ByteBuffer.allocateDirect(131072);  //128kB buffer size is fastest
			byte[] barray = new byte[byteSize];
			long checkSum = 0L;
			int nRead, nGet;
			try {
				while ((nRead=ch.read(bb)) != -1) {
				    if (nRead == 0)
				        continue;
				    bb.position(0);
				    bb.limit(nRead);
				    while(bb.hasRemaining( )) {
				        nGet = Math.min( bb.remaining( ), byteSize);
				        bb.get(barray, 0, nGet);
				        for (int i=0; i<nGet; i++)
				            checkSum += barray[i];
				    }
				    textArea.setText(new String(barray));
				    textArea.setCaretPosition(0);
				    bb.clear( );
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frame, "Error in reading a file "+file.getName(), "File read error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Returns the user selected file.
	 * File to load.
	 * @return file File to load.
	 */
	private File chooseFileToLoad() {
		//Show load dialog.
		final JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new ExecutionFileFilter());
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
	private File chooseFileToSave() {
		//Show save dialog.
		final JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new ExecutionFileFilter());
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
	
}