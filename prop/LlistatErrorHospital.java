package prop;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;


public class LlistatErrorHospital extends PanelLlistatError {
	private CtrlVistaHospital ctrlvh;
	public LlistatErrorHospital(CtrlVistaHospital cvh) {
		ctrlvh = cvh;
		error.setEditable(false);
		llista1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model1.addElement("Clica");
		llista1.addListSelectionListener(this);
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
		ctrlvh.swap(2,2);
		llista1.clearSelection();
	}
}
