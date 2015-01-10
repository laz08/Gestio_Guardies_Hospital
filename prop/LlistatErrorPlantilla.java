package prop;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class LlistatErrorPlantilla extends PanelLlistatError implements ListSelectionListener{
	private CtrlVistaPlantilla ctrlvp;

	public LlistatErrorPlantilla(CtrlVistaPlantilla cvp){
		ctrlvp = cvp;
		model1.addElement(String.valueOf("Clica aqui"));
		llista1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		llista1.addListSelectionListener(this);
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
		ctrlvp.swap(2,3); 
		llista1.clearSelection();
	}
}
