package prop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DosBotons extends Panel implements ActionListener {
	protected JButton b1 = new JButton();
	protected JButton b2 = new JButton();
	
	public DosBotons() {
		add(b1);
		add(b2);
	}

	public void actionPerformed(ActionEvent arg0) {

	}
}
