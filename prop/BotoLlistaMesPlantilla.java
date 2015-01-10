package prop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;


public class BotoLlistaMesPlantilla extends BotoLlista {
	private CtrlVistaPlantilla ctrlvp;
	private JLabel nomPlantilla = new JLabel();
	private JButton b3 = new JButton("Eliminar Plantilla");
	private JButton b4 = new JButton("Enrere");
	
	public BotoLlistaMesPlantilla(CtrlVistaPlantilla cvp) {
		ctrlvp = cvp;
		remove(b1);
		remove(b2);
		remove(scroll1);
		setLayout(new GridLayout(6,1));
		add(nomPlantilla);
		add(scroll1);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b1.setText("Assignar Doctor");
		b2.setText("Desassignar Doctor");
		add(b1);
		add(b2);
		add(b3);
		add(b4);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == b1) {
			ctrlvp.swap(1,2);
		}
		else if (arg0.getSource() == b2) {
			
		}
		else if (arg0.getSource() == b3) {
			ctrlvp.swap(2,1);
		}
		else {
			ctrlvp.swap(2,1);
		}
	}
}
