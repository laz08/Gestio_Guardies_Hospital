package prop;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;


public class BotoTextCalendari extends BotoText {
	//private CtrlVistaCalendari ctrlvc;
	private JLabel anyi = new JLabel("Any inici");
	private JLabel anyf = new JLabel("Any fi");
	private JTextField textfield2 = new JTextField();
	private GridBagConstraints c = new GridBagConstraints();
	
	public BotoTextCalendari() { //(CtrlVistaCalendari cvc) {
		//ctrlvc = cvc;
		remove(textfield1);
		remove(b1);
		remove(b2);
		setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        add(anyi, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(textfield1, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        add(anyf, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        add(textfield2, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        add(b1, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        add(b2, c);
        b1.setText("Enrere");
        b2.setText("Acceptar");
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == b1) {

		}
		else if (arg0.getSource() == b2) {
			
		}
		//cvc.swap(2,1);
	}
}
