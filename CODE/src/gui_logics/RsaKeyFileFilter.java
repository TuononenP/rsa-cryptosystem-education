package gui_logics;

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