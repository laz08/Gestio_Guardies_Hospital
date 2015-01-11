package prop;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;

public class LListatTorns extends PanelLlistat{
	private CtrlVistaAssignacio ctrlva;
	private JLabel titoltorns = new JLabel("LLISTA DE TORNS"); 
	
	public LListatTorns(CtrlVistaAssignacio cva) {
		ctrlva = cva;
		remove(scroll1);
		add(titoltorns, BorderLayout.NORTH);
		add(scroll1);
		llista1.addListSelectionListener(this);
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getSource() == this.llista1) {
			if (!arg0.getValueIsAdjusting()) {
				
			}
		}
	}
}
