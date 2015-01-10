package prop;

import javax.swing.event.ListSelectionEvent;


public class LlistatErrorCalendari extends PanelLlistatError{
	private CtrlVistaCalendari ctrlvc;
	
	public LlistatErrorCalendari(CtrlVistaCalendari cvc) {
		ctrlvc = cvc;
		llista1.addListSelectionListener(this);
		model1.addElement("Clica");
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
		if (!arg0.getValueIsAdjusting()) {
			//if (!CtrlCalendari.existeixCalendari(llista1.getSelectedValue())) {
				//ctrlvc.swap(2,2);
			//}
			ctrlvc.swap(2,3);
		}
		llista1.clearSelection();
	}
}
