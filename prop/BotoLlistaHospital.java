package prop;
import java.awt.event.ActionEvent;

public class BotoLlistaHospital extends BotoLlista {
	private CtrlVistaHospital ctrlvh;
	public BotoLlistaHospital(CtrlVistaHospital cvh) {
		ctrlvh = cvh;
		remove(b1);
		remove(b2);
		add(scroll1);
		add(b1);
		add(b2);
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == b1) {

		}
		
		else if (arg0.getSource() == b2) {

		}
		ctrlvh.swap(1);
	}

}
