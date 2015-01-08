import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class DosBotons extends Panel implements ActionListener {
	protected JButton boto1 = new JButton();
	protected JButton boto2 = new JButton();
	
	public DosBotons() {
		add(boto1);
		add(boto2);
	}

	public void actionPerformed(ActionEvent arg0) {
		
	}
}
