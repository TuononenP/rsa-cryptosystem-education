import java.io.*;
import java.nio.channels.FileChannel;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.nio.ByteBuffer;

import keypair.Open_Save;

public class Load_Save_Exec {

	Open_Save openSave;
	JFrame frame;
	JTextArea textArea;
	
	public Load_Save_Exec(JFrame frame, JTextArea textArea) {
		this.frame = frame;
		this.textArea = textArea;
	}
	
	public void saveExecToFile() {
		openSave = new Open_Save(frame);
		//show file chooser to select file to save.
		File file = openSave.chooseFileToSave();
		//write contents of textfield into choosed file. 
		try {
			BufferedWriter fileOut = new BufferedWriter(new FileWriter(file));
			fileOut.write(textArea.getText());
			fileOut.close();
		} catch (IOException e) {
			System.out.println("Writing execution into a file "+file.getName()+" failed.");
			e.printStackTrace();
		}
	}
	
	/*
	 * One of the fastest methods to read a file.
	 * Uses FileChannel with direct ByteBuffer and byte array reads.
	 * This method reduces the amount of data copying and enables
	 * the JVM to read new data into the application's own array without
	 * going through multiple intermediate buffers.
	 */
	public void loadExecFromFile() {
		openSave = new Open_Save(frame);
		//show file chooser to select file to load.
		File file = openSave.chooseFileToLoad();
		
		//clear textArea
		textArea.setText("");

		long file_size = file.length();
		int byteSize = (int) file_size;
		FileInputStream f = null;
		try {
			f = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println("File "+file.getName()+" not found.");
			e.printStackTrace();
		}
		FileChannel ch = f.getChannel( );
		ByteBuffer bb = ByteBuffer.allocateDirect(131072);  //128kB buffer size is fastest
		byte[] barray = new byte[byteSize];
		long checkSum = 0L;
		int nRead, nGet;
		try {
			while ( (nRead=ch.read( bb )) != -1 )
			{
			    if ( nRead == 0 )
			        continue;
			    bb.position( 0 );
			    bb.limit( nRead );
			    while( bb.hasRemaining( ) )
			    {
			        nGet = Math.min( bb.remaining( ), byteSize);
			        bb.get( barray, 0, nGet );
			        for ( int i=0; i<nGet; i++ )
			            checkSum += barray[i];
			    }
			    textArea.setText(new String(barray));
			    bb.clear( );
			}
		} catch (IOException e) {
			System.out.println("Error in reading a file "+file.getName());
			e.printStackTrace();
		}
	}
	
}