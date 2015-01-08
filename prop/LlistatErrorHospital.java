package prop;
import java.awt.Dimension;
import javax.swing.event.ListSelectionEvent;


public class LlistatErrorHospital extends PanelLlistatError {
	private CtrlVistaHospital ctrlvh;
	public LlistatErrorHospital(CtrlVistaHospital cvh) {
		ctrlvh = cvh;
		error.setEditable(false);
		
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
		
	}
}
