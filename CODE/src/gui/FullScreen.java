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

import guiLogics.ClipboardCopyPaste;
import guiLogics.LoadSaveExec;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Full screen RSA execution window.
 * 
 * @author Petri Tuononen
 * @since 2.2.2009
 */
public class FullScreen extends JFrame {
	
	private static final long serialVersionUID = -556243637750407246L;
	ClipboardCopyPaste clipboard = new ClipboardCopyPaste();

	/**
	 * Constructor.
	 * @param textArea
	 */
	public FullScreen(JTextArea textArea) {
		initComponents();
		textArea1.setText(textArea.getText());
		textArea1.setCaretPosition(0);
	}

	//Variables declaration
	private JPanel panel1;
	private JScrollPane scrollPane1;
	private JTextArea textArea1;
	private JPanel panel2;
	private JButton button2;
	private JButton button3;
	private JComboBox comboBox1;
	private JButton button1;
	private LoadSaveExec loadSaveExec;
	private final String[] textSize = {"Font size 12 pt", "Font size 14 pt",
					"Font size 16 pt", "Font size 20 pt", "Font size 30 pt"};
	//End of variables declaration
	
	/**
	 * Initializes components.
	 */
	private void initComponents() {
		panel1 = new JPanel();
		scrollPane1 = new JScrollPane();
		textArea1 = new JTextArea();
		panel2 = new JPanel();
		button2 = new JButton();
		button3 = new JButton();
		button1 = new JButton();
		comboBox1 = new JComboBox(textSize);

		//======== container ========
		setTitle("RSA Education Cryptosystem");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {15, 849, 10, 0};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {15, 543, 10, 30, 10, 0};
		((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0, 0.0, 0.0, 1.0E-4};

		//======== panel1 ========
		{
			panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(textArea1);
			}
			textArea1.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
			clipboard.createPopupMenu(textArea1);
			panel1.add(scrollPane1);
		}
		contentPane.add(panel1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//======== panel2 ========
		{
			panel2.setLayout(new GridBagLayout());
			((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {125, 125, 305, 105, 100, 0};
			((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {25, 0};
			((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

			//---- button2 ----
			button2.setText("Save to file");
			button2.setPreferredSize(new Dimension(125, 25));
			button2.setMaximumSize(new Dimension(125, 25));
			button2.setMinimumSize(new Dimension(125, 25));
			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveExecutionButtonActionPerformed(e);
				}
			});
			panel2.add(button2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- button3 ----
			button3.setText("Load from file");
			button3.setPreferredSize(new Dimension(125, 25));
			button3.setMaximumSize(new Dimension(125, 25));
			button3.setMinimumSize(new Dimension(125, 25));
			button3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadExecutionButtonActionPerformed(e);
				}
			});
			panel2.add(button3, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- comboBox1 ----
			comboBox1.setPreferredSize(new Dimension(120, 25));
			comboBox1.setMinimumSize(new Dimension(100, 25));
			comboBox1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fontSizeComboBoxActionPerformed(e);
				}
			});
			comboBox1.setSelectedIndex(2);
			panel2.add(comboBox1, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- button1 ----
			button1.setText("Close");
			button1.setPreferredSize(new Dimension(100, 25));
			button1.setMaximumSize(new Dimension(59, 25));
			button1.setMinimumSize(new Dimension(59, 25));
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					closeButtonActionPerformed(e);
				}
			});
			panel2.add(button1, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel2, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));
		
		//Get display's maximum width and height.
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        int width = ge.getMaximumWindowBounds().width; 
        int height = ge.getMaximumWindowBounds().height;     
        setSize(width, height);
//		setSize(900, 650); //option size
		setLocationRelativeTo(null);
		toFront();
		setVisible(true);
	}
	
	/**
	 * 'Save to file' button pressed.
	 * @param e
	 */
	private void saveExecutionButtonActionPerformed(ActionEvent e) {
		loadSaveExec = new LoadSaveExec(this, textArea1);
		loadSaveExec.saveExecToFile();
	}

	/**
	 * 'Load from file' button pressed.
	 * @param e
	 */
	private void loadExecutionButtonActionPerformed(ActionEvent e) {
		loadSaveExec = new LoadSaveExec(this, textArea1);
		loadSaveExec.loadExecFromFile();
	}

	/**
	 * Font size changed.
	 * @param e
	 */
	private void fontSizeComboBoxActionPerformed(ActionEvent e) {
		if (comboBox1.getSelectedItem() == "Font size 12 pt") {
			textArea1.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		}
		else if (comboBox1.getSelectedItem() == "Font size 14 pt") {
			textArea1.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		}
		else if (comboBox1.getSelectedItem() == "Font size 16 pt") {
			textArea1.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
		}
		else if (comboBox1.getSelectedItem() == "Font size 20 pt") {
			textArea1.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
		}
		else if (comboBox1.getSelectedItem() == "Font size 30 pt") {
			textArea1.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));
		}
	}

	/**
	 * 'Close' button pressed.
	 * @param e
	 */
	private void closeButtonActionPerformed(ActionEvent e) {
		this.dispose();
	}
	
	/**
	 * Updates textarea.
	 * @param textArea
	 */
	public void updateTextArea(JTextArea textArea) {
		textArea1.setText(textArea.getText());
	}

	/**
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { 
        	public void run() {
        		JFrame.setDefaultLookAndFeelDecorated(false);
        		new FullScreen(new JTextArea());
            }
        });
    }
    
}
