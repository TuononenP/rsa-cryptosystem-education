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

import guiLogics.*;
import paddingSchemes.*;
import keypair.*;

import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Graphical User Interface for
 * the RSA Education Cryptosystem.
 * 
 * @author Petri Tuononen
 * @since 7.2.2009
 */
public class Gui extends JFrame {

	private static final long serialVersionUID = -2015036022929739032L;

	//Constructor
	public Gui() {
		initComponents();
	}

	//Variables declaration
	private JMenuBar menuBar;

	private JMenu menu;
	private JMenu menu2;

	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;

	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	private JRadioButton radioButton5;

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JButton button10;
	private JButton button11;
	private JButton button12;
	private JButton button13;

	private JPanel panel3;
	private JPanel panel6;
	private JPanel panel7;

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;

	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JTextField textField6;

	private JTextArea textArea1;
	private JTextArea textArea2;

	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;

	private JComboBox comboBox1;

	private ButtonGroup buttonGroup1;
	private ButtonGroup buttonGroup2;

	private RsaPublicKey publicKey;
	private RsaPrivateKey privateKey;
	private OpenSave openSave;
	private PaddingType1 padding1;
	private PaddingType2 padding2;
	private BlocksOf3Padding padding3;
	private ClipboardCopyPaste clipboard;
	private final String[] textSize = {"Font size 12 pt", "Font size 14 pt",
			"Font size 16 pt", "Font size 18 pt", "Font size 20 pt"};
	private GradientPanel panel1;
	private GradientPanel panel2;
	private GradientPanel panel4;
	private GradientPanel panel5;

	private Color panelColor;
	//End of variables declaration

	private void initComponents() {
		//Component initialization
		menuBar = new JMenuBar();

		menu = new JMenu();
		menu2 = new JMenu();

		menuItem1 = new JMenuItem();
		menuItem2 = new JMenuItem();
		menuItem3 = new JMenuItem();

		radioButton1 = new JRadioButton();
		radioButton2 = new JRadioButton();
		radioButton3 = new JRadioButton();
		radioButton4 = new JRadioButton();
		radioButton5 = new JRadioButton();

		button1 = new JButton();
		button2 = new JButton();
		button3 = new JButton();
		button4 = new JButton();
		button5 = new JButton();
		button6 = new JButton();
		button7 = new JButton();
		button8 = new JButton();
		button9 = new JButton();
		button10 = new JButton();
		button11 = new JButton();
		button12 = new JButton();
		button13 = new JButton();

		panel3 = new JPanel();
		panel6 = new JPanel();
		panel7 = new JPanel();

		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		label7 = new JLabel();
		label8 = new JLabel();

		textField1 = new JTextField();
		textField2 = new JTextField();
		textField3 = new JTextField();
		textField4 = new JTextField();
		textField5 = new JTextField();
		textField6 = new JTextField();

		textArea1 = new JTextArea();
		textArea2 = new JTextArea();

		scrollPane1 = new JScrollPane();
		scrollPane2 = new JScrollPane();

		comboBox1 = new JComboBox(textSize);

		buttonGroup1 = new ButtonGroup();
		buttonGroup2 = new ButtonGroup();
		
		padding1 = new PaddingType1();
		padding2 = new PaddingType2();
		padding3 = new BlocksOf3Padding();
		
		clipboard = new ClipboardCopyPaste();

//		panelColor = new Color(117, 154, 178);
		panelColor = Color.LIGHT_GRAY;

		//======== container ========
		setTitle("RSA Education Cryptosystem");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
		try { //try to load icon image
			ClassLoader cl = this.getClass().getClassLoader();
			setIconImage(new ImageIcon(cl.getResource("images/icon.jpg")).getImage());
		}catch (Exception e) { //if not run from jar packet
		}
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {10, 581, 10};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {3, 0, 10, 0, 10, 64, 10, 0, 10, 0, 10, 242, 5, 0, 0, 0};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0E-4};

		//======== menuBar ========
		{

			//======== menu ========
			{
				menu.setText("File");

				//---- menuItem3 ----
				menuItem3.setText("Quit");
				menuItem3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						quitMenuItemActionPerformed(e);
					}
				});
				menu.add(menuItem3);

				menuBar.add(menu);

				//======== menu2 ========
				{
					menu2.setText("Help");

					//---- menuItem1 ----
					menuItem1.setText("Show help");
					menuItem1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							showHelpMenuItemActionPerformed(e);
						}
					});
					//shortcut for help F11
					menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
					menu2.add(menuItem1);

					//---- menuItem2 ----
					menuItem2.setText("About");
					menuItem2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							aboutMenuItemActionPerformed(e);
						}
					});
					menu2.add(menuItem2);
				}
				menuBar.add(menu2);
			}
		}
		setJMenuBar(menuBar);

		//======== panel1 ========
		{ 
			panel1 = new GradientPanel(panelColor, new GridBagLayout());
			((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {25, 162, 32, 105, 110, 105, 105, 20, 0};
			((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {12, 0, 0, 0, 0, 0, 5, 0};
			((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

			panel1.setBorder(new TitledBorder(new LineBorder(Color.gray, 1, true), "Key creation"));

			//---- label1 ----
			label1.setText("p");
			label1.setHorizontalAlignment(SwingConstants.CENTER);
			panel1.add(label1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
					new Insets(0, 0, 5, 5), 0, 0));
			panel1.add(textField1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			//---- label4 ----
			label4.setText("n");
			label4.setHorizontalAlignment(SwingConstants.CENTER);
			panel1.add(label4, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			//---- textField4 ----
			textField4.setEditable(false);
			panel1.add(textField4, new GridBagConstraints(3, 1, 4, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			//---- label2 ----
			label2.setText("q");
			label2.setHorizontalAlignment(SwingConstants.CENTER);
			panel1.add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
					new Insets(0, 0, 5, 5), 0, 0));
			panel1.add(textField2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			//---- label5 ----
			label5.setText("d");
			label5.setHorizontalAlignment(SwingConstants.CENTER);
			panel1.add(label5, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			//---- textField5 ----
			textField5.setEditable(false);
			panel1.add(textField5, new GridBagConstraints(3, 2, 4, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			//---- label3 ----
			label3.setText("e");
			label3.setHorizontalAlignment(SwingConstants.CENTER);
			panel1.add(label3, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
					new Insets(0, 0, 5, 5), 0, 0));
			panel1.add(textField3, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			//---- label6 ----
			label6.setText("Save");
			panel1.add(label6, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
					new Insets(0, 0, 5, 5), 0, 0));

			//---- label7 ----
			label7.setText("Load");
			panel1.add(label7, new GridBagConstraints(6, 3, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
					new Insets(0, 0, 5, 5), 0, 0));

			//---- button11 ----
			button11.setText("Clear keys");
			button11.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clearKeysButtonActionPerformed(e);
				}
			});
			panel1.add(button11, new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			//---- label8 ----
			label8.setText("Prime bitsize");
			label8.setVisible(false);
			panel1.add(label8, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
					new Insets(0, 0, 5, 5), 0, 0));

			//---- button2 ----
			button2.setText("Public key");
			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					savePublicKeyButtonActionPerformed(e);
				}
			});
			panel1.add(button2, new GridBagConstraints(5, 4, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			//---- button9 ----
			button9.setText("Public key");
			button9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadPublicKeyButtonActionPerformed(e);
				}
			});
			panel1.add(button9, new GridBagConstraints(6, 4, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			//---- button1 ----
			button1.setText("Create keys");
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createKeysButtonActionPerformed(e);
				}
			});
			panel1.add(button1, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel1.add(textField6, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			textField6.setVisible(false);

			//---- button7 ----
			button7.setText("Private key");
			button7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					savePrivateKeyButtonActionPerformed(e);
				}
			});
			panel1.add(button7, new GridBagConstraints(5, 5, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			//---- button10 ----
			button10.setText("Private key");
			button10.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadPrivateKeyButtonActionPerformed(e);
				}
			});
			panel1.add(button10, new GridBagConstraints(6, 5, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
				new Insets(0, 0, 0, 0), 0, 0));

		//======== panel2 ========
		{
			panel2 = new GradientPanel(panelColor, new GridBagLayout());
			((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {10, 435, 5, 0};
			((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {71, 5, 0};
			((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
			((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

			panel2.setBorder(new TitledBorder(new LineBorder(Color.gray, 1, true), "Message to encrypt/decrypt"));

			//======== scrollPane2 ========
			{

				//---- textArea2 ----
				textArea2.setLineWrap(true);
				textArea2.setWrapStyleWord(true);
				clipboard.createPopupMenu(textArea2);
				scrollPane2.setViewportView(textArea2);
			}
			panel2.add(scrollPane2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
		}
		contentPane.add(panel2, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		//======== panel3 ========
		{
			panel3.setLayout(new GridBagLayout());
			((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {155, 105, 20, 105, 150, 0};
			((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {28, 0};
			((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0, 1.0, 1.0E-4};
			((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

			//---- button3 ----
			button3.setText("Encrypt");
			button3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					encryptButtonActionPerformed(e);
				}
			});
			panel3.add(button3, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

			//---- button4 ----
			button4.setText("Decrypt");
			button4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					decryptButtonActionPerformed(e);
				}
			});
			panel3.add(button4, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));
		}
		contentPane.add(panel3, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		//======== panel4 ========
		{
			panel4 = new GradientPanel(panelColor, new GridBagLayout());
			panel4.setBorder(new TitledBorder(new LineBorder(Color.gray, 1, true), "Padding scheme"));
			((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {80, 81, 80, 0};
			((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0};
			((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

			//---- radioButton3 ----
			radioButton3.setText("One letter");
			radioButton3.setOpaque(false);
			radioButton3.setSelected(true);
			panel4.add(radioButton3, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));
			
			//---- radioButton4 ----
			radioButton4.setText("Two letters");
			radioButton4.setOpaque(false);
			panel4.add(radioButton4, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));
			
			//---- radioButton5 ----
			radioButton5.setText("Three letters");
			radioButton5.setOpaque(false);
			panel4.add(radioButton5, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel4, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		//======== panel5 ========
		{
			panel5 = new GradientPanel(panelColor, new GridBagLayout());
			((GridBagLayout)panel5.getLayout()).columnWidths = new int[] {10, 0, 5, 0};
			((GridBagLayout)panel5.getLayout()).rowHeights = new int[] {210, 0, 0};
			((GridBagLayout)panel5.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
			((GridBagLayout)panel5.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

			panel5.setBorder(new TitledBorder(new LineBorder(Color.gray, 1, true), "RSA execution"));

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(textArea1);
			}

			clipboard.createPopupMenu(textArea1);

			panel5.add(scrollPane1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
		}
		contentPane.add(panel5, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));


		//======== panel6 ========
		{
			panel6.setLayout(new GridBagLayout());
			((GridBagLayout)panel6.getLayout()).columnWidths = new int[] {0, 0, 15, 65, 55, 15, 119, 90, 0};
			((GridBagLayout)panel6.getLayout()).rowHeights = new int[] {0, 0};
			((GridBagLayout)panel6.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel6.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

			//---- button5 ----
			button5.setText("Save to file");
			button5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveExecutionButtonActionPerformed(e);
				}
			});
			panel6.add(button5, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

			//---- button6 ----
			button6.setText("Load from file");
			button6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadExecutionButtonActionPerformed(e);
				}
			});
			panel6.add(button6, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

			//---- button12 ----
			button12.setText("Clear");
			button12.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ClearExecButtonActionPerformed(e);
				}
			});
			panel6.add(button12, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

			//---- button13 ----
			button13.setText("Copy to clipboard");
			button13.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					copyToClipboardButtonActionPerformed(e);
				}
			});
			panel6.add(button13, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

			//---- comboBox1 ----
			comboBox1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fontSizeComboBoxActionPerformed(e);
				}
			});
			panel6.add(comboBox1, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

			//---- button8 ----
			button8.setText("Full screen");
			button8.setPreferredSize(new Dimension(100, 23));
			button8.setMaximumSize(new Dimension(100, 23));
			button8.setMinimumSize(new Dimension(100, 23));
			button8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fullScreenButtonActionPerformed(e);
				}
			});
			panel6.add(button8, new GridBagConstraints(7, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel6, new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		//======== panel7 ========
		{
			panel7.setLayout(new GridBagLayout());
			((GridBagLayout)panel7.getLayout()).columnWidths = new int[] {0, 10, 0, 10, 0};
			((GridBagLayout)panel7.getLayout()).rowHeights = new int[] {0, 0};
			((GridBagLayout)panel7.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel7.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

			//---- radioButton1 ----
			radioButton1.setText("Teach mode");
			radioButton1.setSelected(true);
			radioButton1.setActionCommand("Teach mode");
			radioButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					teachModeRadioButtonActionPerformed(e);
				}
			});
			panel7.add(radioButton1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

			//---- radioButton2 ----
			radioButton2.setText("Secure mode");
			radioButton2.setActionCommand("Secure mode");
			radioButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					secureModeRadioButtonActionPerformed(e);
				}
			});
			panel7.add(radioButton2, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));
		}
		contentPane.add(panel7, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		setSize(750, 775);
		setLocationRelativeTo(null);
		toFront();
		setVisible(true);

		//---- buttonGroup1 ----
		buttonGroup1.add(radioButton1);
		buttonGroup1.add(radioButton2);
		
		//---- buttonGroup2 ----
		buttonGroup2.add(radioButton3);
		buttonGroup2.add(radioButton4);
		buttonGroup2.add(radioButton5);
		//End of component initialization
	}

	//Event handlers

	/**
	 * Teach mode radiobutton pressed.
	 */
	private void teachModeRadioButtonActionPerformed(ActionEvent e) {
		teachModeButtonPressed();
	}

	/**
	 * Secure mode radiobutton pressed.
	 * @param e
	 */
	private void secureModeRadioButtonActionPerformed(ActionEvent e) {
		secureModeButtonPressed();
	}

	/**
	 * 'Quit' File menu item pressed.
	 * @param e
	 */
	private void quitMenuItemActionPerformed(ActionEvent e) {
		dispose();
	}

	/**
	 * 'Show help' menu item pressed.
	 * @param e
	 */
	private void showHelpMenuItemActionPerformed(ActionEvent e) {
		new Help().toFront();
	}

	/**
	 * 'About' menu item pressed.
	 * @param e
	 */
	private void aboutMenuItemActionPerformed(ActionEvent e) {
		showAbout();
	}

	/**
	 * 'Save public key' button pressed.
	 * @param e
	 */
	private void savePublicKeyButtonActionPerformed(ActionEvent e) {
		savePublicKey();
	}

	/**
	 * 'Load public key' button pressed.
	 * @param e
	 */
	private void loadPublicKeyButtonActionPerformed(ActionEvent e) {
		loadPublicKey();
	}

	/**
	 * 'Save private key' button pressed.
	 * @param e
	 */
	private void savePrivateKeyButtonActionPerformed(ActionEvent e) {
		savePrivateKey();
	}

	/**
	 * 'Load private key' button pressed.
	 * @param e
	 */
	private void loadPrivateKeyButtonActionPerformed(ActionEvent e) {
		loadPrivateKey();
	}

	/**
	 * 'Encrypt' button pressed.
	 * @param e
	 */
	private void encryptButtonActionPerformed(ActionEvent e) {
		encrypt();
	}

	/**
	 * 'Decrypt' button pressed.
	 * @param e
	 */
	private void decryptButtonActionPerformed(ActionEvent e) {
		decrypt();
	}

	/**
	 * 'Save to file' button pressed.
	 * @param e
	 */
	private void saveExecutionButtonActionPerformed(ActionEvent e) {
		saveExecution();
	}

	/**
	 * 'Load from file' button pressed.
	 * @param e
	 */
	private void loadExecutionButtonActionPerformed(ActionEvent e) {
		loadExecution();
	}

	/**
	 * Font selected from font size combo box.
	 * @param e
	 */
	private void fontSizeComboBoxActionPerformed(ActionEvent e) {
		fontSizeChange();
	}

	/**
	 * 'Full screen' button pressed.
	 * @param e
	 */
	private void fullScreenButtonActionPerformed(ActionEvent e) {
		showExecFullScreen();
	}

	/**
	 * 'Create keys' button pressed.
	 * @param e
	 */
	private void createKeysButtonActionPerformed(ActionEvent e) {
		createKeys();
	}

	/**
	 * 'Clear keys' button pressed.
	 * @param e
	 */
	private void clearKeysButtonActionPerformed(ActionEvent e) {
		//Clear p, q, e, n, d textfields
		clearKeyTextFields();
		//delete keys
		publicKey = null;
		privateKey = null;
	}

	/**
	 * 'Clear' button pressed.
	 * @param e
	 */
	private void ClearExecButtonActionPerformed(ActionEvent e) {
		//Clear execution textarea
		textArea1.setText("");
	}

	/**
	 * 'Copy to clipboard' button pressed.
	 * @param e
	 */
	private void copyToClipboardButtonActionPerformed(ActionEvent e) {
		new ClipboardCopyPaste().copy(textArea1.getText());
	}
	//End of event handlers.

	//Methods used in event handlers.

	/**
	 * Clears textfields containing key info.
	 */
	private void clearKeyTextFields() {
		//Clear p, q, e, n, d textfields
		textField1.setText("");
		textField2.setText("");
		textField3.setText("");
		textField4.setText("");
		textField5.setText("");
	}

	/**
	 * Generates keys.
	 */
	private void createKeys() {
		//teach mode
		if (buttonGroup1.getSelection().getActionCommand().equals("Teach mode")) {
			try {
				//get user input
				BigInteger p = new BigInteger(textField1.getText());
				BigInteger q = new BigInteger(textField2.getText());
				BigInteger e = new BigInteger(textField3.getText());

				GenerateUserKeys genKeys = new GenerateUserKeys();

				//test that p, q and e are suitable
				if (genKeys.testInputEligibility(p, q, e)) { //input ok
					//generate keys
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					genKeys.createKeys(p, q, e);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					//store keys
					publicKey = genKeys.getPublicKey();
					privateKey = genKeys.getPrivateKey();

					//write to textfields
					textField4.setText(publicKey.getN().toString());
					textField5.setText(privateKey.getPrivateExponent().toString());
				}
				else { //bad input
					genKeys.showInputError(this, p, q, e);
				}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this, "Key creation error.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		//secure mode
		else if (buttonGroup1.getSelection().getActionCommand().equals("Secure mode")) {
			//bitsize field is empty
			if (textField6.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bitsize field is empty.", "Bitsize error", JOptionPane.ERROR_MESSAGE);
			}else {
				int value = 0;
				if (Integer.parseInt(textField6.getText()) > 2048) {
					//value=0 OK, value=2 CANCEL
					value = JOptionPane.showConfirmDialog(this, "Are you sure you want to create primes larger than 2048 bits? It will take some time.", "Large bitsize warning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				}
				if (value==0) {
					if (Integer.parseInt(textField6.getText()) <= 5) { //bit size under 5 bits.
						JOptionPane.showMessageDialog(this, "Prime bitsize must be larger than 5.", "Bitsize error", JOptionPane.ERROR_MESSAGE);
					}else { //bit size is ok.
						setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						GenerateKeys genKeys = new GenerateKeys(Integer.parseInt(textField6.getText()));
						publicKey = genKeys.getPublicKey();
						privateKey = genKeys.getPrivateKey();
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				}
			}
		}
	}

	/**
	 * Saves a public key to a file.
	 */
	private void savePublicKey() {
		if (publicKey!=null) {
			openSave = new OpenSave(this);
			openSave.savePublicKey(publicKey);
		}else {
			JOptionPane.showMessageDialog(this, "Public key is not created.", "Public key save error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Loads a public key from a file.
	 */
	private void loadPublicKey() {
		openSave = new OpenSave(this);
		RsaPublicKey pubKey = null;
		try {
			pubKey = openSave.loadPublicKey();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Public key couldn't be loaded.", "Public key load error", JOptionPane.ERROR_MESSAGE);
		}
		if (pubKey != null) {
			clearKeyTextFields();
			publicKey = pubKey;
			privateKey = null;
			if (buttonGroup1.getSelection().getActionCommand().equals("Teach mode")) {
				//public key fields
				textField3.setText(publicKey.getE().toString());
				textField4.setText(publicKey.getN().toString());
				
				//private key fields
				textField1.setText("");
				textField2.setText("");
				textField5.setText("");
			}
		}
	}

	/**
	 * Saves a private key to a file.
	 */
	private void savePrivateKey() {
		if (privateKey!=null) {
			openSave = new OpenSave(this);
			openSave.savePrivateKey(privateKey);
		}else {
			JOptionPane.showMessageDialog(this, "Private key is not created.", "Private key save error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Loads a private key from a file.
	 */
	private void loadPrivateKey() {
		openSave = new OpenSave(this);
		RsaPrivateKey privKey = null;
		try {
			//create private & public keys and write values to textfields
			privKey = openSave.loadPrivateKey();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Private key couldn't be loaded.", "Private key load error", JOptionPane.ERROR_MESSAGE);
		}
		if (privKey != null) {
			privateKey = privKey;
			publicKey = new RsaPublicKey(privateKey.getN(), privateKey.getE());
			if (buttonGroup1.getSelection().getActionCommand().equals("Teach mode")) {
				textField1.setText(privateKey.getPrimeP().toString());
				textField2.setText(privateKey.getPrimeQ().toString());
				textField3.setText(privateKey.getE().toString());
				textField4.setText(privateKey.getN().toString());
				textField5.setText(privateKey.getPrivateExponent().toString());
			}
		}
	}

	/**
	 * Saves execution textarea contents to a file.
	 */
	private void saveExecution() {
		new LoadSaveExec(this, textArea1).saveExecToFile();
	}

	/**
	 * Loads content to the execution textarea from a file.
	 */
	private void loadExecution() {
		new LoadSaveExec(this, textArea1).loadExecFromFile();
	}

	/**
	 * Show execution content in a full screen frame. 
	 */
	private void showExecFullScreen() {
		new FullScreen(textArea1);
	}

	/**
	 * Encrypts the text written in 'Message to encrypt/decrypt' textarea.
	 */
	private void encrypt() {
		if (publicKey == null) {
			JOptionPane.showMessageDialog(this, "Public key not created", "Public key error", JOptionPane.ERROR_MESSAGE);
		}
		//teach mode
		if (buttonGroup1.getSelection().getActionCommand().equals("Teach mode")) {
			// if padding type1 checkbox is selected
			if(radioButton3.isSelected()){
				// get plaintext from textarea
				String plaintext = textArea2.getText().toUpperCase();
				// if something is written into the message textarea and public key is generated.
				if (!plaintext.isEmpty() && publicKey != null) {
					// check that n > 25. This is requirement for padding type 1
					if (publicKey.getN().compareTo(new BigInteger("25"))>0){
						// check that input contains only allowed letters and catch all errors
						try{
							textArea1.setText(padding1.getEnCrypted(plaintext, publicKey.getPublicExponent(), publicKey.getModulus()));
							textArea1.setCaretPosition(0);
						}catch (Exception e){
							JOptionPane.showMessageDialog(this, "Only letters A - Z are allowed.", "Input error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(this, "One letter padding scheme requires n > 25", "Input error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			//if padding type2 checkbox is selected
			else if(radioButton4.isSelected()){
				// get plaintext from textarea
				String plaintext = textArea2.getText().toUpperCase();
				// if something is written into the message textarea and public key is generated.
				if (!plaintext.isEmpty() && publicKey != null) {
					// check that n > 2525. This is requirement for padding type 2
					if (publicKey.getN().compareTo(new BigInteger("2525"))>0){
						// check that input contains only allowed letters and catch all errors
						try{
							textArea1.setText(padding2.getEnCrypted(plaintext, publicKey.getPublicExponent(), publicKey.getModulus()));
							textArea1.setCaretPosition(0);
						}catch(Exception e){
							JOptionPane.showMessageDialog(this, "Only letters A - Z are allowed.", "Input error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(this, "Two letters padding scheme requires n > 2525", "Input error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			//if blocks of three letters padding scheme checkbox is selected
			else if (radioButton5.isSelected()) {
				//get plaintext from textarea
				String plaintext = textArea2.getText().toUpperCase();
				//if something is written into the message textarea and public key is generated.
				if (!plaintext.isEmpty() && publicKey != null) {
					// check that n > 17575. This is requirement for padding type 3
					if (publicKey.getN().compareTo(new BigInteger("17575"))>0){
						// check that input contains only allowed letters and catch all errors
						try{
							textArea1.setText(padding3.getEncodeAndEncryptBlocksOfThree(plaintext, publicKey.getPublicExponent(), publicKey.getModulus()));
							textArea1.setCaretPosition(0);
						}catch (Exception e){
							JOptionPane.showMessageDialog(this, "Only letters A - Z are allowed.", "Input error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(this, "Three letters padding scheme letters requires n > 17575", "Input error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		//secure mode
		else if (buttonGroup1.getSelection().getActionCommand().equals("Secure mode")) {
			// if padding type1 checkbox is selected
			if(radioButton3.isSelected()){
				// get plaintext from textarea
				String plaintext = textArea2.getText().toUpperCase();
				// if something is written into the message textarea and public key is generated.
				if (!plaintext.isEmpty() && publicKey != null) {
					// check that n > 25. This is requirement for padding type 1
					if (publicKey.getN().compareTo(new BigInteger("25"))>0){
						// check that input contains only allowed letters and catch all errors
						try{ 
							textArea1.setText(padding1.getEnCryptedSecure(plaintext, publicKey.getPublicExponent(), publicKey.getModulus()));
							textArea1.setCaretPosition(0);
						}catch (Exception e){
							JOptionPane.showMessageDialog(this, "Only letters A - Z are allowed.", "Input error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(this, "One letter padding scheme requires n > 25", "Input error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			//if padding type2 checkbox is selected
			else if(radioButton4.isSelected()){
				// get plaintext from textarea
				String plaintext = textArea2.getText().toUpperCase();
				// if something is written into the message textarea and public key is generated.
				if (!plaintext.isEmpty() && publicKey != null) {
					// check that n > 2525. This is requirement for padding type 2
					if (publicKey.getN().compareTo(new BigInteger("2525"))>0){
						// check that input contains only allowed letters and catch all errors
						try{
						textArea1.setText(padding2.getEnCryptedSecure(plaintext, publicKey.getPublicExponent(), publicKey.getModulus()));
						textArea1.setCaretPosition(0);
						}catch (Exception e){
							JOptionPane.showMessageDialog(this, "Only letters A - Z are allowed.", "Input error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(this, "Two letters padding scheme requires n > 2525", "Input error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			//if blocks of three letters padding scheme checkbox is selected
			else if (radioButton5.isSelected()) {
				//get plaintext from textarea
				String plaintext = textArea2.getText().toUpperCase();
				//if something is written into the message textarea and public key is generated.
				if (!plaintext.isEmpty() && publicKey != null) {
					// check that n > 17575. This is requirement for padding type 3
					if (publicKey.getN().compareTo(new BigInteger("17575"))>0){
						// check that input contains only allowed letters and catch all errors
						try{
						textArea1.setText(padding3.getEncodeAndEncryptSecure(plaintext, publicKey.getPublicExponent(), publicKey.getModulus()));
						textArea1.setCaretPosition(0);
						}catch (Exception e){
							JOptionPane.showMessageDialog(this, "Only letters A - Z are allowed.", "Input error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(this, "Three letters padding scheme letters requires n > 17575", "Input error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

	/**
	 * Decrypts the text written in 'Message to encrypt/decrypt' textarea.
	 */
	private void decrypt() {
		if (privateKey == null) {
			JOptionPane.showMessageDialog(this, "Private key not created", "Private key error", JOptionPane.ERROR_MESSAGE);
		}
		//teach mode
		if (buttonGroup1.getSelection().getActionCommand().equals("Teach mode")) {
			// if padding scheme1 checkbox is selected
			if (radioButton3.isSelected()){
				// get encrypted text from textarea
				String encrypted = textArea2.getText();
				//if something is written into the message textarea and private key is generated.
				if (!encrypted.isEmpty() && privateKey != null) {
					// check that n > 25. This is requirement for padding type 1
					if (privateKey.getN().compareTo(new BigInteger("25"))>0){
						// catch exceptions (exception comes if textarea contains illeagal letters
						try{
							textArea1.setText(padding1.getDeCrypted(encrypted, privateKey.getPrivateExponent(), privateKey.getModulus()));
							textArea1.setCaretPosition(0);
						}catch(Exception e){
							JOptionPane.showMessageDialog(this, "Not a genuine cryptotext.", "Input error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(this, "One letter padding scheme requires n > 25", "Input error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			// if padding scheme2 checkbox is selected
			else if (radioButton4.isSelected()){
				// get encrypted text from textarea
				String encrypted = textArea2.getText();
				//if something is written into the message textarea and private key is generated.
				if (!encrypted.isEmpty() && privateKey != null) {
					// check that n > 2525. This is requirement for padding type 2
					if (privateKey.getN().compareTo(new BigInteger("2525"))>0){
						// catch exceptions (exception comes if textarea contains illeagal letters
						try{
							textArea1.setText(padding2.getDeCrypted(encrypted, privateKey.getPrivateExponent(), privateKey.getModulus()));
							textArea1.setCaretPosition(0);
						}catch(Exception e){
							JOptionPane.showMessageDialog(this, "Not a genuine cryptotext.", "Input error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(this, "Two letters padding scheme requires n > 2525", "Input error", JOptionPane.ERROR_MESSAGE);
					}
				} 
			}
			//if blocks of three letters padding scheme checkbox is selected
			else if (radioButton5.isSelected()) {
				//get encrypted text from textarea
				String encrypted = textArea2.getText();
				//if something is written into the message textarea and private key is generated.
				if (!encrypted.isEmpty() && privateKey != null) {
					// check that n > 17575. This is requirement for padding type 3
					if (privateKey.getN().compareTo(new BigInteger("17575"))>0){
						// catch exceptions (exception comes if textarea contains illeagal letters
						try{
							textArea1.setText(padding3.getDecryptAndDecodeBlocksOfThree(encrypted, privateKey.getPrivateExponent(), privateKey.getModulus()));
							textArea1.setCaretPosition(0);
						}catch(Exception e){
							JOptionPane.showMessageDialog(this, "Not a genuine cryptotext.", "Input error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(this, "Three letters padding scheme requires n > 17575", "Input error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}

		//secure mode
		else if (buttonGroup1.getSelection().getActionCommand().equals("Secure mode")) {
			// if padding scheme1 checkbox is selected
			if (radioButton3.isSelected()){
				// get encrypted text from textarea
				String encrypted = textArea2.getText();
				//if something is written into the message textarea and private key is generated.
				if (!encrypted.isEmpty() && privateKey != null) {
					// check that n > 25. This is requirement for padding type 1
					if (privateKey.getN().compareTo(new BigInteger("25"))>0){
						// catch exceptions (exception comes if textarea contains illeagal letters
						try{
							textArea1.setText(padding1.getDeCryptedSecure(encrypted, privateKey.getPrivateExponent(), privateKey.getModulus()));
							textArea1.setCaretPosition(0);
						}catch(Exception e){
							JOptionPane.showMessageDialog(this, "Not a genuine cryptotext.", "Input error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(this, "One letter padding scheme requires n > 25", "Input error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			// if padding scheme2 checkbox is selected
			else if (radioButton4.isSelected()){
				// get encrypted text from textarea
				String encrypted = textArea2.getText();
				//if something is written into the message textarea and private key is generated.
				if (!encrypted.isEmpty() && privateKey != null) {
					// check that n > 2525. This is requirement for padding type 2
					if (privateKey.getN().compareTo(new BigInteger("2525"))>0){
						// catch exceptions (exception comes if textarea contains illeagal letters
						try{
							textArea1.setText(padding2.getDeCryptedSecure(encrypted, privateKey.getPrivateExponent(), privateKey.getModulus()));
							textArea1.setCaretPosition(0);
						}catch(Exception e){
							JOptionPane.showMessageDialog(this, "Not a genuine cryptotext.", "Input error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(this, "Two letters padding scheme requires n > 2525", "Input error", JOptionPane.ERROR_MESSAGE);
					}
				} 
			}
			//if blocks of three letters padding scheme checkbox is selected
			else if (radioButton5.isSelected()) {
				//get encrypted text from textarea
				String encrypted = textArea2.getText();
				//if something is written into the message textarea and private key is generated.
				if (!encrypted.isEmpty() && privateKey != null) {
					// check that n > 17575. This is requirement for padding type 3
					if (privateKey.getN().compareTo(new BigInteger("17575"))>0){
						// catch exceptions (exception comes if textarea contains illeagal letters
						try{
							textArea1.setText(padding3.getDecryptAndDecodeSecure(encrypted, privateKey.getPrivateExponent(), privateKey.getModulus()));
							textArea1.setCaretPosition(0);
						}catch(Exception e){
							JOptionPane.showMessageDialog(this, "Not a genuine cryptotext.", "Input error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(this, "Three letters padding scheme requires n > 17575", "Input error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}


	/**
	 * Changes execution textarea's font size.
	 */
	private void fontSizeChange() {
		if (comboBox1.getSelectedItem() == "Font size 12 pt") {
			textArea1.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		}
		else if (comboBox1.getSelectedItem() == "Font size 14 pt") {
			textArea1.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		}
		else if (comboBox1.getSelectedItem() == "Font size 16 pt") {
			textArea1.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
		}
		else if (comboBox1.getSelectedItem() == "Font size 18 pt") {
			textArea1.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		}
		else if (comboBox1.getSelectedItem() == "Font size 20 pt") {
			textArea1.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
		}
	}

	/**
	 * Secure mode button action.
	 * Bit size textfield is made visible and
	 * some textfields used by teach mode
	 * are made invisible.
	 */
	private void secureModeButtonPressed() {
		textArea1.setText("");
		textField6.setVisible(true);
		label8.setVisible(true);
		textField1.setVisible(false);
		textField2.setVisible(false);
		textField3.setVisible(false);
		label1.setVisible(false);
		label2.setVisible(false);
		label3.setVisible(false);
		label4.setVisible(false);
		label5.setVisible(false);
		textField4.setVisible(false);
		textField5.setVisible(false);
	}

	/**
	 * Teach mode button action.
	 * Bit size textfield is made invisible
	 * and key textfields visible.
	 */
	private void teachModeButtonPressed() {
		textArea1.setText("");
		textField6.setVisible(false);
		label8.setVisible(false);
		textField1.setVisible(true);
		textField2.setVisible(true);
		textField3.setVisible(true);
		label1.setVisible(true);
		label2.setVisible(true);
		label3.setVisible(true);
		label4.setVisible(true);
		label5.setVisible(true);
		textField4.setVisible(true);
		textField5.setVisible(true);
	}
	
	/**
	 * Shows about screen.
	 */
	private void showAbout() {
		JOptionPane.showMessageDialog(this,
				"Authors: Petri Tuononen, Jukka Tuominen, Jani Kirsi.\n" +
				"Date: 27.02.2009\n" +
				"Version: 1.0\n" +
				"Info: This software is a part of OOP course work at Turku University of Applied Sciences.\n" +
				"GPL lisence: This software can be used, modified and redistibuted freely.\n" +
				"No warranties of any kind.\n" +
				"Feedback: petri.tuononen@students.turkuamk.fi", "About",
				JOptionPane.PLAIN_MESSAGE);
	}

	//End of methods used in event handlers.

	/**
	 * Executes GUI in a thread.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(false);
				new Gui();
			}
		});
	}

}
