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
package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.*;

/**
 * Help window. Contents are loaded from a html file.
 * 
 * @author Petri Tuononen
 * @since 11.02.2009
 */
public class Help extends JFrame {
	
	private static final long serialVersionUID = -7279256753979554192L;

	/**
	 * Constructor.
	 */
	public Help() {
		initComponents();
	}

	//Variables declaration
	private JScrollPane scrollPane1;
	private JEditorPane editorPane1;
	private JButton button1;
	ClassLoader cl = this.getClass().getClassLoader();
	URL urlHelp = cl.getResource("Help.html");

	/**
	 * Initializes graphical user interface components.
	 */
	private void initComponents() {
		scrollPane1 = new JScrollPane();
		editorPane1 = new JEditorPane();
		button1 = new JButton();

		//======== container ========
		setVisible(true);
		setTitle("Help - RSA Education Cryptosystem");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {10, 0, 65, 5, 0};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {10, 0, 30, 5, 0};
		((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 0.0, 1.0E-4};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0, 0.0, 1.0E-4};

		//======== scrollPane1 ========
		{
			//---- editorPane1 ----
			editorPane1.setEditable(false);
			editorPane1.setContentType("text/html");
			scrollPane1.setViewportView(editorPane1);
			
			try {
				editorPane1.setPage(urlHelp);
				
			} catch (IOException e) {
				editorPane1.setText("Help file not found");
			}
		}
		contentPane.add(scrollPane1, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));
		
		//---- button1 ----
		button1.setText("Close");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeButtonActionPerformed(e);
			}
		});
		contentPane.add(button1, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));
		
		setSize(900, 650);
		setLocationRelativeTo(null);
	}
	
	/**
	 * Closes frame.
	 * @param e
	 */
	private void closeButtonActionPerformed(ActionEvent e) {
		this.dispose();
	}

	/**
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { 
        	public void run() {
        		JFrame.setDefaultLookAndFeelDecorated(false);
        		new Help();
            }
        });
    }
    
}
