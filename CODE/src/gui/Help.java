package gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

/**
 * @author Petri Tuononen
 * @since 11.02.2009
 */
public class Help extends JFrame {
	
	public Help() {
		initComponents();
	}

	//Variables declaration  //GEN-BEGIN:variables
	private JScrollPane scrollPane1;
	private JEditorPane editorPane1;
	private String fileName = "help.html"; 
	private String notFoundText = "Help file not found";
	//End of variables declaration  //GEN-END:variables

	private void initComponents() {
		//Component initialization  //GEN-BEGIN:initComponents
		scrollPane1 = new JScrollPane();
		editorPane1 = new JEditorPane();
		editorPane1.setContentType("text/html");
		editorPane1.setEditable(false);
		try {
			File file = new File(fileName);
			String url = "file:///"+ file.getAbsolutePath();
			editorPane1.setPage(url);
			
		} catch (IOException e) {
			editorPane1.setText(notFoundText);
		}

		//======== this ========
		setVisible(true);
		setTitle("Help - RSA Education Cryptosystem");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {10, 0, 5, 0};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {10, 0, 5, 0};
		((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(editorPane1);
		}
		contentPane.add(scrollPane1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));
		setSize(900, 650);
		setLocationRelativeTo(null);
		//End of component initialization  //GEN-END:initComponents
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { 
        	public void run() {
        		JFrame.setDefaultLookAndFeelDecorated(false);
        		new Help();
            }
        });
    }
    
}
