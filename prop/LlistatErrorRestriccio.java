package prop;

import javax.swing.*;
import java.awt.*;

public class LlistatErrorRestriccio extends PanelLlistatError{
	private CtrlVistaRestriccio ctrlvr;
	private JLabel textres = new JLabel("LLISTAT DE RESTRICCIONS", SwingConstants.CENTER);
	
	public LlistatErrorRestriccio(CtrlVistaRestriccio cvr) {
		ctrlvr = cvr;
		remove(scroll1);
		add(textres, BorderLayout.NORTH);
		add(scroll1);
	}
}
