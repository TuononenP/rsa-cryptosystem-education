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

/**
 * Custom file filter for JFileChooser used to load/save RSA keys.
 * 
 * @author Petri Tuononen
 * @since 1.3.2009
 */
public class RsaKeyFileFilter extends javax.swing.filechooser.FileFilter {
    
	/**
	 * Accepts files that are either folders or .pub / .priv files.
	 * @return boolean
	 */
	public boolean accept(File f) {
        return f.isDirectory() || f.getName().toLowerCase().endsWith(".pub") || f.getName().toLowerCase().endsWith(".priv");
    }
    
	/**
	 * Returns description about file types that can be selected.
	 * @return description
	 */
    public String getDescription() {
        return ".pub & .priv files";
    }
    
}