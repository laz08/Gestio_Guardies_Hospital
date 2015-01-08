package prop;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;


public class BotoMesTextHospital extends BotoMesText{
	protected JButton b3 = new JButton("Eliminar Doctor");
	protected JButton b4 = new JButton("Enrere");
	protected JButton b5 = new JButton("Acceptar");
	protected JLabel l7 = new JLabel("Correu:");
	protected GridBagConstraints c = new GridBagConstraints();
	private CtrlVistaHospital ctrlvh;
	
	public BotoMesTextHospital(CtrlVistaHospital cvh) {
		ctrlvh = cvh;
		b1.setText("Afegir Restricció");
		b2.setText("Eliminar Restricció");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		l1.setText("DNI: ");
		l2.setText("Nom: ");
		l3.setText("Primer Cognom: ");
		l4.setText("Segon Cognom: ");
		l5.setText("Sou: ");
		l6.setText("Telèfon: ");
		l7.setText("Correu: ");
		setLayout(new GridBagLayout());
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 1;
		add(l1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.weighty = 1.0;
		c.gridwidth = 2;
		add(t1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1.0;
		c.gridwidth = 1;
		add(l2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.weighty = 1.0;
		c.gridwidth = 2;
		add(t2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 1.0;
		c.gridwidth = 1;
		add(l3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		add(t3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		add(l4, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		add(t4, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		add(l5, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 2;
		add(t5, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		add(l6, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 2;
		add(t6, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		add(l7, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 2;
		add(t7, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		add(b1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 1;
		add(b2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 8;
		c.gridwidth = 1;
		add(b3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		add(b4, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 9;
		c.gridwidth = 1;
		add(b5, c);
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == b1) {
			System.out.println("b1");
			ctrlvh.swap(1);
		}
		else if (arg0.getSource() == b2) {
			ctrlvh.swap(1);
		}
		else if (arg0.getSource() == b3) {
			ctrlvh.swap(2);
		}
		else if (arg0.getSource() == b4) {
			System.out.println("b4");
			ctrlvh.swap(2);
		}
		else if (arg0.getSource() == b5) {
			ctrlvh.swap(2);
		}
	}
}
