package prop;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;


public class LlistatErrorHospital extends PanelLlistatError {
	public LlistatErrorHospital() {
		error.setEditable(false);
		setPreferredSize(new Dimension(400,600));
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
		
	}
}
