
package prop;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class PanelLlistatError extends PanelLlistat{
	protected JTextField error = new JTextField();
	protected ImageIcon load = new ImageIcon("Cercle.gif");
	protected JLabel icona = new JLabel(load, JLabel.RIGHT);
	protected JLabel label = new JLabel();
	
	public PanelLlistatError() {
		label.add(error, JLabel.LEFT);
		label.add(icona, JLabel.RIGHT);
		add(label);
		
	}
}
