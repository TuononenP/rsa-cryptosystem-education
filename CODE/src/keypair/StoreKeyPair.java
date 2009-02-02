package keypair;

import java.security.KeyPair;

/**
 * 
 * @author Petri Tuononen
 * @since 1.2.2009
 */
public class StoreKeyPair {
	
	KeyPair keyPair;
	
	/**
	 * Constructor.
	 * @param pubK
	 * @param privK
	 */
	public StoreKeyPair(RsaPublicKey pubK, RsaPrivateKey privK) {
		keyPair = new KeyPair(pubK, privK);
	}
	
	/**
	 * Default constructor.
	 */
	public StoreKeyPair() {
		
	}
	
	public KeyPair getKeyPair() {
		return keyPair;
	}
	
	public void setKeyPair(RsaPublicKey pubK, RsaPrivateKey privK) {
		keyPair = new KeyPair(pubK, privK);
	}
	
}
