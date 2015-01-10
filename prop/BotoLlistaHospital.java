package prop;

import java.awt.event.ActionEvent;

public class BotoLlistaHospital extends BotoLlista {
	private CtrlVistaHospital ctrlvh;
	public BotoLlistaHospital(CtrlVistaHospital cvh) {
		ctrlvh = cvh;
		b1.addActionListener(this);
		b2.addActionListener(this);
		b1.setText("Enrere");
		b2.setText("Acceptar");
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == b1) {

		}
		
		else if (arg0.getSource() == b2) {

		}
		ctrlvh.swap(1,1);
	}

}
