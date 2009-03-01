package gui_logics;

import java.io.*;

/**
 * Custom file filter for JFileChooser used to load/save execution text files.
 * 
 * @author Petri Tuononen
 * @since 1.3.2009
 */
public class ExecutionFileFilter extends javax.swing.filechooser.FileFilter {
    
	/**
	 * Accepts files that are either folders or .txt files.
	 * @return boolean
	 */
	public boolean accept(File f) {
        return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
    }
    
	/**
	 * Returns description about file types that can be selected.
	 * @return description
	 */
    public String getDescription() {
        return ".txt files";
    }
    
}