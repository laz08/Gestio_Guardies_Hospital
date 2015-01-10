package prop;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;


public class BotoTextPlantilla extends BotoText{
	private CtrlVistaPlantilla ctrlvp;
	
	public BotoTextPlantilla(CtrlVistaPlantilla cvp) {
		ctrlvp = cvp;
		textfield1.setText("HOLAAAA");
		b1.setText("Enrere");
		b2.setText("Acceptar");
		b1.addActionListener(this);
		b2.addActionListener(this);
		setLayout(new GridBagLayout());
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == b1) {
			
		}
		else {
			
		}
		ctrlvp.swap(2,1);
	}
	
}
