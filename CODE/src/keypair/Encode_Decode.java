package keypair;

/**
 * Encode/decode public/private keys.
 * 
 * @author Petri Tuononen
 * @since 1.2.2009
 */
public class Encode_Decode {

	/**
	 * Encodes public key.
	 * @param publicKey Public key to be encoded. 		
	 * @return enc Byte array.
	 */
	public byte[] encPublicKey(GnuRsaPublicKey publicKey) {
		byte[] enc = publicKey.getEncoded(IKeyPairCodec.RAW_FORMAT);
		return enc;
	}
	
	/**
	 * Encodes private key.
	 * @param privateKey Private key to be encoded.
	 * @return enc Byte array.
	 */
	public byte[] encPrivateKey(GnuRsaPrivateKey privateKey) {
		byte[] enc = privateKey.getEncoded(IKeyPairCodec.RAW_FORMAT);
		return enc;
	}
	
	/**
	 * Decodes public key.
	 * @param enc Encoded byte array to be decoded to public key.
	 * @return pubK Public key instance.
	 */
	public GnuRsaPublicKey decPublicKey(byte[] enc) {
		GnuRsaPublicKey pubK = GnuRsaPublicKey.valueOf(enc);
		return pubK;
	}
	
	/**
	 * Decodes private key.
	 * @param enc Encoded byte array to be decoded to private key.
	 * @return privK Private key instance.
	 */
	public GnuRsaPrivateKey decPrivateKey(byte[] enc) {
		GnuRsaPrivateKey privK = GnuRsaPrivateKey.valueOf(enc);
		return privK;
	}
	
}
