package gui;

import gui_logics.ClipboardCopy;
import gui_logics.Load_Save_Exec;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import keypair.*;

/*
 * Sat Feb 07 23:09:55 EET 2009
 */

/**
 * @author Petri Tuononen
 */
public class Gui extends JFrame {
	
	//Constructor
	public Gui() {
		initComponents();
	}

	//Variables declaration  //GEN-BEGIN:variables
	private JMenuBar menuBar;
	
	private JMenu menu;
	private JMenu menu1;
	private JMenu menu2;
	private JMenu menu3;
	private JMenu menu4;
	private JMenu menu5;
	private JMenu menu6;
	private JMenu menu7;
	
	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private JMenuItem menuItem4;
	private JMenuItem menuItem5;
	private JMenuItem menuItem6;
	private JMenuItem menuItem7;
	private JMenuItem menuItem8;
	private JMenuItem menuItem9;
	private JMenuItem menuItem10;
	
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	
	private JCheckBox checkBox1;
	private JCheckBox checkBox2;
	private JCheckBox checkBox3;
	private JCheckBox checkBox4;
	private JCheckBox checkBox5;
	private JCheckBox checkBox6;
	
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

//	private JPanel panel1;
//	private JPanel panel2;
	private JPanel panel3;
//	private JPanel panel4;
//	private JPanel panel5;
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
	private JLabel label9;
	
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

	private RsaPublicKey publicKey;
	private RsaPrivateKey privateKey;
	private Open_Save openSave;
	private Load_Save_Exec loadSaveExec;
	ClipboardCopy clipboard;
	private final String[] textSize = {"Font size 12 pt", "Font size 14 pt",
					"Font size 16 pt", "Font size 18 pt", "Font size 20 pt"};
	GradientPanel panel1;
	GradientPanel panel2;
	GradientPanel panel4;
	GradientPanel panel5;
	
	Color panelColor;
	//End of variables declaration  //GEN-END:variables

	private void initComponents() {
		//Component initialization  //GEN-BEGIN:initComponents
		menuBar = new JMenuBar();
		
		menu = new JMenu();
		menu1 = new JMenu();
		menu2 = new JMenu();
		menu3 = new JMenu();
		menu4 = new JMenu();
		menu5 = new JMenu();
		menu6 = new JMenu();
		menu7 = new JMenu();
		
		menuItem1 = new JMenuItem();
		menuItem2 = new JMenuItem();
		menuItem3 = new JMenuItem();
		menuItem4 = new JMenuItem();
		menuItem5 = new JMenuItem();
		menuItem6 = new JMenuItem();
		menuItem7 = new JMenuItem();
		menuItem8 = new JMenuItem();
		menuItem9 = new JMenuItem();
		menuItem10 = new JMenuItem();
		
		radioButton1 = new JRadioButton();
		radioButton2 = new JRadioButton();
		radioButton3 = new JRadioButton();
		radioButton4 = new JRadioButton();
		
		checkBox1 = new JCheckBox();
		checkBox2 = new JCheckBox();
		checkBox3 = new JCheckBox();
		checkBox4 = new JCheckBox();
		checkBox5 = new JCheckBox();
		checkBox6 = new JCheckBox();
		
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
		
//		panel1 = new JPanel();
//		panel2 = new JPanel();
		panel3 = new JPanel();
//		panel4 = new JPanel();
//		panel5 = new JPanel();
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
		label9 = new JLabel();
		
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
		
//		panelColor = new Color(117, 154, 178);
		panelColor = Color.LIGHT_GRAY;

		//======== this ========
		setTitle("RSA Education Cryptosystem");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
		setIconImage(new ImageIcon("C:\\Tiedostovarasto\\AMK\\Olio-ohjelmoinnin projektity\u00f6 - RSA\\Logo\\Ikoni3.jpg").getImage());
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

				//======== menu1 ========
				{
					menu1.setText("Mode");

					//---- radioButton3 ----
					radioButton3.setText("Teach");
					radioButton3.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent e) {
							teachRadioItemButtonStateChanged(e);
						}
					});
					menu1.add(radioButton3);

					//---- radioButton4 ----
					radioButton4.setText("Secure");
					radioButton4.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent e) {
							secureRadioItemButtonStateChanged(e);
						}
					});
					menu1.add(radioButton4);
				}
				menu.add(menu1);
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
				
				//======== menu3 ========
				{
					menu3.setText("Key creation");

					//---- menuItem3 ----
					menuItem3.setText("Create keys");
					menuItem3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							createKeysMenuItemActionPerformed(e);
						}
					});
					menu3.add(menuItem3);

					//======== menu6 ========
					{
						menu6.setText("Save keys");

						//---- menuItem4 ----
						menuItem4.setText("Public key");
						menuItem4.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								savePublicKeyMenuItemActionPerformed(e);
							}
						});
						menu6.add(menuItem4);

						//---- menuItem5 ----
						menuItem5.setText("Private key");
						menuItem5.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								savePrivateKeyMenuItemActionPerformed(e);
							}
						});
						menu6.add(menuItem5);
					}
					menu3.add(menu6);

					//======== menu7 ========
					{
						menu7.setText("Load keys");

						//---- menuItem6 ----
						menuItem6.setText("Public key");
						menuItem6.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								loadPublicKeyMenuItemActionPerformed(e);
							}
						});
						menu7.add(menuItem6);

						//---- menuItem7 ----
						menuItem7.setText("Private key");
						menuItem7.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								loadPrivateKeyMenuItemActionPerformed(e);
							}
						});
						menu7.add(menuItem7);
					}
					menu3.add(menu7);
				}
				menu.add(menu3);

				//======== menu4 ========
				{
					menu4.setText("Paddings schemes");

					//---- checkBox4 ----
					checkBox4.setText("One letter");
					checkBox4.setPreferredSize(new Dimension(95, 23));
					checkBox4.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent e) {
							padding1MenuItemStateChanged(e);
						}
					});
					menu4.add(checkBox4);

					//---- checkBox5 ----
					checkBox5.setText("Two letters");
					checkBox5.setPreferredSize(new Dimension(95, 23));
					checkBox5.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent e) {
							padding2MenuItemStateChange(e);
						}
					});
					menu4.add(checkBox5);

					//---- checkBox6 ----
					checkBox6.setText("Three letters");
					checkBox6.setPreferredSize(new Dimension(95, 23));
					checkBox6.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent e) {
							padding3MenuItemStateChange(e);
						}
					});
					menu4.add(checkBox6);
				}
				menu.add(menu4);

				//======== menu5 ========
				{
					menu5.setText("Execution");

					//---- menuItem8 ----
					menuItem8.setText("Save");
					menuItem8.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							saveExecutionMenuItemActionPerformed(e);
						}
					});
					menu5.add(menuItem8);

					//---- menuItem9 ----
					menuItem9.setText("Load");
					menuItem9.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							loadExecutionMenuItemActionPerformed(e);
						}
					});
					menu5.add(menuItem9);

					//---- menuItem10 ----
					menuItem10.setText("Full screen");
					menuItem10.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							fullScreenMenuItemActionPerformed(e);
						}
					});
					menu5.add(menuItem10);
				}
				menu.add(menu5);
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
			
//			Color panelColor = new Color(90, 147, 195);
//			panel1.setBorder(new TitledBorder(new LineBorder(panelColor, 1, true), "Key creation"));
//			panel1.setLayout(new GridBagLayout());
//			((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {25, 162, 32, 105, 110, 105, 105, 5, 0};
//			((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {12, 0, 0, 0, 0, 0, 5, 0};
//			((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0E-4};
//			((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

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

			//---- label9 ----
			label9.setText("Please wait... generating keys");
			label9.setVisible(false); //not visible when key generation is not in progress.
			panel1.add(label9, new GridBagConstraints(3, 3, 2, 1, 0.0, 0.0,
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

			//---- button1 ----
			button1.setText("Create keys");
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createKeysButtonActionPerformed(e);
				}
			});
			panel1.add(button1, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- label8 ----
			label8.setText("Prime bitsize");
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

			//---- button11 ----
			button11.setText("Clear keys");
			button11.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clearKeysButtonActionPerformed(e);
				}
			});
			panel1.add(button11, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
			panel1.add(textField6, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

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
//			panel2.setLayout(new GridBagLayout());
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
//			panel4.setLayout(new GridBagLayout());
			((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {80, 81, 80, 0};
			((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0};
			((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

			//---- checkBox1 ----
			checkBox1.setText("One letter");
			checkBox1.setOpaque(false);
			checkBox1.setSelected(true);
			checkBox1.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					padding1CheckBoxStateChanged(e);
				}
			});
			panel4.add(checkBox1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- checkBox2 ----
			checkBox2.setText("Two letters");
			checkBox2.setOpaque(false);
			checkBox2.setSelected(true);
			checkBox2.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					padding2CheckBoxStateChanged(e);
				}
			});
			panel4.add(checkBox2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- checkBox3 ----
			checkBox3.setText("Three letters");
			checkBox3.setOpaque(false);
			checkBox3.setSelected(true);
			checkBox3.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					padding3CheckBoxStateChanged(e);
				}
			});
			panel4.add(checkBox3, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel4, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 0, 0), 0, 0));
		
		//======== panel5 ========
		{
			panel5 = new GradientPanel(panelColor, new GridBagLayout());
//			panel5.setLayout(new GridBagLayout());
			((GridBagLayout)panel5.getLayout()).columnWidths = new int[] {10, 0, 5, 0};
			((GridBagLayout)panel5.getLayout()).rowHeights = new int[] {210, 0, 0};
			((GridBagLayout)panel5.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
			((GridBagLayout)panel5.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};
			
			panel5.setBorder(new TitledBorder(new LineBorder(Color.gray, 1, true), "RSA execution"));

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(textArea1);
			}
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
			radioButton1.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					teachModeRadioButtonStateChanged(e);
				}
			});
			panel7.add(radioButton1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- radioButton2 ----
			radioButton2.setText("Secure mode");
			radioButton2.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					secureModeRadioButtonStateChanged(e);
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

		//---- buttonGroup2 ----
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(radioButton3);
		buttonGroup2.add(radioButton4);

		//---- buttonGroup1 ----
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(radioButton1);
		buttonGroup1.add(radioButton2);
		//End of component initialization  //GEN-END:initComponents
	}
	
	//Action events
	
	private void teachRadioItemButtonStateChanged(ChangeEvent e) {

	}

	private void secureRadioItemButtonStateChanged(ChangeEvent e) {

	}

	private void createKeysMenuItemActionPerformed(ActionEvent e) {
		createKeys();
	}

	private void savePublicKeyMenuItemActionPerformed(ActionEvent e) {
		savePublicKey();
	}

	private void savePrivateKeyMenuItemActionPerformed(ActionEvent e) {
		savePrivateKey();
	}

	private void loadPublicKeyMenuItemActionPerformed(ActionEvent e) {
		loadPublicKey();
	}

	private void loadPrivateKeyMenuItemActionPerformed(ActionEvent e) {
		loadPrivateKey();
	}

	private void padding1MenuItemStateChanged(ChangeEvent e) {

	}

	private void padding2MenuItemStateChange(ChangeEvent e) {

	}

	private void padding3MenuItemStateChange(ChangeEvent e) {

	}

	private void saveExecutionMenuItemActionPerformed(ActionEvent e) {
		saveExecution();
	}

	private void loadExecutionMenuItemActionPerformed(ActionEvent e) {
		loadExecution();
	}

	private void fullScreenMenuItemActionPerformed(ActionEvent e) {
		showExecFullScreen();
	}

	private void showHelpMenuItemActionPerformed(ActionEvent e) {
		new Help();
	}

	private void aboutMenuItemActionPerformed(ActionEvent e) {

	}

	private void teachModeRadioButtonStateChanged(ChangeEvent e) {

	}

	private void secureModeRadioButtonStateChanged(ChangeEvent e) {

	}

	private void savePublicKeyButtonActionPerformed(ActionEvent e) {
		savePublicKey();
	}

	private void loadPublicKeyButtonActionPerformed(ActionEvent e) {
		loadPublicKey();
	}

	private void savePrivateKeyButtonActionPerformed(ActionEvent e) {
		savePrivateKey();
	}

	private void loadPrivateKeyButtonActionPerformed(ActionEvent e) {
		loadPrivateKey();
	}

	private void padding1CheckBoxStateChanged(ChangeEvent e) {

	}

	private void padding2CheckBoxStateChanged(ChangeEvent e) {

	}

	private void padding3CheckBoxStateChanged(ChangeEvent e) {

	}

	private void encryptButtonActionPerformed(ActionEvent e) {

	}

	private void decryptButtonActionPerformed(ActionEvent e) {

	}

	private void saveExecutionButtonActionPerformed(ActionEvent e) {
		saveExecution();
	}

	private void loadExecutionButtonActionPerformed(ActionEvent e) {
		loadExecution();
	}

	private void fontSizeComboBoxActionPerformed(ActionEvent e) {
		if (comboBox1.getSelectedItem() == "Font size 12 pt") {
			textArea1.setFont(new Font("Arial", Font.PLAIN, 12));
		}
		else if (comboBox1.getSelectedItem() == "Font size 14 pt") {
			textArea1.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		else if (comboBox1.getSelectedItem() == "Font size 16 pt") {
			textArea1.setFont(new Font("Arial", Font.PLAIN, 16));
		}
		else if (comboBox1.getSelectedItem() == "Font size 18 pt") {
			textArea1.setFont(new Font("Arial", Font.PLAIN, 18));
		}
		else if (comboBox1.getSelectedItem() == "Font size 20 pt") {
			textArea1.setFont(new Font("Arial", Font.PLAIN, 20));
		}
	}

	private void fullScreenButtonActionPerformed(ActionEvent e) {
		showExecFullScreen();
	}
	
	private void createKeysButtonActionPerformed(ActionEvent e) {
		createKeys();
	}
	
	private void clearKeysButtonActionPerformed(ActionEvent e) {
		//Clear p, q, e, n, d textfields
		clearKeyTextFields();
	}
	
	private void ClearExecButtonActionPerformed(ActionEvent e) {
		//Clear execution textarea
		textArea1.setText("");
	}

	private void copyToClipboardButtonActionPerformed(ActionEvent e) {
		clipboard = new ClipboardCopy();
		clipboard.copyToClipboard(textArea1.getText());
	}
	//End of action events
	
	//Methods used in actions.
	
	public void clearKeyTextFields() {
		//Clear p, q, e, n, d textfields
		textField1.setText("");
		textField2.setText("");
		textField3.setText("");
		textField4.setText("");
		textField5.setText("");
	}
	
	public void createKeys() {
		//Generate keys
		boolean correctBitSize = false;
		if (Integer.parseInt(textField6.getText()) <= 5) { //bit size under 5 bits.
			System.out.println("Prime bit size must be larger than 5.");
		}
		else if (textField6.getText().equals("")) { //bit size field is empty.
			System.out.println("You didn't define prime bit size.");
		}
		else { //bit size is ok.
			label9.setVisible(true);
			
//			GenerateKeys genKeys = new GenerateKeys(Integer.parseInt(textField6.getText()));
//			correctBitSize = true;
			//Store key instances
//	        SwingUtilities.invokeLater(new Runnable() { 
//	        	public void run() {
//	        		JFrame.setDefaultLookAndFeelDecorated(false);
	    			GenerateKeys genKeys = new GenerateKeys(Integer.parseInt(textField6.getText()));
	    			publicKey = genKeys.getPublicKey();
	    			privateKey = genKeys.getPrivateKey();
	    			
//	            }
//	        });
	        correctBitSize = true;
//			publicKey = genKeys.getPublicKey();
//			privateKey = genKeys.getPrivateKey();
			//Write textfields
			textField1.setText(privateKey.getPrimeP().toString());
			textField2.setText(privateKey.getPrimeQ().toString());
			textField3.setText(privateKey.getE().toString());
			textField4.setText(publicKey.getN().toString());
			textField5.setText(privateKey.getPrivateExponent().toString());
			label9.setVisible(false);
		}	
	}
	
	public void savePublicKey() {
		openSave = new Open_Save(this);
		openSave.savePublicKey(publicKey);
	}
	
	public void loadPublicKey() {
		clearKeyTextFields();
		openSave = new Open_Save(this);
		publicKey = openSave.loadPublicKey();
		textField3.setText(privateKey.getE().toString());
		textField4.setText(publicKey.getN().toString());
	}
	
	public void savePrivateKey() {
		openSave = new Open_Save(this);
		openSave.savePrivateKey(privateKey);
	}
	
	public void loadPrivateKey() {
		openSave = new Open_Save(this);
		privateKey = openSave.loadPrivateKey();
		textField1.setText(privateKey.getPrimeP().toString());
		textField2.setText(privateKey.getPrimeQ().toString());
		textField3.setText(privateKey.getE().toString());
		textField4.setText(privateKey.getN().toString());
		textField5.setText(privateKey.getPrivateExponent().toString());
	}
	
	public void saveExecution() {
		loadSaveExec = new Load_Save_Exec(this, textArea1);
		loadSaveExec.saveExecToFile();
	}
	
	public void loadExecution() {
		loadSaveExec = new Load_Save_Exec(this, textArea1);
		loadSaveExec.loadExecFromFile();
	}
	
	public void showExecFullScreen() {
		FullScreen fullScreen = new FullScreen(textArea1);
	}
	
	//End of methods used in actions.
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { 
        	public void run() {
        		JFrame.setDefaultLookAndFeelDecorated(false);
        		new Gui();
            }
        });
    }

}
