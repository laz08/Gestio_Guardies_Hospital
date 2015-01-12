package prop;

import javax.swing.*;
import java.awt.*;

public class CtrlVistaRestriccio {
	private CtrlVistaPrincipal ctrlvp;
	private JPanel restriccions = new JPanel();
	private JPanel dret = new JPanel();
	private CalendariRestriccio calendari = new CalendariRestriccio(this);
	private LlistatErrorRestriccio llistat= new LlistatErrorRestriccio(this);
	private BotonsRestriccio botons = new BotonsRestriccio(this);
	
	public CtrlVistaRestriccio(CtrlVistaPrincipal cvp) {
		ctrlvp = cvp;
		restriccions.setLayout(new BorderLayout());
		dret.setLayout(new CardLayout());
		dret.add(botons, "2-1");
		dret.add(calendari, "2-2");

        restriccions.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 0));
        restriccions.add(llistat, BorderLayout.WEST);
		restriccions.add(dret, BorderLayout.EAST);
	}

	public Component tornavista() {
		return restriccions;
	}
	
	public void swap(int banda, int numpanelins) {
		if (banda == 2) {
			CardLayout cl = (CardLayout) dret.getLayout();
			cl.show(dret, String.valueOf(banda)+"-"+String.valueOf(numpanelins));
		}
	}
	public void habilitatorns(boolean torns) {
		calendari.habilitatorns(torns);
	}
	
	public void habilitaoperacions(boolean operacions) {
		botons.habilitaoperacions(operacions);
	}
	
}
