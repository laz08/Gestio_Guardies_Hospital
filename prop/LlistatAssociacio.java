package prop;

import java.awt.BorderLayout;

import javax.swing.JLabel;

public class LlistatAssociacio extends PanelLlistat{
	private CtrlVistaAssignacio ctrlva;
	private JLabel titolassociat= new JLabel("ASSOCIAT AMB");
	
	public LlistatAssociacio(CtrlVistaAssignacio cva) {
		ctrlva = cva;
		remove(scroll1);
		add(titolassociat, BorderLayout.NORTH);
		add(scroll1);
	}
}
