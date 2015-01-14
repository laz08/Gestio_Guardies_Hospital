package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

public class LListatErrorAssignacio extends PanelLlistatError {

	private CtrlVistaAssignacio ctrlva;
	private JLabel titoldoctor = new JLabel("LLISTA DOCTORS", SwingConstants.CENTER);
	private boolean event = true;

	public LListatErrorAssignacio(CtrlVistaAssignacio cva) {
		ctrlva = cva;
		remove(scroll1);
		remove(error);
		add(titoldoctor, BorderLayout.NORTH);
		add(scroll1);
		add(error, BorderLayout.SOUTH);
		scroll1.setSize(getSize());
		error.setText("");
		llista1.addListSelectionListener(this);
	}
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getSource() == this.llista1) {
			if (!arg0.getValueIsAdjusting()) {
				if (event) {
					String Doctor = (String) llista1.getSelectedValue();
					String[] splitejat = Doctor.split(" ");
					//System.out.println(splitejat[0]);
					ctrlva.mostraTornsAssociats(splitejat[0]);
				}
			}
		}
	}
	public void event(boolean b) {
		event = b;
	}

}

