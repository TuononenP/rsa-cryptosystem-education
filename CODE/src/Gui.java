import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

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
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	private JMenu menu3;
	private JMenuItem menuItem3;
	private JMenu menu6;
	private JMenuItem menuItem4;
	private JMenuItem menuItem5;
	private JMenu menu7;
	private JMenuItem menuItem6;
	private JMenuItem menuItem7;
	private JMenu menu4;
	private JCheckBox checkBox6;
	private JCheckBox checkBox7;
	private JCheckBox checkBox8;
	private JMenu menu5;
	private JMenuItem menuItem8;
	private JMenuItem menuItem9;
	private JMenuItem menuItem10;
	private JMenu menu2;
	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JPanel panel7;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JPanel panel1;
	private JLabel label1;
	private JTextField textField1;
	private JLabel label4;
	private JTextField textField4;
	private JLabel label2;
	private JTextField textField2;
	private JLabel label5;
	private JTextField textField5;
	private JLabel label3;
	private JTextField textField3;
	private JLabel label6;
	private JLabel label7;
	private JButton button2;
	private JButton button9;
	private JButton button1;
	private JButton button7;
	private JButton button10;
	private JPanel panel2;
	private JScrollPane scrollPane2;
	private JTextArea textArea2;
	private JPanel panel4;
	private JCheckBox checkBox3;
	private JCheckBox checkBox4;
	private JCheckBox checkBox5;
	private JPanel panel3;
	private JButton button3;
	private JButton button4;
	private JPanel panel5;
	private JScrollPane scrollPane1;
	private JTextArea textArea1;
	private JPanel panel6;
	private JButton button5;
	private JButton button6;
	private JComboBox comboBox1;
	private JButton button8;
	//End of variables declaration  //GEN-END:variables

	private void initComponents() {
		//Component initialization  //GEN-BEGIN:initComponents
		menuBar = new JMenuBar();
		menu = new JMenu();
		menu1 = new JMenu();
		radioButton3 = new JRadioButton();
		radioButton4 = new JRadioButton();
		menu3 = new JMenu();
		menuItem3 = new JMenuItem();
		menu6 = new JMenu();
		menuItem4 = new JMenuItem();
		menuItem5 = new JMenuItem();
		menu7 = new JMenu();
		menuItem6 = new JMenuItem();
		menuItem7 = new JMenuItem();
		menu4 = new JMenu();
		checkBox6 = new JCheckBox();
		checkBox7 = new JCheckBox();
		checkBox8 = new JCheckBox();
		menu5 = new JMenu();
		menuItem8 = new JMenuItem();
		menuItem9 = new JMenuItem();
		menuItem10 = new JMenuItem();
		menu2 = new JMenu();
		menuItem1 = new JMenuItem();
		menuItem2 = new JMenuItem();
		panel7 = new JPanel();
		radioButton1 = new JRadioButton();
		radioButton2 = new JRadioButton();
		panel1 = new JPanel();
		label1 = new JLabel();
		textField1 = new JTextField();
		label4 = new JLabel();
		textField4 = new JTextField();
		label2 = new JLabel();
		textField2 = new JTextField();
		label5 = new JLabel();
		textField5 = new JTextField();
		label3 = new JLabel();
		textField3 = new JTextField();
		label6 = new JLabel();
		label7 = new JLabel();
		button2 = new JButton();
		button9 = new JButton();
		button1 = new JButton();
		button7 = new JButton();
		button10 = new JButton();
		panel2 = new JPanel();
		scrollPane2 = new JScrollPane();
		textArea2 = new JTextArea();
		panel4 = new JPanel();
		checkBox3 = new JCheckBox();
		checkBox4 = new JCheckBox();
		checkBox5 = new JCheckBox();
		panel3 = new JPanel();
		button3 = new JButton();
		button4 = new JButton();
		panel5 = new JPanel();
		scrollPane1 = new JScrollPane();
		textArea1 = new JTextArea();
		panel6 = new JPanel();
		button5 = new JButton();
		button6 = new JButton();
		comboBox1 = new JComboBox();
		button8 = new JButton();

		//======== this ========
		setTitle("RSA Education Cryptosystem");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {10, 581, 10};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {10, 0, 10, 0, 10, 64, 10, 0, 10, 0, 10, 232, 5, 0, 0, 0};
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

					//---- checkBox6 ----
					checkBox6.setText("One letter");
					checkBox6.setPreferredSize(new Dimension(95, 23));
					checkBox6.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent e) {
							padding1MenuItemStateChanged(e);
						}
					});
					menu4.add(checkBox6);

					//---- checkBox7 ----
					checkBox7.setText("Two letters");
					checkBox7.setPreferredSize(new Dimension(95, 23));
					checkBox7.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent e) {
							padding2MenuItemStateChange(e);
						}
					});
					menu4.add(checkBox7);

					//---- checkBox8 ----
					checkBox8.setText("Three letters");
					checkBox8.setPreferredSize(new Dimension(95, 23));
					checkBox8.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent e) {
							padding3MenuItemStateChange(e);
						}
					});
					menu4.add(checkBox8);
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
		}
		setJMenuBar(menuBar);

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

		//======== panel1 ========
		{
			panel1.setBorder(new TitledBorder(new LineBorder(Color.gray, 1, true), "Key creation"));
			panel1.setLayout(new GridBagLayout());
			((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {25, 162, 32, 105, 105, 20, 194, 5, 0};
			((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {12, 0, 0, 0, 0, 0, 5, 0};
			((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0E-4};
			((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

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
			panel1.add(label6, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- label7 ----
			label7.setText("Load");
			panel1.add(label7, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- button2 ----
			button2.setText("Public key");
			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					savePublicKeyButtonActionPerformed(e);
				}
			});
			panel1.add(button2, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- button9 ----
			button9.setText("Public key");
			button9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadPublicKeyButtonActionPerformed(e);
				}
			});
			panel1.add(button9, new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- button1 ----
			button1.setText("Create keys");
			panel1.add(button1, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- button7 ----
			button7.setText("Private key");
			button7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					savePrivateKeyButtonActionPerformed(e);
				}
			});
			panel1.add(button7, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- button10 ----
			button10.setText("Private key");
			button10.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadPrivateKeyButtonActionPerformed(e);
				}
			});
			panel1.add(button10, new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
			new Insets(0, 0, 0, 0), 0, 0));

		//======== panel2 ========
		{
			panel2.setBorder(new TitledBorder(new LineBorder(Color.gray, 1, true), "Message to encrypt/decrypt"));
			panel2.setLayout(new GridBagLayout());
			((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {10, 435, 5, 0};
			((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {71, 5, 0};
			((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
			((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

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

		//======== panel4 ========
		{
			panel4.setBorder(new TitledBorder(new LineBorder(Color.gray, 1, true), "Padding scheme"));
			panel4.setLayout(new GridBagLayout());
			((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {80, 81, 80, 0};
			((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0};
			((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

			//---- checkBox3 ----
			checkBox3.setText("One letter");
			checkBox3.setSelected(true);
			checkBox3.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					padding1CheckBoxStateChanged(e);
				}
			});
			panel4.add(checkBox3, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- checkBox4 ----
			checkBox4.setText("Two letters");
			checkBox4.setSelected(true);
			checkBox4.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					padding2CheckBoxStateChanged(e);
				}
			});
			panel4.add(checkBox4, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- checkBox5 ----
			checkBox5.setText("Three letters");
			checkBox5.setSelected(true);
			checkBox5.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					padding3CheckBoxStateChanged(e);
				}
			});
			panel4.add(checkBox5, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel4, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
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

		//======== panel5 ========
		{
			panel5.setBorder(new TitledBorder(new LineBorder(Color.gray, 1, true), "RSA execution"));
			panel5.setLayout(new GridBagLayout());
			((GridBagLayout)panel5.getLayout()).columnWidths = new int[] {10, 0, 5, 0};
			((GridBagLayout)panel5.getLayout()).rowHeights = new int[] {200, 5, 0};
			((GridBagLayout)panel5.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
			((GridBagLayout)panel5.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

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
			((GridBagLayout)panel6.getLayout()).columnWidths = new int[] {0, 0, 113, 93, 100, 0};
			((GridBagLayout)panel6.getLayout()).rowHeights = new int[] {0, 0};
			((GridBagLayout)panel6.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)panel6.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

			//---- button5 ----
			button5.setText("Save execution");
			button5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveExecutionButtonActionPerformed(e);
				}
			});
			panel6.add(button5, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- button6 ----
			button6.setText("Load execution");
			button6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadExecutionButtonActionPerformed(e);
				}
			});
			panel6.add(button6, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- comboBox1 ----
			comboBox1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fontSizeComboBoxActionPerformed(e);
				}
			});
			panel6.add(comboBox1, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- button8 ----
			button8.setText("Full screen");
			button8.setPreferredSize(new Dimension(100, 23));
			button8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fullScreenButtonActionPerformed(e);
				}
			});
			panel6.add(button8, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel6, new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0,
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
	
	private void teachRadioItemButtonStateChanged(ChangeEvent e) {

	}

	private void secureRadioItemButtonStateChanged(ChangeEvent e) {

	}

	private void createKeysMenuItemActionPerformed(ActionEvent e) {

	}

	private void savePublicKeyMenuItemActionPerformed(ActionEvent e) {

	}

	private void savePrivateKeyMenuItemActionPerformed(ActionEvent e) {

	}

	private void loadPublicKeyMenuItemActionPerformed(ActionEvent e) {

	}

	private void loadPrivateKeyMenuItemActionPerformed(ActionEvent e) {

	}

	private void padding1MenuItemStateChanged(ChangeEvent e) {

	}

	private void padding2MenuItemStateChange(ChangeEvent e) {

	}

	private void padding3MenuItemStateChange(ChangeEvent e) {

	}

	private void saveExecutionMenuItemActionPerformed(ActionEvent e) {

	}

	private void loadExecutionMenuItemActionPerformed(ActionEvent e) {

	}

	private void fullScreenMenuItemActionPerformed(ActionEvent e) {

	}

	private void showHelpMenuItemActionPerformed(ActionEvent e) {

	}

	private void aboutMenuItemActionPerformed(ActionEvent e) {

	}

	private void teachModeRadioButtonStateChanged(ChangeEvent e) {

	}

	private void secureModeRadioButtonStateChanged(ChangeEvent e) {

	}

	private void savePublicKeyButtonActionPerformed(ActionEvent e) {

	}

	private void loadPublicKeyButtonActionPerformed(ActionEvent e) {

	}

	private void savePrivateKeyButtonActionPerformed(ActionEvent e) {

	}

	private void loadPrivateKeyButtonActionPerformed(ActionEvent e) {

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

	}

	private void loadExecutionButtonActionPerformed(ActionEvent e) {

	}

	private void fontSizeComboBoxActionPerformed(ActionEvent e) {

	}

	private void fullScreenButtonActionPerformed(ActionEvent e) {

	}
	
	public static void createGUI() {
		new Gui();
	}
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { 
        	public void run() {
        		JFrame.setDefaultLookAndFeelDecorated(false);
        		createGUI();
            }
        });
    }

}
