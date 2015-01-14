package prop;

import javax.swing.*;
import java.awt.*;

public class LlistatAssociacio extends PanelLlistat{
	private CtrlVistaAssignacio ctrlva;
	private JLabel titolassociat= new JLabel("ASSOCIAT AMB", SwingConstants.CENTER);
	
	public LlistatAssociacio(CtrlVistaAssignacio cva) {
		ctrlva = cva;
		remove(scroll1);
		add(titolassociat, BorderLayout.NORTH);
		add(scroll1);
	}

}
