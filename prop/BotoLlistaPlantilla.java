package prop;
import java.awt.event.ActionEvent;

import javax.swing.ListSelectionModel;


public class BotoLlistaPlantilla extends BotoLlista{
	private CtrlVistaPlantilla ctrlvp;
	
	public BotoLlistaPlantilla(CtrlVistaPlantilla cvp) {
		ctrlvp = cvp;
		b1.setText("Enrere");
		b2.setText("Acceptar");
		b1.addActionListener(this);
		b2.addActionListener(this);
		llista1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == b1) {
			
		}
		else {
			
		}
		ctrlvp.swap(1,1);
	}
	
}
