package prop;

import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;

public class LlistatErrorAlgorisme extends PanelLlistatError {
	private CtrlVistaAlgorisme ctrlval;
	private JLabel textplantilla = new JLabel("Plantilles");
	
	public LlistatErrorAlgorisme(CtrlVistaAlgorisme cval) {
		ctrlval = cval;
		remove(scroll1);
		add(textplantilla);
		add(scroll1);
		llista1.addListSelectionListener(this);
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getSource() == llista1) {
			if (!arg0.getValueIsAdjusting()) {
				
			}
		}
	}
}
