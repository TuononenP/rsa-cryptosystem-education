package keypair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class Load_Save_Key {

    /**
     * Returns a byte array of encoded key from the file.
     * @param file Source file to be read.
     * @return byte[] byte array of the encoded key.
     */
    public byte[] loadKeyFromFile(File file) throws IOException {

        InputStream inputS = new FileInputStream(file);

        //Size of the file.
        long length = file.length();

        /*
         * Array can't be created using a long type. It needs to be and int type.
         * Before converting to an int type, check that file is not larger than Integer.MAX_VALUE;
         */
        if (length > Integer.MAX_VALUE) {
            System.out.println("File is too large to process");
            return null;
        }

        //Create the byte array.
        byte[] bytes = new byte[(int)length];

        //Read in the bytes.
        int offset = 0;
        int numRead = 0;
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
			e.printStackTrace();
		}
		try {
			out.write(enc);
			out.close();	
		} catch (IOException e) {
			System.out.println("Error in reading file.");
			e.printStackTrace();
		}
	}
	
	public void checkTwoN(BigInteger A, BigInteger B) {
		if (A.equals(B)) {
			System.out.println("Succesful!");
		}
		else {
			System.out.println("Fail!");
		}
	}
	
	public static void main(String[] args) {
		GenerateKeys gen = new GenerateKeys(512); //generates keys
		GnuRsaPublicKey publicKey = gen.getPublicKey();
		
		Load_Save_Key open = new Load_Save_Key();
		File file = new File("c:\\PubKey.txt");
		Encode_Decode encDec = new Encode_Decode();
		
		byte[] encoded = encDec.encPublicKey(publicKey);
		GnuRsaPublicKey pubK = encDec.decPublicKey(encoded);
		System.out.println("N after encoding and decoding: ");
		BigInteger A = pubK.getN();
		System.out.println("N: " +A);
		
		open.saveKeyToFile(encoded, file); //save to a file
		try {
			encoded = open.loadKeyFromFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pubK = encDec.decPublicKey(encoded); //decode byte array to form a public key
		System.out.println("N from file key: ");
		BigInteger B = pubK.getN();
		System.out.println("N: " +B);
		open.checkTwoN(A, B);
		System.out.println("E: " +pubK.getE());
	}
}
