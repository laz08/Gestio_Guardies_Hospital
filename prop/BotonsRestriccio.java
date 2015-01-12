package prop;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class BotonsRestriccio extends QuatreBotons{
	//private CtrlVistaRestriccio ctrlvr;
	private JRadioButton bototorn = new JRadioButton("Torn");
	private JRadioButton botodia = new JRadioButton("Dia");
	private JLabel obrircalendari = new JLabel("Selecciona una data:");
	private JLabel textcomplet = new JLabel("Restricció Completa");
	private JTextField restricciocompleta = new JTextField();
	private GridBagConstraints c = new GridBagConstraints();
	private JButton neteja = new JButton("Neteja Restriccio");
	
	public BotonsRestriccio() { //(CtrlVistaRestriccio cvr) {
		remove(b1);
		remove(b2);
		remove(b3);
		remove(b4);
		b1.setText("Obrir Calendari");
		b2.setText("XOR");
		b3.setText("AND");
		b4.setText("NOP");
		setLayout(new GridBagLayout());
		b1.addActionListener(this);
		restricciocompleta.setEditable(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.insets  = new Insets(20,10,10,20);
		add(bototorn, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		add(botodia, c);
		c.gridx = 1;
		c.gridy = 0;
		add(obrircalendari);
		c.gridx = 1;
		c.gridy = 1;
		add(b1, c);
		c.gridx = 0;
		c.gridy = 2;
		add(b2, c);
		c.gridx = 1;
		c.gridy = 2;
		add(b3, c);
		c.gridx = 2;
		c.gridy = 2;
		add(b4, c);
		c.gridx = 0;
		c.gridy = 3;
		add(textcomplet, c);
		c.gridx = 0;
		c.gridy = 4;
		add(restricciocompleta, c);
		c.gridx = 0;
		c.gridy = 5;
		add(neteja, c);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.b1) {
			
		}
	}
}
