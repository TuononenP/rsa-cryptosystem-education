package keypair;

// ----------------------------------------------------------------------------
// $Id: Registry.java.in,v 1.4 2005/10/06 04:24:13 rsdio Exp $
//
// Copyright (C) 2001, 2002, 2003, 2004 Free Software Foundation, Inc.
//
// This file is part of GNU Crypto.
//
// GNU Crypto is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2, or (at your option)
// any later version.
//
// GNU Crypto is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; see the file COPYING.  If not, write to the
//
//    Free Software Foundation Inc.,
//    51 Franklin Street, Fifth Floor,
//    Boston, MA 02110-1301
//    USA
//
// Linking this library statically or dynamically with other modules is
// making a combined work based on this library.  Thus, the terms and
// conditions of the GNU General Public License cover the whole
// combination.
//
// As a special exception, the copyright holders of this library give
// you permission to link this library with independent modules to
// produce an executable, regardless of the license terms of these
// independent modules, and to copy and distribute the resulting
// executable under terms of your choice, provided that you also meet,
// for each linked independent module, the terms and conditions of the
// license of that module.  An independent module is a module which is
// not derived from or based on this library.  If you modify this
// library, you may extend this exception to your version of the
// library, but you are not obligated to do so.  If you do not wish to
// do so, delete this exception statement from your version.
// ----------------------------------------------------------------------------

/**
 * A placeholder for <i>names</i> and <i>literals</i> used throughout this
 * library.
 *
 * @version $Revision: 1.4 $
 */
public interface Registry {

	// Asymmetric keypair generators............................................
	String RSA_KPG =  "rsa";
	
	// Format IDs used to identify how we externalise asymmetric keys ..........
	String RAW_ENCODING = "gnu.crypto.raw.format";
	int RAW_ENCODING_ID = 1;

	// Magic bytes we generate/expect in externalised asymmetric keys ..........
	// the four bytes represent G (0x47) for GNU, 1 (0x01) for Raw format,
	// D (0x44) for DSS, R (0x52) for RSA, H (0x48) for Diffie-Hellman, or S
	// (0x53) for SRP-6, and finally P (0x50) for Public, p (0x70) for private,
	// or S (0x53) for signature.
	byte[] MAGIC_RAW_RSA_PUBLIC_KEY =    new byte[] {0x47, RAW_ENCODING_ID, 0x52, 0x50};
	byte[] MAGIC_RAW_RSA_PRIVATE_KEY =   new byte[] {0x47, RAW_ENCODING_ID, 0x52, 0x70};
	byte[] MAGIC_RAW_RSA_PSS_SIGNATURE = new byte[] {0x47, RAW_ENCODING_ID, 0x52, 0x53};
}
