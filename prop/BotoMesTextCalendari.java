package prop;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JTextField;


public class BotoMesTextCalendari extends BotoMesText {
	private CtrlVistaCalendari ctrlvc;
	private JCheckBox festiu = new JCheckBox();
	private JTextField t8 = new JTextField();
	private JTextField t9 = new JTextField();
	private JTextField t10 = new JTextField();
	private JTextField t11 = new JTextField();
	private JTextField t12 = new JTextField();
	private JTextField t13 = new JTextField();
	private GridBagConstraints c = new GridBagConstraints();

	public BotoMesTextCalendari(CtrlVistaCalendari cvc) {
		ctrlvc = cvc;
		t1.setEditable(false);
		t2.setEditable(false);
		t3.setEditable(false);
		t6.setEditable(false);
		t7.setEditable(false);
		t10.setEditable(false);
		t11.setEditable(false);
		l1.setText("Dia: ");
		l2.setText("Festiu: ");
		l3.setText("Torns: ");
		l4.setText("Matí: ");
		l5.setText("Tarda: ");
		l6.setText("Nit: ");
		b1.setText("Enrere");
		b2.setText("Acceptar");
		b1.addActionListener(this);
		b2.addActionListener(this);
		setLayout(new GridBagLayout());
		remove(textfield1);
		remove(b1);
		remove(b2);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		add(l1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		add(t1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		add(l2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		add(festiu, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		add(l3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		add(l4, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		add(t2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		add(t3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 1;
		add(t4, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 3;
		c.gridwidth = 1;
		add(t5, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		add(l5, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		add(t6, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 1;
		add(t7, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 4;
		c.gridwidth = 1;
		add(t8, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 4;
		c.gridwidth = 1;
		add(t9, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		add(l6, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		add(t10, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 1;
		add(t11, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 5;
		c.gridwidth = 1;
		add(t12, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 5;
		c.gridwidth = 1;
		add(t13, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		add(b1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 6;
		c.gridwidth = 1;
		add(b2, c);

	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == b1) {
			
		}
		else {
			
		}
		ctrlvc.swap(1,1);
	}
}
