package prop;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class PanelLlistatError extends PanelLlistat{
	protected JTextField error = new JTextField();
	protected ImageIcon load = new ImageIcon("Cercle.gif");
	protected JLabel icona = new JLabel("Error: ", load, JLabel.CENTER);
	
	public PanelLlistatError() {
		icona.setHorizontalTextPosition(JLabel.LEFT);
		add(icona, BorderLayout.SOUTH);
		
	}
}
