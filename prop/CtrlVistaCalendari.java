package prop;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

public class CtrlVistaCalendari {
	private CtrlVistaPrincipal ctrlvp;
	private JPanel calendari = new JPanel();
	private JPanel dret = new JPanel();
	private JPanel esquerre = new JPanel();
	
	public CtrlVistaCalendari(CtrlVistaPrincipal cvp) {
		ctrlvp = cvp;
		calendari.setLayout(new BorderLayout());
		dret.setLayout(new CardLayout());
		esquerre.setLayout(new CardLayout());
		esquerre.add(new LlistatErrorCalendari(this),"1-1");
		esquerre.add(new BotoMesTextCalendari(this), "1-2");
		dret.add(new CGCalendari(this), "2-1");
		dret.add(new BotoTextCalendari(this), "2-2");
		dret.add(new ModelCalendari(this), "2-3");
		calendari.add(esquerre, BorderLayout.WEST);
		calendari.add(dret, BorderLayout.EAST);
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

	public JPanel tornavista() {
		return calendari;
	}
	
}
