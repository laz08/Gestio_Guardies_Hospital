package prop;

import javax.swing.JLabel;

public class LlistatErrorRestriccio extends PanelLlistatError{
	//private CtrlVistaRestriccio ctrlvr;
	private JLabel textres = new JLabel("LLISTAT DE RESTRICCIONS");
	
	public LlistatErrorRestriccio() { //(CtrlVistaRestriccio cvr) {
		//ctrlvr = cvr;
		remove(scroll1);
		add(textres);
		add(scroll1);
	}
}
