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

/**
 * Encode/decode public/private keys.
 * Used when keys are saved to a file/loaded from a file.
 * 
 * @author Petri Tuononen
 * @since 1.2.2009
 */
public class EncodeDecode {

	/**
	 * Encodes public key.
	 * @param publicKey Public key to be encoded. 		
	 * @return enc Byte array.
	 */
	public byte[] encPublicKey(RsaPublicKey publicKey) {
		byte[] enc = publicKey.getEncoded(IKeyPairCodec.RAW_FORMAT);
		return enc;
	}
	
	/**
	 * Encodes private key.
	 * @param privateKey Private key to be encoded.
	 * @return enc Byte array.
	 */
	public byte[] encPrivateKey(RsaPrivateKey privateKey) {
		byte[] enc = privateKey.getEncoded(IKeyPairCodec.RAW_FORMAT);
		return enc;
	}
	
	/**
	 * Decodes public key.
	 * @param enc Encoded byte array to be decoded to public key.
	 * @return pubK Public key instance.
	 */
	public RsaPublicKey decPublicKey(byte[] enc) {
		RsaPublicKey pubK = RsaPublicKey.valueOf(enc);
		return pubK;
	}
	
	/**
	 * Decodes private key.
	 * @param enc Encoded byte array to be decoded to private key.
	 * @return privK Private key instance.
	 */
	public RsaPrivateKey decPrivateKey(byte[] enc) {
		RsaPrivateKey privK = RsaPrivateKey.valueOf(enc);
		return privK;
	}
	
}
