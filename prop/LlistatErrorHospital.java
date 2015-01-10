package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;


public class LlistatErrorHospital extends PanelLlistatError {
	private CtrlVistaHospital ctrlvh;
	public LlistatErrorHospital(CtrlVistaHospital cvh) {
		ctrlvh = cvh;
		llista1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//model1.addElement("Clica2");
		llista1.addListSelectionListener(this);
        ctrlvh.actualitza_llista_docs();
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
        ctrlvh.actualitza_llista_docs();
        ctrlvh.swap(2,2);
		llista1.clearSelection();
	}
}
