package gui;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Paints a panel with gradient color.
 * 
 * @author Daniele @ koders.com /open source
 * Modified: Petri Tuononen
 */
public class GradientPanel extends JPanel {

	private static final long serialVersionUID = 7994720220746831366L;

	/**
	 * Constructor.
	 * @param background
	 */
    public GradientPanel(Color background) {
        setBackground(background);
    }

    /**
     * Constructor.
     * @param background
     * @param lm
     */
    public GradientPanel(Color background, LayoutManager lm) {
        super(lm);
        setBackground(background);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isOpaque()) {
            return;
        }
        Color control = UIManager.getColor("control");
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g;
        Paint storedPaint = g2.getPaint();
        setPaint(control, g2);
        g2.fillRect(0, 0, width, height);
        g2.setPaint(storedPaint);
    }

    private void setPaint(Color control, Graphics2D g2) {
    	g2.setPaint(new GradientPaint(0, 0, getBackground(), 0, getHeight(), control));
    }

    /**
     * Test method.
     * @param args
     */
    public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(600, 600);
		
		GradientPanel gP = new GradientPanel(Color.DARK_GRAY);
		gP.setSize(500, 500);
		
		frame.getContentPane().add(gP);
		frame.setVisible(true);
	}
}