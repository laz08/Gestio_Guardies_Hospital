package prop;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;


public class CtrlVistaPlantilla {
	private CtrlVistaPrincipal ctrlvp;
	private JPanel plantilla = new JPanel();
	private JPanel dret = new JPanel();
	private JPanel esquerre = new JPanel();
	
	public CtrlVistaPlantilla(CtrlVistaPrincipal cvp) {
		ctrlvp = cvp;
		plantilla.setLayout(new BorderLayout());
		dret.setLayout(new CardLayout());
		esquerre.setLayout(new CardLayout());
		dret.add(new TresBotonsPlantilla(this), "2-1");
		dret.add(new BotoTextPlantilla(this), "2-2");
		dret.add(new BotoLlistaMesPlantilla(this), "2-3");
		esquerre.add(new LlistatErrorPlantilla(this), "1-1");
		esquerre.add(new BotoLlistaPlantilla(this), "1-2");
		plantilla.add(esquerre, BorderLayout.WEST);
		plantilla.add(dret, BorderLayout.EAST);
	}
	
	public JPanel tornavista() {
		return plantilla;
	}
	
	public void swap(int banda, int numpanelins) {
		if (banda == 1) {
			CardLayout cl = (CardLayout) esquerre.getLayout();
			cl.show(esquerre, String.valueOf(banda)+"-"+String.valueOf(numpanelins));
		}
		else {
			CardLayout cl = (CardLayout) dret.getLayout();
			cl.show(dret, String.valueOf(banda)+"-"+String.valueOf(numpanelins));
		}
	}
}
