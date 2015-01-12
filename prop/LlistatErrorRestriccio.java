package prop;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class LlistatErrorRestriccio extends PanelLlistatError{
	private CtrlVistaRestriccio ctrlvr;
	private JLabel textres = new JLabel("LLISTAT DE RESTRICCIONS", SwingConstants.CENTER);
	
	public LlistatErrorRestriccio(CtrlVistaRestriccio cvr) {
		ctrlvr = cvr;
		remove(scroll1);
		add(textres, BorderLayout.NORTH);
		add(scroll1);

        setPreferredSize(new Dimension(343, 250));
        setMaximumSize(new Dimension(343, 250));
	}

	public void actualitza(ArrayList<String> arrayList) {
		for (int i = 0; i < arrayList.size(); ++i) {
			model1.addElement(arrayList.get(i));
		}
	}
}
