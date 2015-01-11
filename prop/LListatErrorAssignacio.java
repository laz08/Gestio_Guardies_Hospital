package prop;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;

public class LListatErrorAssignacio extends PanelLlistatError{
	private CtrlVistaAssignacio ctrlva;
	private JLabel titoldoctor = new JLabel("LLISTA DOCTORS");
	
	public LListatErrorAssignacio(CtrlVistaAssignacio cva) {
		ctrlva = cva;
		remove(scroll1);
		remove(error);
		add(titoldoctor, BorderLayout.NORTH);
		add(scroll1);
		add(error, BorderLayout.SOUTH);
		scroll1.setSize(getSize());
		llista1.addListSelectionListener(this);
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getSource() == this.llista1) {
			if (!arg0.getValueIsAdjusting()) {
				
			}
		}
	}
}
